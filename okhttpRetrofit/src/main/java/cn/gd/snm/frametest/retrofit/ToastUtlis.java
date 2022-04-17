package cn.gd.snm.frametest.retrofit;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class ToastUtlis {
    public static void toast(final Context context, final String content){
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
