package com.ln.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterViewFlipper;

import com.ln.adapters.HomeGridAdapter;
import com.ln.base.BaseFragment;
import com.ln.view.R;
import com.ln.widgets.MyBanner;
import com.ln.widgets.TextAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by linan   on 2017/4/5.
 */

public class HomeFragment extends BaseFragment {
    LinkedList<String> imgsUrl; //轮播图片URL合集
    @Bind(R.id.banner)
    MyBanner mBanner;
    @Bind(R.id.avf)
    AdapterViewFlipper flipper;
    @Bind(R.id.home_rv)
    RecyclerView gridRv; //网格模块

    @Override
    public int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews(View self, Bundle savedInstanceState) {
        initBanner();
        initAdapterFlipper();
        initGrid();
    }

    /**
     * adapterViewFlipper实现文字滚动
     */
    private void initAdapterFlipper() {
        String[] lst = getActivity().getResources().getStringArray(R.array.flipper);
        TextAdapter adapter = new TextAdapter(lst);
        flipper.setAdapter(adapter);
//        flipper.setFlipInterval(2000);
//        flipper.setInAnimation(this.getContext(),R.animator.flipper_in);
//        flipper.setOutAnimation(this.getContext(),R.animator.flipper_out);
        flipper.setAutoStart(true);
    }

    /**
     * 轮播
     */
    private void initBanner() {
        imgsUrl = new LinkedList<>();
        imgsUrl.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imgsUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgsUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        //添加广告数据
        mBanner.setVpData(imgsUrl);
    }

    /**
     * 模块
     */
    private void initGrid() {
        String  arrayGrids[][]={{"视频","10"},{"文字","20"},{"语言","30"},{"音乐","40"},{"视频","50"},{"视频","60"},{"视频","70"},{"视频","80"}};
        List<SparseArray<String>> gridList = new ArrayList<>();
        for (int i = 0; i < arrayGrids.length; i++) {
            SparseArray<String> sa=new SparseArray<>();
            sa.put(Integer.parseInt(arrayGrids[i][1]),arrayGrids[i][0]);
            gridList.add(sa);
        }
        HomeGridAdapter mAdapter = new HomeGridAdapter(R.layout.adapter_home_grid, gridList);
        GridLayoutManager glm = new GridLayoutManager(this.getContext(), 4);
        gridRv.setHasFixedSize(true);
        gridRv.setLayoutManager(glm);
        gridRv.setAdapter(mAdapter);

    }

}
