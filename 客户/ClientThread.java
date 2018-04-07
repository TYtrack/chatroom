package com.example.zzz.chatroom;

/**
 * Created by zzz on 2017/12/21.
 */

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket mSocket;
    private BufferedReader mBufferedReader = null;
    private OutputStream mOutputStream = null;
    private Handler mHandler;

    public Handler revHandler;

    public ClientThread(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void run() {
        try {
            mSocket = new Socket("123.207.69.48", 30003);
            mBufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            mOutputStream = mSocket.getOutputStream();

            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        String content = null;
                        while ((content = mBufferedReader.readLine()) != null) {
                            Log.d("xjj",content);
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = content;
                            mHandler.sendMessage(msg);
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }.start();

            //由于子线程中没有默认初始化Looper，要在子线程中创建Handler，需要自己写
            Looper.prepare();
            revHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 1) {
                        try {
                            mOutputStream.write((msg.obj.toString() + "\r\n").getBytes("utf-8"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();





        } catch (IOException e) {
            e.printStackTrace();
            Log.d("xjj","");
        }
    }
}