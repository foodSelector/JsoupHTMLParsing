package com.example.jisungkim.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ResConnection resConnection = new ResConnection();
    String msg;

    @BindView(R.id.alone) LinearLayout alone;
    @BindView(R.id.date)   LinearLayout date;
    @BindView(R.id.friend)     LinearLayout friend;
    @BindView(R.id.family)     LinearLayout family;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        alone.setOnClickListener(this);
        date.setOnClickListener(this);
        friend.setOnClickListener(this);
        family.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        AsyncTask<String, String, String> result = resConnection.execute("","");
        System.out.println("RESULT");

        try {
            msg = result.get();
            System.out.println(msg);
//            name.setText(msg.toString());
        } catch (Exception e){}

        Intent i;

        switch (view.getId()) {
            case R.id.alone:
                i = new Intent(this, Alone.class);
                i.putExtra("a1",msg);
                i.putExtra("a2","혼밥의 고수");
                i.putExtra("a3","오늘도나혼자밥");
                i.putExtra("a4","혼밥혼밥");
                startActivity(i);
                break;
            case R.id.date:
                i = new Intent(MainActivity.this, Date.class);
                i.putExtra("a1","밀당의 고수");
                i.putExtra("a2","연애의 고수");
                i.putExtra("a3","내이상형은 고수");
                i.putExtra("a4","쌀국수에 고수");
                startActivity(i);
                break;
            case R.id.friend:
                i = new Intent(MainActivity.this, Friend.class);
                i.putExtra("a1","오늘도너랑");
                i.putExtra("a2","어제도너였는데");
                i.putExtra("a3","내일도너일듯");
                i.putExtra("a4","쌀국수에 고수");
                startActivity(i);
                break;
            case R.id.family:
                i = new Intent(MainActivity.this, Family.class);
                i.putExtra("a1","패밀리레스토랑");
                i.putExtra("a2","아빠카드찬스");
                i.putExtra("a3","엄마카드찬스");
                i.putExtra("a4","가족끼리끼리");
                startActivity(i);
                break;
        }
    }

    //Jsoup으로 웹 파싱
    public class ResConnection extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {

            // Jsoup을 이용한 맛집 데이터 Parsing하기 try
            try{
                //성신여대 맛집,데이트맛집,가족맛집,친구맛집 조회결과
                String path = "https://store.naver.com/restaurants/list?filterId=s11556056&query=성신여대%20맛집";
                String date_path = "https://store.naver.com/restaurants/list?context=1&filterId=s11556056&query=성신여대%20맛집";
                String family_path = "https://store.naver.com/restaurants/list?context=11&filterId=s11556056&query=성신여대%20맛집";
                String friend_path = "https://store.naver.com/restaurants/list?context=1&filterId=s11556056&query=성신여대%20맛집";
                Document document = Jsoup.connect(path).get();
                Elements elements = document.select("span");
                System.out.println(elements);
                Element targetElement = elements.get(3);
                String text = targetElement.text();
                System.out.println(text);
                return text;
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
