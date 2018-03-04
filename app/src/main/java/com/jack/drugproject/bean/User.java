package com.jack.drugproject.bean;

/**
 * Created by Roy.
 * Date on 2018/3/4.
 * Class describe:
 * <p>
 * Email : 292701927@qq.com.
 */

public class User {
    /**
     * user_id : 1
     * user_name : 飞
     * user_password : e10adc3949ba59abbe56e057f20f883e
     * user_phone : 123456
     */

    private int user_id;
    private String user_name;
    private String user_password;
    private int user_phone;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(int user_phone) {
        this.user_phone = user_phone;
    }
}

