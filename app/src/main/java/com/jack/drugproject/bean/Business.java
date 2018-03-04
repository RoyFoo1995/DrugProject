package com.jack.drugproject.bean;

/**
 * Created by Roy.
 * Date on 2018/3/5.
 * Class describe:
 * <p>
 * Email : 292701927@qq.com.
 */

public class Business {
    /**
     * re_user_business_id : 2
     * user_id : 1
     * business_id : 1
     * business_name : 寻医问药
     * business_password : e10adc3949ba59abbe56e057f20f883e
     * business_status : 1
     * business_phone : 123456
     */

    private int re_user_business_id;
    private int user_id;
    private int business_id;
    private String business_name;
    private String business_password;
    private int business_status;
    private int business_phone;

    public int getRe_user_business_id() {
        return re_user_business_id;
    }

    public void setRe_user_business_id(int re_user_business_id) {
        this.re_user_business_id = re_user_business_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(int business_id) {
        this.business_id = business_id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBusiness_password() {
        return business_password;
    }

    public void setBusiness_password(String business_password) {
        this.business_password = business_password;
    }

    public int getBusiness_status() {
        return business_status;
    }

    public void setBusiness_status(int business_status) {
        this.business_status = business_status;
    }

    public int getBusiness_phone() {
        return business_phone;
    }

    public void setBusiness_phone(int business_phone) {
        this.business_phone = business_phone;
    }
}
