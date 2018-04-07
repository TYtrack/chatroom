package com.example.zzz.chatroom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etMain;
    private Button btnMain;
    private TextView tvMain;
    private ClientThread mClientThread;
    private List<User_info> users=new ArrayList<User_info>();


    //在主线程中定义Handler传入子线程用于更新TextView
    private Handler mHandler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        final String username=intent.getStringExtra("username");
        final String hex_color=intent.getStringExtra("hex_color");


        etMain = (EditText) findViewById(R.id.et_main);
        btnMain = (Button) findViewById(R.id.btn_main);

        //UseAdapter userAdapter=new UseAdapter(MainActivity.this,R.layout.user_layout,users);
        //final ListView listView=(ListView)findViewById(R.id.listView);
        //listView.setAdapter(userAdapter);

        //User_info user_info=new User_info("zplus","FF00FF","你好",1);
        //users.add(user_info);
        final TextView textView=(TextView)findViewById(R.id.text11);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());



        mHandler=new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0) {

                    int is_Self=1;
                    String[]  strs=msg.obj.toString().split("--");
                    if (strs[0]!=username){
                        is_Self=0;
                    }
                    textView.append(strs[0]+" : "+strs[2]+"\n");
                    //User_info user_info=new User_info(strs[0],strs[1],strs[2],is_Self);
                    //users.add(user_info);
                }
            }
        };

        //点击button时，获取EditText中string并且调用子线程的Handler发送到服务器
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Message msg = new Message();
                    msg.what = 1;
                    String mess=username+"--"+hex_color+"--"+etMain.getText().toString();
                    msg.obj = mess;
                    mClientThread.revHandler.sendMessage(msg);
                    etMain.setText("");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mClientThread = new ClientThread(mHandler);
        new Thread(mClientThread).start();


    }
}
