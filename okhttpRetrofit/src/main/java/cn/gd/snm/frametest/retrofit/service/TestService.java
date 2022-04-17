package cn.gd.snm.frametest.retrofit.service;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

import cn.gd.snm.frametest.retrofit.service.bean.Result;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 注解：
 * @Get ：用于声明当前请求类型。
 *  1. get/post请求。
 *  2. 方法上一行声明。
 *
 * @Path : 用于动态声明后缀不同的路径。
 *  1. 如果地址中，仅仅只是请求最后的目录格式不一样，则可以支持使用@Path，路径由外部传入。
 *  2. 如果路径相同，仅仅只是body不同，则可以在base中直接写死，不需要@Path注解。
 *  3. 方法参数中声明。
 *  4. 注意：仅仅只支持url路径，不支持请求参数的配置，例如：users?sortby={sortby}
 *
 * @Query，@QueryMap ：用于请求时以普通方式传参。
 *  1. get/post方法中，参数的申明。
 *  2. 方法参数中申明。
 *  3. 前者用于参数较少场景，以方法参数传入。后者用于多数参数场景，将参数添加在map中一次传入。
 *
 * @Body ：以json的方式传递参数。
 *  1. 在方法参数中声明。
 *
 * @FormUrlEncoded + @Field：以表单的方式提交参数。
 *  1. FormUrlEncoded在方法上一行声明，Field在方法参数中申明。
 *
 *
 *
 */
public interface TestService {
    //TODO:第一种注解方式。
    @GET("epgol/{page}")
    Call<ResponseBody> getTest(@Path("page") String page, @QueryMap Map<String,String>map);

    //TODO:第二种注解方式。
    @HTTP(method = "GET",path = "epgol/{page}")
    Call<ResponseBody> getTest2(@Path("page") String page, @QueryMap Map<String,String>map);

    @GET("epgol/{page}")
    Call<ResponseBody> getTestByPar(@Path("page") String page,
                                    @Query("source") String source,
                                    @Query("productline") String productline,
                                    @Query("channel") String channel,
                                    @Query("ifver") String ifver,
                                    @Query("code") String code);


    //TODO:测试path不使用传值方式，直接在申明处写死。
    @GET("epgol/getsimpledetail")
    Call<ResponseBody> getTest3( @QueryMap Map<String,String>map);


    /**
     * Post相关。
     */

    //TODO:测试body使用对象的方式传入,接受使用ResponseBody接受
    @POST("launcher/{code}")
    Call<ResponseBody> postTestObjToResp(@Path("code") String code, @Body User user);

    //TODO:测试body使用对象的方式传入,接受使用对象接收
    @POST("launcher/{code}")
    Call<Result> postTestObjToObj(@Path("code") String code, @Body User user);

    //TODO:测试使用JS的方式传入，使用对象接受。
    @POST("launcher/{code}")
    Call<Result> postTestByJs(@Path("code") String code, @Body JSONObject jsonObject);



}
