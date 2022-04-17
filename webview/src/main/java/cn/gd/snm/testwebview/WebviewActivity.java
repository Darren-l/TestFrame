package cn.gd.snm.testwebview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebViewClient;

import cn.gd.snm.testwebview.databinding.ActivityWebviewBinding;

public class WebviewActivity extends AppCompatActivity {

    private static final String TAG = WebviewActivity.class.getSimpleName();
    private ActivityWebviewBinding databind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_webview);
        databind = DataBindingUtil.setContentView(this,R.layout.activity_webview);

        //TODO:测试webview与js的交互。
//        initWebViewTestHtml();

        //TODO:测试请求百度。
        initWebviewTestBaidu();
    }

    /**
     * 测试自定义的view与js的交互。
     */
    private void initWebViewTestHtml() {
        Log.d(TAG,"darren,initWebViewTestHtml...");
        databind.webview.setWebViewClient(new CommonWebViewClient());
        databind.webview.setWebChromeClient(new ComWebChromeClient());
        databind.webview.loadUrl("file:///android_asset/demo.html");

        databind.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
//                testCallJsMenthod();
                testCallJsMenthod2();
            }
        });
    }

    /**
     * 测试android调用js带参数。
     */
    private void testCallJsMenthod2() {
        String message = "hello android";
        databind.webview.loadUrl("javascript:test2('"+message+"')");
    }

    /**
     * 测试android调用js方法。
     */
    private void testCallJsMenthod() {
        databind.webview.loadUrl("javascript:test()");
    }

    /**
     * 测试百度。
     */
    private void initWebviewTestBaidu() {
        databind.webview.getSettings().setJavaScriptEnabled(true);
        databind.webview.setWebViewClient(new CommonWebViewClient());
        databind.webview.setWebChromeClient(new ComWebChromeClient());
        databind.webview.loadUrl("https://www.baidu.com");
    }
}
