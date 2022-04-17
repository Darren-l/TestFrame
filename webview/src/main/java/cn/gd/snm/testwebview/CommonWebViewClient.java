package cn.gd.snm.testwebview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CommonWebViewClient extends WebViewClient {
    private static final String TAG = CommonWebViewClient.class.getSimpleName();
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.d(TAG,"darren:onPageFinished...");
    }
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.d(TAG,"darren:onPageStarted...");
    }
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
        Log.d(TAG,"darren:onReceivedSslError...");
    }
}
