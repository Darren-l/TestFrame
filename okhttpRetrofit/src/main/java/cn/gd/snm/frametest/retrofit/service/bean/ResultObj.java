package cn.gd.snm.frametest.retrofit.service.bean;

public class ResultObj {
    private String area_name;
    private String cid;
    private String code;
    private String dureation;

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDureation() {
        return dureation;
    }

    public void setDureation(String dureation) {
        this.dureation = dureation;
    }

    @Override
    public String toString() {
        return "ResultObj{" +
                "area_name='" + area_name + '\'' +
                ", cid='" + cid + '\'' +
                ", code='" + code + '\'' +
                ", dureation='" + dureation + '\'' +
                '}';
    }
}
