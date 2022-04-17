package cn.gd.snm.frametest.retrofit.service.bean;

import java.util.List;

public class Result {
    List<ResultObj> datas;
    public List<ResultObj> getResultObjList() {
        return datas;
    }
    public void setResultObjList(List<ResultObj> datas) {
        this.datas = datas;
    }
    @Override
    public String toString() {
        return "Result{" +
                "datas=" + datas +
                '}';
    }
}
