
package com.example.priya.digitalsignature;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.priya.digitalsignature.Sign;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class MainActivity extends Activity {
    private static final String RSA = "RSA";
    static String puk;
    public static PrivateKey rk;
    public static PublicKey uk;
    String dec;
    String hmsg;
    String m = "";
    String prk;


    public static String encrypt(String string2) throws Exception {
        Cipher cipher = Cipher.getInstance((String) "RSA/ECB/PKCS1Padding");
        cipher.init(1, (Key) rk);
        byte[] arrby = cipher.doFinal(string2.getBytes());
        String string3 = "";
        int n = 0;
        while (n < arrby.length) {
            String string4 = Integer.toHexString((int) (255 & arrby[n]));
            string3 = string4.length() == 1 ? string3 + "0" + string4 : string3 + string4;
            ++n;
        }
        return string3.toUpperCase();
    }

    private Key generatekey(String string2) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode((String) string2, (int) 0));
        return KeyFactory.getInstance((String) "RSA").generatePublic((KeySpec) x509EncodedKeySpec);
    }

    public String decrypt(String string2, String string3) throws Exception {
        if (string2.getBytes().length % 2 != 0) {
            throw new IllegalArgumentException("hello");
        }
        byte[] arrby = new byte[string2.getBytes().length / 2];
        for (int i = 0; i < string2.getBytes().length; i += 2) {
            String string4 = new String(string2.getBytes(), i, 2);
            arrby[i / 2] = (byte) Integer.parseInt((String) string4, (int) 16);
        }
        Cipher cipher = Cipher.getInstance((String) "RSA");
        cipher.init(2, generatekey(string3));
        String string5 = new String(cipher.doFinal(arrby));
        return string5.substring(-40 + string5.length());
    }

    public void generateKey() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance((String) "RSA");
        keyPairGenerator.initialize(512, new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        uk = keyPair.getPublic();
        rk = keyPair.getPrivate();
        puk = Base64.encodeToString((byte[]) uk.getEncoded(), (int) 0);
        this.prk = Base64.encodeToString((byte[]) rk.getEncoded(), (int) 0);
    }

    public String hash(String string2) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance((String) "SHA-512");
        messageDigest.reset();
        messageDigest.update(string2.getBytes());
        byte[] arrby = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < messageDigest.digest().length; ++i) {
            stringBuffer.append(Integer.toHexString((int) (255 & arrby[i])));
        }
        return stringBuffer.toString().substring(0, 40);
    }


    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.main_activity_button);
        final EditText editText = (EditText) findViewById(R.id.editText);
        try {
            this.generateKey();
        } catch (Exception var4_4) {
            Toast.makeText(MainActivity.this, "" + var4_4, Toast.LENGTH_SHORT).show();
        }
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (editText.getText().toString().length() == 12) {
                  //  new LongOperation(MainActivity.this).execute((String[]) new String[]{"http://192.168.7.15:8080/DS/HashFetch"});
                    return;
                }
                Toast.makeText(MainActivity.this.getApplicationContext(), "Please Enter A valid Adhaar Number!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

//
//   private class LongOperation extends AsyncTask<String, Void, Void> {
//        private  HttpClient client=null;
//        String content;
//        private ProgressDialog dialog;
//        private String error;
//        int existFlag;
//          MainActivity abc=null;
//
//        private LongOperation(MainActivity mainActivity) {
//            this.abc = mainActivity;
//            this.content = "";
//            this.dialog = new ProgressDialog((Context)this.abc);
//            this.error = null;
//            this.client = new DefaultHttpClient();
//        }
//
//
//        protected /* varargs */ Void doInBackground(String ... var1) {
//            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
//            // java.util.ConcurrentModificationException
//            // java.util.LinkedList$ReverseLinkIterator.next(LinkedList.java:217)
//            // org.benf.cfr.reader.bytecode.analysis.structured.statement.Block.extractLabelledBlocks(Block.java:212)
//            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement$LabelledBlockExtractor.transform(Op04StructuredStatement.java:485)
//            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.transform(Op04StructuredStatement.java:639)
//            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.insertLabelledBlocks(Op04StructuredStatement.java:649)
//            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:816)
//            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
//            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
//            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
//            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
//            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
//            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
//            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
//            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
//            // org.benf.cfr.reader.Main.doJar(Main.java:128)
//            // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
//            // java.lang.Thread.run(Thread.java:818)
//            throw new IllegalStateException("Decompilation failed");
//        }
//
//        /*
//         * Unable to fully structure code
//         * Enabled force condition propagation
//         * Lifted jumps to return sites
//         */
//        protected void onPostExecute(Void var1) {
//            this.dialog.dismiss();
//           String var2_2 = "";
//            try {
//                var2_2 = MainActivity.encrypt(this.content);
//                this.abc.dec = this.abc.decrypt(var2_2, MainActivity.puk);
//
//                do {
//                    if (this.content == null) return;
//                    break;
//                } while (true);
//            }
//            catch (Exception var3_4) {
//
//            }
//            {
//                Toast.makeText(abc.getApplicationContext(), ("Public Key: " + MainActivity.uk + "\n" + "Private Key: " + MainActivity.rk + "\n" + "Hash value: " + this.content), Toast.LENGTH_SHORT).show();
//               Intent var4_3 = new Intent(abc.getApplicationContext(), (Class)Sign.class);
//                var4_3.putExtra("ds", var2_2);
//                var4_3.putExtra("puk", MainActivity.puk);
//                abc.startActivity(var4_3);
//                return;
//            }
//        }
//
//        protected void onPreExecute() {
//            this.dialog.setMessage((CharSequence)"Processing...");
//            this.dialog.show();
//        }
//    }
//
//}
//
