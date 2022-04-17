package cn.gd.snm.testwebview;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class ComWebChromeClient extends WebChromeClient {
    private static final String TAG = ComWebChromeClient.class.getSimpleName();

    /**
     * 显示网页title回调。
     * @param view
     * @param title
     */
    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        Log.d(TAG,"darren:title=" + title);
    }

    /**
     * 输出H5相关打印。
     * @param consoleMessage
     * @return
     */
    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        // Call the old version of this function for backwards compatability.
        Log.d(TAG, consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
