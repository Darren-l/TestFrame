package cn.gd.snm.frametest.retrofit.service;

import com.google.gson.annotations.SerializedName;

public class User {
    private int featuredSize;
    private String type;
    private String code;
    private String ifver;
    public int getFeaturedSize() {
        return featuredSize;
    }
    public void setFeaturedSize(int featuredSize) {
        this.featuredSize = featuredSize;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getIfver() {
        return ifver;
    }
    public void setIfver(String ifver) {
        this.ifver = ifver;
    }
}
