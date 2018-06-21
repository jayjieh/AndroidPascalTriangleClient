package com.tulaaclient.feature.pojo;

import com.google.gson.annotations.SerializedName;

public class PascalPojo {
    @SerializedName("data")
    private String[] data;

    @SerializedName("code")
    private String code;

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
