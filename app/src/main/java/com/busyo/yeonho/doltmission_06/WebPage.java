package com.busyo.yeonho.doltmission_06;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class WebPage extends LinearLayout {
    Context mContext;

    WebView webview;
    //TextView tv;


    public WebPage(Context context) {
        super(context);

        init(context);
    }

    public WebPage(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        mContext = context;

        // inflate XML layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.webpage, this, true);

        webview = (WebView)findViewById(R.id.webview);


        // 웹뷰 설정 정보
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebWebChromeClient());
        webview.setWebViewClient(new WebWebViewClient());
        //tv = (TextView)findViewById(R.id.webtext);




    }
    public void setUrl(String url) {

        webview.loadUrl(url.toString());
        //tv.setText(url.toString());
    }
    final class WebWebViewClient extends WebViewClient {
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d("WebPage", message);
            result.confirm();

            return true;
        }
    }
    final class WebWebChromeClient extends WebChromeClient {
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d("WebPage", message);
            result.confirm();

            return true;
        }
    }






}
