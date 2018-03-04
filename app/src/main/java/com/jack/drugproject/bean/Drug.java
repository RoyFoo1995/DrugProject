package com.jack.drugproject.bean;

import java.io.Serializable;

/**
 * Created by Roy.
 * Date on 2018/3/4.
 * Class describe:
 * <p>
 * Email : 292701927@qq.com.
 */

public class Drug implements Serializable {

    /**
     * re_drug_business_id : 4
     * re_drug_id : 9
     * re_business_id : 1
     * drug_id : 9
     * drug_name : 感康
     * drug_info : 感冒药
     * drug_symptom : 咳嗽
     * drug_time : 2018-02-26 22:26:06
     * business_id : 1
     * business_name : 寻医问药
     * business_password : e10adc3949ba59abbe56e057f20f883e
     * business_status : 1
     * business_phone : 123456
     */

    private int re_drug_business_id;
    private int re_drug_id;
    private int re_business_id;
    private int drug_id;
    private String drug_name;
    private String drug_info;
    private String drug_symptom;
    private String drug_time;
    private int business_id;
    private String business_name;
    private String business_password;
    private int business_status;
    private int business_phone;

    public int getRe_drug_business_id() {
        return re_drug_business_id;
    }

    public void setRe_drug_business_id(int re_drug_business_id) {
        this.re_drug_business_id = re_drug_business_id;
    }

    public int getRe_drug_id() {
        return re_drug_id;
    }

    public void setRe_drug_id(int re_drug_id) {
        this.re_drug_id = re_drug_id;
    }

    public int getRe_business_id() {
        return re_business_id;
    }

    public void setRe_business_id(int re_business_id) {
        this.re_business_id = re_business_id;
    }

    public int getDrug_id() {
        return drug_id;
    }

    public void setDrug_id(int drug_id) {
        this.drug_id = drug_id;
    }

    public String getDrug_name() {
        return drug_name;
    }

    public void setDrug_name(String drug_name) {
        this.drug_name = drug_name;
    }

    public String getDrug_info() {
        return drug_info;
    }

    public void setDrug_info(String drug_info) {
        this.drug_info = drug_info;
    }

    public String getDrug_symptom() {
        return drug_symptom;
    }

    public void setDrug_symptom(String drug_symptom) {
        this.drug_symptom = drug_symptom;
    }

    public String getDrug_time() {
        return drug_time;
    }

    public void setDrug_time(String drug_time) {
        this.drug_time = drug_time;
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

