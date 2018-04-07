package com.example.zzz.chatroom;

/**
 * Created by zzz on 2017/9/5.
 */


public class User_info {

    private int is_self;
    private String username;
    private String image_id;
    private String message_body;



    public User_info(String username, String image_id, String message_body,int is_self){
        this.is_self=is_self;
        this.username=username;
        this.image_id=image_id;
        this.message_body=message_body;
    }

    public int getIs_self() {
        return is_self;
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return image_id;
    }

    public String getMessage_body() {
        return message_body;
    }

}

