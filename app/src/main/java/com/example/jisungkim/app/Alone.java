package com.example.jisungkim.app;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Alone extends AppCompatActivity implements OnMapReadyCallback {
    ArrayList<MyItem> arItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alone);

        //맵 가져오는 코드
        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent= getIntent();
        String text=intent.getStringExtra("a1");
        String text2=intent.getStringExtra("a2");
        String text3=intent.getStringExtra("a3");
        String text4=intent.getStringExtra("a4");

        //MyItem에 알파벳, 식당명, 가격
        arItem=new ArrayList<MyItem>();
        MyItem mi;
        mi=new MyItem("A",text,"2인 2만원대");arItem.add(mi);
        mi=new MyItem("B",text2,"2인 1만원대");arItem.add(mi);
        mi=new MyItem("C",text3,"2인 2만원대");arItem.add(mi);
        mi=new MyItem("D",text4,"2인 3만원대");arItem.add(mi);
        MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.list,arItem);

        final ListView MyList;
        MyList = (ListView) findViewById(R.id.list_item);
        MyList.setAdapter(MyAdapter);

        MyList.setOnItemClickListener(MyAdapter.mItemClickListener);
    }

    //초기 지도 핀 위치
    @Override
    public void onMapReady(final GoogleMap map) {
        LatLng SEOUL = new LatLng(37.56, 126.97);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        map.addMarker(markerOptions);
        map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));
    }

    //상세페이지로 전환
    public void mmOnClick(View v){
        ImageButton btn = (ImageButton)findViewById(R.id.btn);
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://store.naver.com/restaurants/detail?id=1630366010"));
        startActivity(intent);
    }

    //별점순, 거리순, 가격순으로 재배열
    public void mOnClick(View v){
        Intent intent= getIntent();
        String text=intent.getStringExtra("a1");
        String text2=intent.getStringExtra("a2");
        String text3=intent.getStringExtra("a3");
        String text4=intent.getStringExtra("a4");

        arItem=new ArrayList<MyItem>();
        MyItem mi;
        mi=new MyItem("A",text,"2인 2만원대");arItem.add(mi);
        mi=new MyItem("B",text2,"2인 1만원대");arItem.add(mi);
        mi=new MyItem("C",text3,"2인 4만원대");arItem.add(mi);
        mi=new MyItem("D",text4,"2인 6만원대");arItem.add(mi);

        MyListAdapter MyAdapter = new MyListAdapter(this, R.layout.list,arItem);
        ListView MyList;
        MyList=(ListView) findViewById(R.id.list_item);
        MyList.setAdapter(MyAdapter);

        switch(v.getId()){
            case R.id.rankbtn:
                arItem=new ArrayList<MyItem>();
                MyItem mii;
                mii=new MyItem("A",text2,"2인 3만원대");arItem.add(mii);
                mii=new MyItem("B",text,"2인 2만원대");arItem.add(mii);
                mii=new MyItem("C",text4,"2인 1만원대");arItem.add(mii);
                mii=new MyItem("D",text3,"2인 2만원대");arItem.add(mii);

                MyListAdapter MyAdapter1 = new MyListAdapter(this, R.layout.list,arItem);
                ListView MyList1;
                MyList1=(ListView) findViewById(R.id.list_item);
                MyList1.setAdapter(MyAdapter1);
                break;

            case R.id.lengthbtn:
                arItem=new ArrayList<MyItem>();
                MyItem mi2;
                mi2=new MyItem("A",text3,"2인 5만원대");arItem.add(mi2);
                mi2=new MyItem("B",text,"2인 3만원대");arItem.add(mi2);
                mi2=new MyItem("C",text2,"2인 1만원대");arItem.add(mi2);
                mi2=new MyItem("D",text4,"2인 2만원대");arItem.add(mi2);

                MyListAdapter MyAdapter2 = new MyListAdapter(this, R.layout.list,arItem);
                ListView MyList2;
                MyList2=(ListView) findViewById(R.id.list_item);
                MyList2.setAdapter(MyAdapter2);
                break;

            case R.id.pricebtn:
                arItem=new ArrayList<MyItem>();
                MyItem mi3;
                mi3=new MyItem("A",text3,"2인 3만원대");arItem.add(mi3);
                mi3=new MyItem("B",text4,"2인 6만원대");arItem.add(mi3);
                mi3=new MyItem("C",text,"2인 2만원대");arItem.add(mi3);
                mi3=new MyItem("D",text2,"2인 2만원대");arItem.add(mi3);

                MyListAdapter MyAdapter3 = new MyListAdapter(this, R.layout.list,arItem);
                ListView MyList3;
                MyList3=(ListView) findViewById(R.id.list_item);
                MyList3.setAdapter(MyAdapter3);
                break;
        }
    }
}
