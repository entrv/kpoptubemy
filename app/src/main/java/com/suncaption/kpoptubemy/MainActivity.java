package com.suncaption.kpoptubemy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.suncaption.kpoptubemy.adapter.TabPagerAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Elements contents;
    Document doc = null;
    private Realm realm;
    private TabLayout mTabLayout;

    private ViewPager mViewPager;

    private TabPagerAdapter mTabPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.layout_tab);
        mTabLayout.addTab(mTabLayout.newTab().setText("ALL"));
        for (int i = 1; i <= 10; i++) {
            Integer channelId = getResources().getIdentifier(
                    "channel" + i, "string", getPackageName());
            Integer urlId = getResources().getIdentifier("url" + i ,
                    "string", getPackageName());

            String channel_name = getString(channelId);
            String url_name = getString(urlId);
            mTabLayout.addTab(mTabLayout.newTab().setText(channel_name));
        }


        mViewPager = (ViewPager) findViewById(R.id.pager_content);

        mTabPagerAdapter = new TabPagerAdapter(

                getSupportFragmentManager(), mTabLayout.getTabCount());

        mViewPager.setAdapter(mTabPagerAdapter);



        mViewPager.addOnPageChangeListener(

                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override

            public void onTabSelected(TabLayout.Tab tab) {

                mViewPager.setCurrentItem(tab.getPosition());

            }



            @Override

            public void onTabUnselected(TabLayout.Tab tab) {



            }



            @Override

            public void onTabReselected(TabLayout.Tab tab) {



            }

        });



        checkFirstRun();
    }

    public final void checkFirstRun() {
        SharedPreferences sf = getSharedPreferences("Pref", MODE_PRIVATE);
        if (sf.getBoolean("isFirstRun", true)) {
            System.out.println("FirstRun!");
            new Thread(new checkFirstRun1(this)).start();
            /*new Thread(new MainActivity$checkFirstRun$2(this)).start();
            new Thread(new MainActivity$checkFirstRun$3(this)).start();
            new Thread(new MainActivity$checkFirstRun$4(this)).start();
            new Thread(new MainActivity$checkFirstRun$5(this)).start();
            new Thread(new MainActivity$checkFirstRun$6(this)).start();
            new Thread(new MainActivity$checkFirstRun$7(this)).start();
            new Thread(new MainActivity$checkFirstRun$8(this)).start();
            new Thread(new MainActivity$checkFirstRun$9(this)).start();
            new Thread(new MainActivity$checkFirstRun$10(this)).start();*/
        }
        //sf.edit().putBoolean("isFirstRun", false).apply();

    }

    class checkFirstRun1 implements Runnable {
        MainActivity mainActivity = null;

        public checkFirstRun1(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        public final void run() {
            MainActivity mainActivity = this.mainActivity;
            String url = getString(R.string.url1);
            String channel = getString(R.string.channel1);
            mainActivity.insertFirstVideos(url, channel);

        }
    }

    private final void insertFirstVideos(String url, String channel) {
        realm = Realm.getDefaultInstance();

        try {
            doc = Jsoup.connect(url).get(); //naver페이지를 불러옴
            contents = doc.select("li.channels-content-item");//셀렉터로 span태그중 class값이 ah_k인 내용을 가져옴
            Log.d("entrv", "insertFirstVideos: ");

            List<String> myArrayList = new ArrayList<String>();
            for (Element pElem : contents) {
                myArrayList.add(pElem.html());
                Log.d("entrv", "aaaa: " + pElem.html());
            }
            Collections.sort(myArrayList);
            Log.d("entrv", "insertFirstVideos: ");






           /* Collections.sort(contents, new Comparator<Element>() {

                @Override

                public int compare(Element e1, Element e2) {

                    logger.info(">> e1.children.first = "+ e1.children()..first().text());

                    return e1.children().first().text().compareTo(e2.children().first().text());

                }

            });
*/

            // realm에 저장된 모든 문자대상 정보 가져오기
            final RealmResults<Videos> results = realm.where(Videos.class).findAll();

            // realm에 저장된 모든 문자대상 삭제하기
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    // 전체 맞는 데이터를 삭제합니다
                    results.deleteAllFromRealm();

                    //Log.d(TAG, "delete complete -> realm transaction 완료");
                    //Toast.makeText(context, "전체가 삭제되었습니다", Toast.LENGTH_SHORT).show();
                }
            });

            for (String elementstring : myArrayList) {
                String str = elementstring;
                Document element = Jsoup.parse(str);
                final String img = element.select("img").attr("src");
                final String title = element.select("a.yt-uix-sessionlink").attr("title");

                final String time = element.select("span.video-time").text();
                final String id = element.select("button.yt-uix-button").attr("data-video-ids");
                final String channelReal = channel;

                TimeZone tz = TimeZone.getDefault();


                Date date = new Date();
                //Long a = (Long.parseLong("1568463316476"));
                Timestamp ts = new Timestamp(date.getTime());
                final long date_long = ts.getTime();

                //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //System.out.println(formatter.format(ts));
                //System.out.println("1");
/*
                TimeZone time;
                Date date = new Date();
                DateFormat df = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss (z Z)");

                time = TimeZone.getTimeZone("Asia/Seoul");
                df.setTimeZone(time);
                System.out.format("%s%n%s%n%n", time.getDisplayName(),
                        df.format(date));
                */

                try {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {

                            Videos video = realm.createObject(Videos.class, id);

                            video.setTitle(title);
                            video.setChannel(channelReal);
                            video.setImg(img);
                            video.setTime(time);
                            video.setDate(date_long);


                            Log.d("entrv", "new save -> realm transaction 완료");
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "생성되었습니다.", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            });


                        }
                    });
                } catch(Exception e) {
                    e.printStackTrace();
                }




            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
