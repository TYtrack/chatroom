package com.example.zzz.chatroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class loginActi extends AppCompatActivity {

    public static String getRandColorCode(){
        String r,g,b;
        Random random = new Random();
        r=Integer.toHexString(random.nextInt(256)).toUpperCase();
        g=Integer.toHexString(random.nextInt(256)).toUpperCase();
        b=Integer.toHexString(random.nextInt(256)).toUpperCase();
        r=r.length()==1?"0"+r:r;
        g=g.length()==1?"0"+g:g;
        b=b.length()==1?"0"+b:b;
        return r+g+b;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button=(Button)findViewById(R.id.button1);
        final String hex_color=getRandColorCode();
        final EditText editText=(EditText)findViewById(R.id.edit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=editText.getText().toString();
                Intent intent=new Intent(loginActi.this,MainActivity.class);
                intent.putExtra("username",username);
                intent.putExtra("hex_color",hex_color);
                startActivity(intent);
            }
        });
    }
}
