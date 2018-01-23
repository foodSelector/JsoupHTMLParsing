package com.example.jisungkim.app;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Friend extends AppCompatActivity implements OnMapReadyCallback {
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
}
