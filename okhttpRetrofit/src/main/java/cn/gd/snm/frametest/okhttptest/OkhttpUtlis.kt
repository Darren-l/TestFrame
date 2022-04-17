package cn.gd.snm.frametest.okhttptest

import android.os.Handler
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * Okhttp请求方式有两种：
 *  1. 同步请求。
 *  2. 异步请求。
 *
 * Okhttp使用方式：
 *  1. 通过build模式，获取客户端对象，该对象用于设置连接参数。
 *  2. Request.Builder()获取请求体对象，用于设置请求相关信息。
 *  3. newCall的方式发起请求。
 *
 */
class OkhttpUtlis private constructor(){

    companion object{
        val instance:OkhttpUtlis by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkhttpUtlis()
        }
    }

    private lateinit var handler:Handler
    private lateinit var client:OkHttpClient

    /**
     * 初始化Okhttp。
     *
     */
    fun initOkhttp(){
        handler = Handler()

        client = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    /**
     * 使用异步的方式，get请求。
     *
     */
    fun doGetAsyn(url: String, networkCallback: (isSuccess: Boolean, content: String?) -> Unit){
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                handler.post {
                    networkCallback(false, null)
                }
            }
            override fun onResponse(call: Call, response: Response) {
                handler.post {
                    if (response.isSuccessful) {
                        handler.post {
                            networkCallback(true, response.body()?.toString())
                        }
                    }
                }
            }
        })
    }

    /**
     * 同步get请求。
     *
     */
    fun doGetSyn(url: String, content: String):String{
        var request = Request.Builder().url(url).build()
        var response = client.newCall(request).execute()

        return response.body().toString()
    }

    /**
     * 异步post请求
     *
     */
    fun doPostAsyn(url: String, map: Map<String, String>, networkCallback:
        (isSuccess: Boolean, content: String?) -> Unit) {
        val builder = FormBody.Builder()
        for ((key, value) in map) {
            builder.add(key, value)
        }
        val request = Request.Builder()
            .url(url)
            .post(builder.build())
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                handler.post {
                    networkCallback(false,null)
                }
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.body() != null && response.isSuccessful) {
                    handler.post {
                        networkCallback(true,response.body().toString())
                    }
                } else {
                    networkCallback(false,null)

                }
            }
        })
    }


    /**
     * 异步请求get。
     *
     */
    fun doPostSyn(url: String, map: Map<String, String>,
                  networkCallback: (isSuccess: Boolean, content: String?) -> Unit):String{
        val builder = FormBody.Builder()
        for ((key, value) in map) {
            builder.add(key, value)
        }
        val request = Request.Builder()
            .url(url)
            .post(builder.build())
            .build()
        var resp = client.newCall(request).execute()

        return resp.body().toString()
    }


}