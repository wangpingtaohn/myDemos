package com.wpt.mydemos.arcgis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.wpt.mydemos.R;

public class ArcGisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_gis);

        initView();
    }

    private void initView() {
        //在Layout中获取到MapView控件，记得在外层创建mMapView对象
        MapView mMapView = (MapView) findViewById(R.id.mapView);
        //创建一个地图对象
        ArcGISMap map = new ArcGISMap();
        //创建并添加地图服务
        String strMapUrl = "http://map.geoq.cn/ArcGIS/rest/services/ChinaOnlineCommunity/MapServer";
        ArcGISTiledLayer arcGISTiledLayer = new ArcGISTiledLayer(strMapUrl);
        //创建底图、并设置底图图层
        Basemap basemap = new Basemap();
        basemap.getBaseLayers().add(arcGISTiledLayer);
        //设置地图底图
        map.setBasemap(basemap);
        //设置map地图对象在MapView控件中显示
        mMapView.setMap(map);
    }
}