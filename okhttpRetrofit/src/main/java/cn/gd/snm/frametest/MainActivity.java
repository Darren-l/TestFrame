package cn.gd.snm.frametest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import cn.gd.snm.frametest.retrofit.RetrofitUtils;
import cn.gd.snm.frametest.retrofit.ToastUtlis;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                testRetrofit();
                testToast();
            }
        });
    }

    private void testToast() {
        ToastUtlis.toast(MainActivity.this,"1");
        ToastUtlis.toast(MainActivity.this,"2");
        ToastUtlis.toast(MainActivity.this,"3");
        ToastUtlis.toast(MainActivity.this,"4");
    }

    private void testRetrofit() {
        //TODO: 测试RetrofitUtils
        RetrofitUtils retrofitUtils =  new RetrofitUtils();
        retrofitUtils.init();
    }


    /**
     * 系统按键事件分发从act——》view
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        //TODO:按下上键,这个重写方法能收到遥控器的键值。

        if(event.getAction() == KeyEvent.ACTION_DOWN){
            //TODO:此时遥控器是按下的状态。
            if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
                //TODO:此时遥控器按的是返回键。
                return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }
}
