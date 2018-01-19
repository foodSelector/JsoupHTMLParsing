package com.example.jisungkim.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

public class Alone extends AppCompatActivity implements MapView.CurrentLocationEventListener, MapView.MapViewEventListener, MapView.POIItemEventListener {

    private static final String LOG_TAG = "LocationDemoActivity";
    private static final MapPoint DEFAULT_MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(37.5913103, 127.0199425);
    private static final MapPoint DEFAULT_MARKER_POINT2 = MapPoint.mapPointWithGeoCoord(37.591361, 127.019481);
    private static final MapPoint DEFAULT_MARKER_POINT3 = MapPoint.mapPointWithGeoCoord(37.588369, 127.022764);
    private static final MapPoint DEFAULT_MARKER_POINT4 = MapPoint.mapPointWithGeoCoord(37.591079, 127.027215);
    private MapView mMapView;
    private MapPOIItem mDefaultMarker;

    class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {
        private final View mCalloutBalloon;

        public CustomCalloutBalloonAdapter() {
            mCalloutBalloon = getLayoutInflater().inflate(R.layout.custom_callout_ballon, null);
        }

        @Override
        public View getCalloutBalloon(MapPOIItem poiItem) {
            ((ImageView) mCalloutBalloon.findViewById(R.id.badge)).setImageResource(R.drawable.ic_launcher);
            ((TextView) mCalloutBalloon.findViewById(R.id.title)).setText(poiItem.getItemName());
            ((TextView) mCalloutBalloon.findViewById(R.id.desc)).setText("별점OR거리OR가격");
            return mCalloutBalloon;
        }

        @Override
        public View getPressedCalloutBalloon(MapPOIItem poiItem) {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alone);

        mMapView = (MapView) findViewById(R.id.map_view);
        mMapView.setDaumMapApiKey(MapApiConst.DAUM_MAPS_ANDROID_APP_API_KEY);
        mMapView.setMapViewEventListener(this);
        mMapView.setPOIItemEventListener(this);
        mMapView.setCurrentLocationEventListener(this);
        createDefaultMarker1(mMapView);
        createDefaultMarker2(mMapView);
        createDefaultMarker4(mMapView);
        createDefaultMarker3(mMapView);
        showAll();

        Intent intent= getIntent();
        String text=intent.getStringExtra("a1");
        String text2=intent.getStringExtra("a2");
        String text3=intent.getStringExtra("a3");
        String text4=intent.getStringExtra("a4");

        ArrayList<String> array=new ArrayList<String>();

        array.add(text);
        array.add(text2);
        array.add(text3);
        array.add(text4);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);

        ListView list=(ListView)findViewById(R.id.list_item);
        list.setAdapter(adapter);
    }


//   public void mOnClick2(View v){
//        Intent i=new Intent(this,Date.class);
//        startActivity(i);
//    }


    public void mOnClick(View v){
        Intent intent=getIntent();
        String mtext=intent.getStringExtra("a3");
        String mtext2=intent.getStringExtra("a2");
        String mtext3=intent.getStringExtra("a1");
        String mtext4=intent.getStringExtra("a4");

        ArrayList<String> array=new ArrayList<String>();

        switch(v.getId()){
            case R.id.rankbtn:
                array.add(mtext);
                array.add(mtext2);
                array.add(mtext3);
                array.add(mtext4);

                ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);

                ListView list=(ListView)findViewById(R.id.list_item);
                list.setAdapter(adapter);

                mMapView.removeAllPOIItems();
                createDefaultMarker2(mMapView);
                createDefaultMarker4(mMapView);
                createDefaultMarker3(mMapView);
                createDefaultMarker1(mMapView);
                break;

            case R.id.lengthbtn:
                array.add(mtext2);
                array.add(mtext);
                array.add(mtext4);
                array.add(mtext3);

                ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);

                ListView list2=(ListView)findViewById(R.id.list_item);
                list2.setAdapter(adapter2);

                mMapView.removeAllPOIItems();
                createDefaultMarker1(mMapView);
                createDefaultMarker4(mMapView);
                createDefaultMarker3(mMapView);
                createDefaultMarker2(mMapView);
                break;

            case R.id.pricebtn:
                array.add(mtext4);
                array.add(mtext2);
                array.add(mtext3);
                array.add(mtext);

                ArrayAdapter<String> adapter3=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,array);

                ListView list3=(ListView)findViewById(R.id.list_item);
                list3.setAdapter(adapter3);

                mMapView.removeAllPOIItems();
                createDefaultMarker1(mMapView);
                createDefaultMarker2(mMapView);
                createDefaultMarker3(mMapView);
                createDefaultMarker4(mMapView);
                break;
        }

    }
    @Override
    public void onCurrentLocationUpdate(MapView mapView, MapPoint currentLocation, float accuracyInMeters) {
        MapPoint.GeoCoordinate mapPointGeo = currentLocation.getMapPointGeoCoord();
        Log.i(LOG_TAG, String.format("MapView onCurrentLocationUpdate (%f,%f) accuracy (%f)", mapPointGeo.latitude, mapPointGeo.longitude, accuracyInMeters));
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {
        mMapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeadingWithoutMapMoving);
    }


    private void createDefaultMarker1(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "오늘도나혼자밥";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(0);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT1);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
        mapView.selectPOIItem(mDefaultMarker, true);
        mapView.setMapCenterPoint(DEFAULT_MARKER_POINT1, false);
    }

    private void createDefaultMarker2(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "혼밥의 고수";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(1);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT2);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
        mapView.selectPOIItem(mDefaultMarker, true);
        mapView.setMapCenterPoint(DEFAULT_MARKER_POINT2, false);
    }
    private void createDefaultMarker3(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "싸움의 고수";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(2);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT3);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
        mapView.selectPOIItem(mDefaultMarker, true);
        mapView.setMapCenterPoint(DEFAULT_MARKER_POINT3, false);
    }
    private void createDefaultMarker4(MapView mapView) {
        mDefaultMarker = new MapPOIItem();
        String name = "혼밥혼밥";
        mDefaultMarker.setItemName(name);
        mDefaultMarker.setTag(3);
        mDefaultMarker.setMapPoint(DEFAULT_MARKER_POINT4);
        mDefaultMarker.setMarkerType(MapPOIItem.MarkerType.BluePin);
        mDefaultMarker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);

        mapView.addPOIItem(mDefaultMarker);
        mapView.selectPOIItem(mDefaultMarker, true);
        mapView.setMapCenterPoint(DEFAULT_MARKER_POINT4, false);
    }

    private void showAll() {
        int padding = 20;
        float minZoomLevel = 7;
        float maxZoomLevel = 10;
        MapPointBounds bounds = new MapPointBounds(DEFAULT_MARKER_POINT1, DEFAULT_MARKER_POINT1);
        mMapView.moveCamera(CameraUpdateFactory.newMapPointBounds(bounds, padding, minZoomLevel, maxZoomLevel));
    }

    @Override
    public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {}

    @Override
    public void onCurrentLocationUpdateFailed(MapView mapView) {}

    @Override
    public void onCurrentLocationUpdateCancelled(MapView mapView) {}

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {}

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {}

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {}

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {}

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {}

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {}

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {}

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {}

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {}

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
            mMapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter());
            mapView.addPOIItem(mDefaultMarker);
            mapView.selectPOIItem(mDefaultMarker, true);
            mapView.setMapCenterPoint(DEFAULT_MARKER_POINT1, false);
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) { }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) { }
}
