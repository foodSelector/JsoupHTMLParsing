package com.example.jisungkim.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parsing extends AppCompatActivity {

    TextView name = (TextView)findViewById(R.id.name);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing);

        ResConnection resConnection = new ResConnection();
        AsyncTask<String, String, String> result = resConnection.execute("","");
        System.out.println("RESULT");

        try {
            String msg = result.get(); //ResConnection의 리턴결과 get방식으로 얻어옴
            System.out.println(msg);
            name.setText(msg.toString());
        } catch (Exception e){}
    }

    public class ResConnection extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            // Jsoup을 이용한 식당데이터 Pasing하기.
            try{
                String path = "https://store.naver.com/restaurants/list?filterId=s11556056&query=성신여대%20맛집";
                Document document = Jsoup.connect(path).get();
                Elements elements = document.select("a[class=name]");
                System.out.println(elements);
                Element targetElement = elements.get(1);
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