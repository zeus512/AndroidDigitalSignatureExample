/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.os.Bundle
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.TextView
 *  java.lang.Object
 */
package com.example.priya.digitalsignature;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class setPassword extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.setpassword);
        final EditText token = (EditText)findViewById(R.id.set_password_token);
        final EditText conf_token = (EditText)findViewById(R.id.set_password_cnf_token);
        Button save = (Button)findViewById(R.id.set_password_signin);
        save.setOnClickListener(new View.OnClickListener(){

            public void onClick(View var1) {
                Toast.makeText(setPassword.this,"coming soon",Toast.LENGTH_SHORT).show();
            }
        });
    }

}

