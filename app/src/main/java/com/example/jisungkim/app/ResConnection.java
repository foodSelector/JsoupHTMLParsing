package com.example.jisungkim.app;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//Jsoup으로 웹파싱
public class ResConnection extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... strings) {
        // Jsoup을 이용한 맛집 데이터 Parsing하기 try
        try{
            //성신여대 맛집,데이트맛집,가족맛집,친구맛집 조회url
            String path = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=성신여대+맛집";
            String date_path = "https://store.naver.com/restaurants/list?context=1&filterId=s11556056&query=성신여대%20맛집";
            String family_path = "https://store.naver.com/restaurants/list?context=11&filterId=s11556056&query=성신여대%20맛집";
            String friend_path = "https://store.naver.com/restaurants/list?context=1&filterId=s11556056&query=성신여대%20맛집";

            Document document = Jsoup.connect(path).get();
            Elements elements = document.select("span");
            System.out.println(elements);
            Element targetElement = elements.get(122);
            String text = targetElement.text();
            System.out.println(text);
            return text;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
