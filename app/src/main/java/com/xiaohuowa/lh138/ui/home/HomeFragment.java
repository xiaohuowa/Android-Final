package com.xiaohuowa.lh138.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.xiaohuowa.lh138.R;
import com.xiaohuowa.lh138.adapter.HomeAdapter;
import com.xiaohuowa.lh138.adapter.ImageAdapter;
import com.xiaohuowa.lh138.adapter.ImageTitleNumAdapter;
import com.xiaohuowa.lh138.bean.NewsBean;
import com.xiaohuowa.lh138.databinding.FragmentHomeBinding;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.transformer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private HomeAdapter homeAdapter;
    private Banner<?, BannerAdapter<?,?>> banner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RefreshLayout refreshLayout = (RefreshLayout) root.findViewById(R.id.refreshLayout);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(null);
        recyclerView.setAdapter(homeAdapter);

        homeAdapter.setEmptyView(R.layout.empty_home);
        View headerView = inflater.inflate(R.layout.header_home, container, false);
        homeAdapter.addHeaderView(headerView);  // 添加头视图
        // 空布局的时候也显示头（主要结构在没刷新的时候也保留）
        homeAdapter.setHeaderWithEmptyEnable(true);
        //--------------------------banner 简单使用-------------------------------
        banner = headerView.findViewById(R.id.banner);
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
//                .setIndicator(new RectangleIndicator(getContext()));
                // 设置切换动画
                .setPageTransformer(new ZoomOutPageTransformer())
                // 设置指示器默认颜色
                .setIndicatorNormalColorRes(R.color.purple_700)
                // 设置指示器选中颜色
                .setIndicatorSelectedColorRes(R.color.purple_200)
                .start();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            // 加入缺省图
            list.add(R.drawable.pic_item_list_default);
        }
        banner.setAdapter(new ImageAdapter(list));

        refreshLayout.setOnRefreshListener((refresh) -> {
            refresh.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            getAdList();
            getNewsList();
        });
        refreshLayout.setOnLoadMoreListener(refresh -> {
            refresh.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
        });

        // 进入即加载
        getAdList();
        getNewsList();

        LinearLayout linearLayout_Python = headerView.findViewById(R.id.linearLayout_Python);
        linearLayout_Python.setOnClickListener(v -> {
            // 跳转
            Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_pythonFragment);
        });

        // 菠萝跳转
        LinearLayout linearLayout_pineapple = headerView.findViewById(R.id.linearLayout_pineapple);
        linearLayout_pineapple.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", "https://baike.baidu.com/link?url=IcxntEbw-rm0Fq1enaVhoEHkLRvk3zZdohCaOK5eu85dnwL_SnJoabk2bpfpvtJAbwYjJoHKlQqvhXx-brcS2coKhg0wYQ2Ezr5x6KT4WDW");
            Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_webFragment, bundle);
        });
        // 樱桃跳转
        LinearLayout linearLayout_cherry = headerView.findViewById(R.id.linearLayout_cherry);
        linearLayout_cherry.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", "https://baike.baidu.com/link?url=O1kpVTnI_94fIs0CG1roTdAbVxhpbMn9tle55IZgKj2q7i__1dDU9xfC1TBOrP-vGSh12XqGk-h1OO7CcXxcz6K2qxLTo-DKa8pQLxosqP_");
            Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_webFragment, bundle);
        });
        // PHP跳转
        LinearLayout linearLayout_php = headerView.findViewById(R.id.linearLayout_php);
        linearLayout_php.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", "https://www.php.net/");
            Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_webFragment, bundle);
        });

        homeAdapter.setOnItemClickListener((adapter, view, position) -> {
            // 传递新闻数据，实现新闻点击跳转
            Bundle bundle = new Bundle();
            bundle.putString("url", homeAdapter.getData().get(position).getNewsUrl());
            Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_webFragment, bundle);
        });


        return root;
    }

    /**
     * 刷新之后得到广告列表
     */
    private void getAdList() {
        homeViewModel.getAdList().observe(getViewLifecycleOwner(), newsBeans -> {
            /*for (NewsBean newsBean : newsBeans) {
                Log.i("Ads:", newsBean.getNewsName());
            }*/
            banner.setAdapter(new ImageTitleNumAdapter(newsBeans));
            // 点击banner广告
            banner.setOnBannerListener((data, position) -> {
                // 传递新闻数据，实现新闻点击跳转
                Bundle bundle = new Bundle();
                bundle.putString("url", ((NewsBean)data).getNewsUrl());
                Navigation.findNavController(banner).navigate(R.id.action_navigation_home_to_webFragment, bundle);
            });
        });
    }

    /**
     * 刷新之后得到新闻列表
     */
    private void getNewsList() {
        homeViewModel.getNewsList().observe(getViewLifecycleOwner(), newsBeans -> {
            /*for (NewsBean newsBean : newsBeans) {
                Log.i("News:", newsBean.getNewsName());
            }*/
            homeAdapter.setList(newsBeans);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}