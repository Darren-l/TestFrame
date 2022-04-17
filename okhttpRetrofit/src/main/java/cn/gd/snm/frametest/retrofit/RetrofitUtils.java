package cn.gd.snm.frametest.retrofit;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import cn.gd.snm.frametest.retrofit.service.TestService;
import cn.gd.snm.frametest.retrofit.service.User;
import cn.gd.snm.frametest.retrofit.service.bean.Result;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//地址：http://epg.launcher.aisee.tv/epgol/getsimpledetail?source=snmott&productline=lanxu&channel=10401&ifver=v6&code=mzc00200zfxryzs
//地址：http://recommend.launcher.aisee.tv/intelRecom/api/launcher/getVideoByCode
//{"featuredSize":12,"ifver":"v6","code":"mzc002002z6j5hi","type":"10"}
public class RetrofitUtils {

    public static final String DETAILRECOMMEND = "http://epg.launcher.aisee.tv/";
    public static final String DETAILRECOMMEND2 = "http://recommend.launcher.aisee.tv/intelRecom/api/";
    public static final String DETAILRECOMMEND3 = "http://epg.launcher.aisee.tv/";
    private static final String TAG = RetrofitUtils.class.getSimpleName();

    private Retrofit retrofit;
    private TestService testService;
    public void init(){

        //TODO:baseUrl必须为/结尾否则会报错，不能包含子路径，会被删除。子路径应放置到service的path中声明。
        retrofit = new Retrofit.Builder().baseUrl(DETAILRECOMMEND).build();
        testService = retrofit.create(TestService.class);

        //TODO：get请求，测试使用map的方式添加body。ok
//        testAddbodyByMap();

        //TODO:get请求，测试使用方法参数的方式添加body。ok
//        testAddBodyByPar();

        //TODO：测试path不使用传值方式，需要在service的path中写死。ok
//        test3Get();

        //TODO:get请求，添加header。


        //TODO:Post请求，测试请求body传入对象，返回ResponeBody。ok
        testPost();

        //TODO:Post请求，测试请求body传入Js，返回ResponeBody。fail
        testPost2();

        //TODO：post请求，测试请求body传入对象，返回为对象。ok
        testPost3();

        //TODO:Post请求添加头。

    }

    private void testPost() {
        Retrofit retrofit2 = new Retrofit.Builder().baseUrl(DETAILRECOMMEND2).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        TestService testService2 = retrofit2.create(TestService.class);
        User user = new User();
        user.setCode("mzc002002z6j5hi");
        user.setFeaturedSize(12);
        user.setIfver("v6");
        user.setType("10");
        Call<ResponseBody> call = testService2.postTestObjToResp("getVideoByCode",user);
        Log.d(TAG,"request=" + call.request());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG,"darren test-->response=" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"darren test-->onFailure=" + t.getMessage());
            }
        });
    }

    private void testPost2() {
        Retrofit retrofit2 = new Retrofit.Builder().baseUrl(DETAILRECOMMEND2).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        TestService testService2 = retrofit2.create(TestService.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code","mzc002002z6j5hi");
            jsonObject.put("featuredSize",12);
            jsonObject.put("ifver","v6");
            jsonObject.put("type","10");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Call<Result> call = testService2.postTestByJs("getVideoByCode",jsonObject);

        Log.d(TAG,"request=" + call.request());

        call.enqueue(new Callback<Result>() {

            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d(TAG,"darren test-->response=" + response.body().toString());

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d(TAG,"darren test-->onFailure=" + t.getMessage());
            }
        });
    }


    private void testPost3() {
        Retrofit retrofit2 = new Retrofit.Builder().baseUrl(DETAILRECOMMEND2).
                addConverterFactory(GsonConverterFactory.create())
                .build();
        TestService testService2 = retrofit2.create(TestService.class);
        User user = new User();
        user.setCode("mzc002002z6j5hi");
        user.setFeaturedSize(12);
        user.setIfver("v6");
        user.setType("10");
        Call<Result> call = testService2.postTestObjToObj("getVideoByCode",user);
        Log.d(TAG,"request=" + call.request());
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Log.d(TAG,"darren test-->response=" + response.body().toString());
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d(TAG,"darren test-->onFailure=" + t.getMessage());
            }
        });
    }

    private void test3Get() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(DETAILRECOMMEND3).build();
        TestService testService = retrofit.create(TestService.class);
        Map<String,String> map = new HashMap<>();
        map.put("source","snmott");
        map.put("productline","lanxu");
        map.put("channel","10401");
        map.put("ifver","v6");
        map.put("code","mzc00200zfxryzs");
        Call<ResponseBody> call = testService.getTest3(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG,"darren test-->response=" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"darren test-->onFailure=" + t.getMessage());
            }
        });
    }

    private void testAddBodyByPar() {
        Call<ResponseBody> call = testService.getTestByPar("getsimpledetail",
                "snmott",
                "lanxu","10401","v6","mzc00200zfxryzs");
        Log.d(TAG,"request=" + call.request());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG,"darren test-->response=" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"darren test-->onFailure=" + t.getMessage());
            }
        });
    }

    private void testAddbodyByMap() {
        Map<String,String> map = new HashMap<>();
        map.put("source","snmott");
        map.put("productline","lanxu");
        map.put("channel","10401");
        map.put("ifver","v6");
        map.put("code","mzc00200zfxryzs");
        Call<ResponseBody> call = testService.getTest("getsimpledetail",map);
        //TODO:获取当次请求具体信息。
        Log.d(TAG,"request=" + call.request());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG,"darren test-->response=" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG,"darren test-->onFailure=" + t.getMessage());
            }
        });
    }

}
