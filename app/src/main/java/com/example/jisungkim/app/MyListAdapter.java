package com.example.jisungkim.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wldnj on 2018-01-13.
 */

public class MyListAdapter extends BaseAdapter {
    Context maincon;
    LayoutInflater Inflater;
    ArrayList<MyItem> arSrc;
    int layout;
    ListView MyList;
    int count=0;

    public MyListAdapter(Context context, int alayout, ArrayList<MyItem> aarSrc){
        maincon=context;
        Inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arSrc=aarSrc;
        layout=alayout;
    }
    public int getCount(){
        return arSrc.size();
    }
    public String getItem(int position){
        return arSrc.get(position).name;
    }
    public String getItem2(int position){return arSrc.get(position).alphabet;}
    public long getItemId(int position){
        return position;
    }

   public View getView(int position, View convertView, ViewGroup parent){
        final int pos=position;
        if(convertView==null)
        {
            convertView= Inflater.inflate(layout,parent,false);
        }
        TextView txt=(TextView)convertView.findViewById(R.id.text);
        TextView txt2=(TextView)convertView.findViewById(R.id.alpha);
        TextView txt3=(TextView)convertView.findViewById(R.id.text2);
        txt2.setText(arSrc.get(position).alphabet);
        txt.setText(arSrc.get(position).name);
        txt3.setText(arSrc.get(position).name2);

        /*MyList=(ListView)convertView.findViewById(R.id.list_item);
        MyList.setOnItemClickListener(mItemClickListener);*/
        return convertView;
    }
    final AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ImageButton btn=(ImageButton)view.findViewById(R.id.btn);
            btn.setVisibility(View.VISIBLE);
        }
    };
}

