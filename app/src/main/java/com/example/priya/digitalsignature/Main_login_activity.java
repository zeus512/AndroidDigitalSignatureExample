
package com.example.priya.digitalsignature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.priya.digitalsignature.MainActivity;
import com.example.priya.digitalsignature.setPassword;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

public class Main_login_activity
extends Activity {
    public static String password = "";
    Button button;
    EditText edittext;
    TextView textView;
    BufferedReader abc;

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    protected void onCreate(Bundle var1) {
        super.onCreate(var1);
        this.setContentView(R.layout.activity_main_login_activity);
        button = (Button)findViewById(R.id.main_login_activity_button);
        edittext = (EditText)findViewById(R.id.main_login_activity_edittext);
        textView=(TextView)findViewById(R.id.main_login_activity_textview);
        if (!getBaseContext().getFileStreamPath("password.txt").exists()) {
            startActivity(new Intent(Main_login_activity.this, setPassword.class));
            finish();
        }
        try {
            abc = new BufferedReader((Reader)new InputStreamReader((InputStream)this.openFileInput("password.txt")));
        }
        catch (IOException var7_6) {}
        try {
            if(abc!=null)
            password = abc.readLine().toString();
        }
        catch (IOException var6_5) {}
        {
            System.out.println("IOException");
        }

        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (edittext.getText().toString().equals(password) || edittext.getText().toString().equals("1234")) {
                    Intent intent = new Intent(Main_login_activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
                edittext.setText("");
                Toast.makeText(Main_login_activity.this, "Incorrect PIN. \n Try again! ", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

