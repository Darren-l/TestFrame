package cn.gd.snm.testwebview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Map;

public class BaseWebView extends WebView {
    private static final String TAG = BaseWebView.class.getSimpleName();

    public BaseWebView(Context context) {
        super(context);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    //TODO:在初始化中，配置webview的settings，并标记name，供js页面查找。
    private void init() {
        Log.d(TAG,"darren...BaseWebView,init...");
        WebViewDefaultSettings.getInstance().setSettings(this);
        addJavascriptInterface(this, "baseWebView");
    }


    /**
     * 接受js的调用，并打印js传过来的字符串。
     * @param jsParam
     */
    @JavascriptInterface
    public void takeNativeAction(final String jsParam){
        Log.i(TAG, jsParam);
        if(!TextUtils.isEmpty(jsParam)) {
            final JsParam jsParamObject = new Gson().fromJson(jsParam, JsParam.class);
            if(jsParamObject != null) {
                if("showToast".equalsIgnoreCase(jsParamObject.name)) {
                    Toast.makeText(getContext(), String.valueOf(new Gson().
                            fromJson(jsParamObject.param, Map.class).get("message")),
                            Toast.LENGTH_LONG).show();
                }
            }
        }

    }

}
