
package com.example.priya.digitalsignature;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.priya.digitalsignature.MainActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class    Sign extends Activity {
    String ds;
    String puk;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.sign);
        Button button = (Button) this.findViewById(R.id.signin);
        Intent intent = this.getIntent();
        puk = intent.getStringExtra("puk");
        ds = intent.getStringExtra("ds");
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Toast.makeText(Sign.this, "Signature created! ", Toast.LENGTH_SHORT).show();
                Toast.makeText(Sign.this, "Digital Signature: " + ds, Toast.LENGTH_SHORT).show();
                // new LongOperation(Sign.this).execute((String[]) new String[]{"http://192.168.7.15:8080/DS/VerifyDS"});
            }
        });
    }
}
//
//    private class LongOperation extends AsyncTask<String, Void, Void> {
//        private final HttpClient client;
//        String content;
//        private ProgressDialog dialog;
//        private String error;
//        int existFlag;
//        final /* synthetic */ Sign this$0;
//
//        public LongOperation(Sign sign) {
//            this.this$0 = sign;
//            this.content = "";
//            this.dialog = new ProgressDialog((Context)this.this$0);
//            this.error = null;
//            this.client = new DefaultHttpClient();
//        }
//
//        /* synthetic */ LongOperation(Sign sign,  var2_2) {
//            super(sign);
//        }
//
//        }
//
//        /*
//         * Unable to fully structure code
//         * Enabled aggressive block sorting
//         * Enabled unnecessary exception pruning
//         * Enabled aggressive exception aggregation
//         * Lifted jumps to return sites
//         */
//        protected /* varargs */ Void doInBackground(String ... var1) {
//            var2_2 = new DefaultHttpClient();
//            var3_3 = new HttpPost(var1[0]);
//            var4_4 = new ArrayList(1);
//            var4_4.add((Object)new BasicNameValuePair("ds", this.this$0.ds));
//            var4_4.add((Object)new BasicNameValuePair("puk", this.this$0.puk));
//            try {
//                var3_3.setEntity((HttpEntity)new UrlEncodedFormEntity((List)var4_4));
//            }
//            catch (UnsupportedEncodingException var7_5) {
//                var7_5.printStackTrace();
//            }
//            var10_6 = var2_2.execute((HttpUriRequest)var3_3);
//            if (var10_6.getStatusLine().getStatusCode() != 200) ** GOTO lbl-1000
//            var11_7 = new BufferedReader((Reader)new InputStreamReader(var10_6.getEntity().getContent()));
//            try {
//                var12_8 = var11_7.readLine();
//                while (var12_8 != null) {
//                    this.content = this.content + var12_8;
//                    var12_8 = var13_9 = var11_7.readLine();
//                }
//                var11_7.close();
//                var14_10 = var11_7;
//                ** GOTO lbl26
//            }
//            catch (IOException var8_16) {}
//lbl-1000: // 1 sources:
//            {
//                this.content = "-1";
//                var14_10 = null;
//lbl26: // 2 sources:
//                ** try [egrp 4[TRYBLOCK] [9, 10 : 216->230)] {
//lbl27: // 1 sources:
//                var14_10.close();
//                return null;
//            }
//lbl29: // 2 sources:
//            catch (ClientProtocolException var9_11) {}
//            ** GOTO lbl-1000
//lbl31: // 2 sources:
//            catch (IOException var8_14) {}
//            {
//                var8_15.printStackTrace();
//                return null;
//            }
//            catch (ClientProtocolException var9_13) {}
//lbl-1000: // 2 sources:
//            {
//                var9_12.printStackTrace();
//                return null;
//            }
//        }
//
//        protected void onPostExecute(Void void_) {
//            if (this.dialog != null) {
//                this.dialog.dismiss();
//                this.dialog = null;
//            }
//            Intent intent = new Intent(this.this$0.getApplicationContext(), (Class)MainActivity.class);
//            this.this$0.startActivity(intent);
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
