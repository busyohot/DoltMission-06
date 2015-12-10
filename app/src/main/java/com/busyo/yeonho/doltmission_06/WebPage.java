package com.busyo.yeonho.doltmission_06;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class WebPage extends LinearLayout {
    /**
     * 페이지가 열려 있는지 알기 위한 플래그
     */
    boolean isPageOpen = false;

    /**
     * 애니메이션 객체
     */
    Animation translatednAnim;
    Animation translateupAnim;

    /**
     * 슬라이딩으로 보여지는 페이지 레이아웃
     */
    LinearLayout slidingPage;

    /**
     * 열고닫는버튼
     */
    Button openbt;

    Context mContext;
    Button bt;
    WebView webview;
    EditText et;
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

        //tv.setText(" " + (i+1)+" page URL: " + url[i]);

        et =(EditText)findViewById(R.id.inputurl);

        bt = (Button)findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!et.getText().toString().substring(0, 7).toLowerCase().equals("http://")) {
                    webview.loadUrl("http://" + et.getText().toString());


                } else {
                    webview.loadUrl(et.getText().toString());

                }
                //tv.setText(" " + (i + 1) + " page URL: " + url[i]);
                //pager.setAdapter(page, setUrl);




            }
        });












        openbt = (Button) findViewById(R.id.open);
        openbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 애니메이션 적용
                if (isPageOpen) {
                    slidingPage.setVisibility(View.GONE);
                    slidingPage.startAnimation(translateupAnim);
                } else {
                    slidingPage.setVisibility(View.VISIBLE);
                    slidingPage.startAnimation(translatednAnim);
                }
            }
        });
        // 슬라이딩으로 보여질 레이아웃 객체 참조
        slidingPage = (LinearLayout) findViewById(R.id.sliding);

        // 애니메이션 객체 로딩
        translatednAnim = AnimationUtils.loadAnimation(mContext, R.anim.translate_dn);
        translateupAnim = AnimationUtils.loadAnimation(mContext, R.anim.translate_up);

        // 애니메이션 객체에 리스너 설정
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translatednAnim.setAnimationListener(animListener);
        translateupAnim.setAnimationListener(animListener);





        webview = (WebView)findViewById(R.id.webview);


        // 웹뷰 설정 정보
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebWebChromeClient());
        webview.setWebViewClient(new WebWebViewClient());
        //tv = (TextView)findViewById(R.id.webtext);




    }

    /**
     * 애니메이션 리스너 정의
     */
    private class SlidingPageAnimationListener implements Animation.AnimationListener {
        /**
         * 애니메이션이 끝날 때 호출되는 메소드
         */
        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                openbt.setText("내리기");
                isPageOpen = false;

            } else {
                openbt.setText("올리기");
                isPageOpen = true;
            }
        }

        public void onAnimationRepeat(Animation animation) {

        }

        public void onAnimationStart(Animation animation) {

        }

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
