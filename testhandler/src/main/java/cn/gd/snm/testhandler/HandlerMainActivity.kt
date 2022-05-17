package cn.gd.snm.testhandler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import kotlin.concurrent.thread

class HandlerMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_main)

//        testHandler()

//        testHandlerLooper()

        testIntentService()
    }

    private fun testIntentService() {
        val intent = Intent(this, TestIntentService::class.java)
        val bundle = Bundle()
        bundle.putString("taskName", "task1")
        intent.putExtras(bundle)
        startService(intent)


        val intent2 = Intent(this, TestIntentService::class.java)
        val bundle2 = Bundle()
        bundle.putString("taskName", "task2")
        intent.putExtras(bundle)
        startService(intent)


    }


    private fun testHandlerLooper() {
        thread {
            var handler = Handler(Looper.myLooper()!!)
            Looper.prepare()

            Looper.loop()
        }
    }



    private fun testHandler() {
        //todo 切换主线程执行。
        var handler = Handler(Looper.getMainLooper())
        handler.post{
            //要在主线程执行的代码。
        }

        //todo 延迟一秒执行
        handler.postDelayed({

        },1000)

        //todo 根据不同的意图，处理不同的事情。
        var mes = Message()
        mes.arg1 = 1
        mes.what = 100
        handler.handleMessage(mes)

        //todo 以下两种方式都可以初始化一个msg，但优先应该使用obtain的方式，
        // 复用同一个msg在大量消息时，可以防止内存抖动。
        var ha2 = Message()
        ha2.what = 1

        var ha1 = handler.obtainMessage()  //会有一个pool，缓存的msg
    }

    /**
     * 自定义Handler
     */
    inner class MyHandler(looper:Looper):Handler(looper){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            testHandler()

            when(msg.what){
                1,2,3 ->
                    Log.d("test","123")
                4,5,6 ->
                    Log.d("test","456")
                else ->
                    Log.d("test","other")
            }
        }
    }
}