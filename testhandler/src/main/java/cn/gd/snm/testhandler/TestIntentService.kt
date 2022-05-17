package cn.gd.snm.testhandler

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 * 多个异步实现同步。
 *
 */
class TestIntentService: IntentService("test123") {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("TestIntentService","onHandleIntent...")

        var taskName = intent!!.extras!!.get("taskName")

        when(taskName){
            "task1" ->
                Log.d("TestIntentService","task1...")

            "task2" ->
                Log.d("TestIntentService","task2...")

            else ->
                Log.d("TestIntentService","other...")

        }

    }
}