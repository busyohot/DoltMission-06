
package com.busyo.yeonho.doltmission_06;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
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



    TextView tv;
    ViewPager pager;
    WebPage page;
    EditText et;
    Button bt;
    public int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰페이저 객체를 참조하고 어댑터를 설정합니다.
        pager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        pager.setAdapter(adapter);

        tv = (TextView)findViewById(R.id.text);
        et = (EditText)findViewById(R.id.inputurl);

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
        translatednAnim = AnimationUtils.loadAnimation(this, R.anim.translate_dn);
        translateupAnim = AnimationUtils.loadAnimation(this, R.anim.translate_up);

        // 애니메이션 객체에 리스너 설정
        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translatednAnim.setAnimationListener(animListener);
        translateupAnim.setAnimationListener(animListener);


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

    /**
     * 뷰페이저를 위한 어댑터 정의
     */
    public class ViewPagerAdapter extends PagerAdapter {
        String[] url = { "http://m.naver.com", "http://m.daum.net", "http://login.hanssem.com" };

        /**
         * Context 객체
         */


        private Context mContext;

        /**
         * 초기화
         *
         * @param context
         */
        public ViewPagerAdapter( Context context ) {
            mContext = context;
        }

        /**
         * 페이지 갯수
         */
        public int getCount() {
            return url.length;
        }

        /**
         * 뷰페이저가 만들어졌을 때 호출됨
         */
        public Object instantiateItem(ViewGroup container, int position) {
            // create a instance of the page and set data
            page = new WebPage(mContext);


            page.setUrl(url[position]);
                    // 컨테이너에 추가
            container.addView(page, position);

            return page;
        }

        /**
         * Called to remove the page
         */
        public void destroyItem(ViewGroup container, int position, Object view) {


            container.removeView((View) view);
        }

        public boolean isViewFromObject(View view, Object object) {
            i=pager.getCurrentItem();
            tv.setText(" " + (i+1)+" page URL: " + url[i]);



            bt = (Button)findViewById(R.id.button);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!et.getText().toString().substring(0,7).toLowerCase().equals("http://"))
                    {
                        url[i]="http://" + et.getText().toString();

                    }
                    else
                    {
                        url[i]=et.getText().toString();

                    }
                    tv.setText(" " + (i + 1) + " page URL: " + url[i]);

                    page.setUrl(url[i]);



                }
            });

            return view.equals(object);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
