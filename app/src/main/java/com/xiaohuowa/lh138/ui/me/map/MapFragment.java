package com.xiaohuowa.lh138.ui.me.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.xiaohuowa.lh138.R;

public class MapFragment extends Fragment {

    private static final int REQUEST_CODE = 0x10086;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private PoiSearch mPoiSearch;

    private LatLng latLng;

    public class MyPoiOverlay extends PoiOverlay {

        /**
         * 构造函数
         *
         * @param baiduMap 该 PoiOverlay 引用的 BaiduMap 对象
         */
        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        /**
         * 兴趣点被点击方法
         *
         * @param i 被点击的poi在
         *          {@link PoiResult#getAllPoi()} 中的索引
         * @return
         */
        @Override
        public boolean onPoiClick(int i) {
            if (getPoiResult().getAllPoi() != null) {
                PoiInfo poiInfo = getPoiResult().getAllPoi().get(i);
                if (poiInfo != null) {
                    Toast.makeText(getContext(),
                                    "名称:" + poiInfo.name
                                    + "\n地址:" + poiInfo.address
                                    + "\n电话:" + poiInfo.phoneNum, Toast.LENGTH_LONG)
                            .show();
                }
            }
            return super.onPoiClick(i);
        }
    }

    /**
     * 构造地图数据
     * 通过继承抽象类BDAbstractListener并重写其onReceieveLocation方法来获取定位数据，并将其传给MapView。
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            // 获得经纬度
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
            mBaiduMap.setMyLocationData(locData);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);
        //获取地图控件引用
        mMapView = root.findViewById(R.id.bmapView);
        // 设置缩放级别
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(18.0f);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        // 声明权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (root.getContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // 版本大于6.0且没获得权限
                // 请求权限
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            }
        }
        // 开启地图的定位图层
        mBaiduMap.setMyLocationEnabled(true);
        //定位初始化
        try {
            mLocationClient = new LocationClient(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();
        // 在定义了以上属性之后，通过如下方法来设置生效：
        MyLocationConfiguration mLocationConfiguration = new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.COMPASS,
                true, null);
        mBaiduMap.setMyLocationConfiguration(mLocationConfiguration);

        // 创建POI检索实例
        mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    mBaiduMap.clear();

                    //创建PoiOverlay对象
                    MyPoiOverlay poiOverlay = new MyPoiOverlay(mBaiduMap);

                    //设置Poi检索数据
                    poiOverlay.setData(poiResult);

                    //将poiOverlay添加至地图并缩放至合适级别
                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();
                    // 兴趣点标记点击
                    mBaiduMap.setOnMarkerClickListener(poiOverlay);
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }

            //废弃
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        };
        // 设置检索监听器
        mPoiSearch.setOnGetPoiSearchResultListener(listener);
        EditText editText = root.findViewById(R.id.editText);
        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(v -> {
            /**
             * 搜索周边
             */
            String keyword = editText.getText().toString();
            mPoiSearch.searchNearby(new PoiNearbySearchOption()
                    .location(latLng)
                    .radius(100000)
                    //支持多个关键字并集检索，不同关键字间以$符号分隔，最多支持10个关键字检索。如:”银行$酒店”
                    .keyword(keyword));
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 获得权限了，重新定位
                mLocationClient.restart();
            } else {
                // 没获得权限
                Toast.makeText(getContext(), "拒绝了GPS定位，无法定位", Toast.LENGTH_SHORT).show();
            }

        }
    }
}