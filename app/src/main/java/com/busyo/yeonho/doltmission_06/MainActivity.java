
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
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {




    //TextView tv;
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

        //tv = (TextView)findViewById(R.id.text);
        et = (EditText)findViewById(R.id.inputurl);




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

        public boolean isViewFromObject(View view, final Object object) {



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
