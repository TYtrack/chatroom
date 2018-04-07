package com.example.zzz.chatroom;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zzz on 2017/9/5.
 */



public class UseAdapter extends ArrayAdapter<User_info> {
    private int resourceid;

    public UseAdapter(Context context, int textVi, List<User_info> objects){
        super(context,textVi,objects);
        resourceid=textVi;
    }

    class ViewHolder {

        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
    }

    public View getView(int pos, View con, ViewGroup par)
    {

        View view= LayoutInflater.from(getContext()).inflate(resourceid,null);
        ViewHolder viewHolder=new ViewHolder();
        viewHolder.leftLayout = (LinearLayout)view.findViewById(R.id.left_layout);
        viewHolder.rightLayout = (LinearLayout)view.findViewById(R.id.right_layout);

        User_info userinfo =getItem(pos);


        ImageView imageView_left=(ImageView)view.findViewById(R.id.left_image);
        ImageView imageView_right=(ImageView)view.findViewById(R.id.right_image);



        TextView textView=(TextView)view.findViewById(R.id.user_name_1);
        TextView textView1=(TextView)view.findViewById(R.id.user_name_2);
        textView.setText(userinfo.getUsername());
        textView1.setText(userinfo.getUsername());

        TextView textView2=(TextView)view.findViewById(R.id.message_left);
        TextView textView3=(TextView)view.findViewById(R.id.message_right);
        textView2.setText(userinfo.getIs_self()+userinfo.getMessage_body()+userinfo.getId());
        textView3.setText(userinfo.getIs_self()+userinfo.getMessage_body()+userinfo.getId());

        imageView_left.setColorFilter(Color.parseColor("#"+userinfo.getId()));
        imageView_right.setColorFilter(Color.parseColor("#"+userinfo.getId()));


        //参数为1，消息为用户本人发送，左控件设为不可见
        if (userinfo.getIs_self()==0){
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
        } else{
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
        }
        return view;
    }

}