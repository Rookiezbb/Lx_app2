package com.bawei.lx_app2.activity;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bawei.xiangmutwo.R;
import com.liaoinstan.springview.container.BaseHeader;
import com.liaoinstan.springview.utils.DensityUtil;

/**
 * Created by lenovo on 2017/10/23.
 */

public class MyHeader extends BaseHeader {
    private ImageView textView;
    private int i=0;
    private Context context;
    private ImageView header_img;
    private int[] pullAnimSrcs = new int[]{com.liaoinstan.springview.R.drawable.mt_pull, com.liaoinstan.springview.R.drawable.mt_pull01, com.liaoinstan.springview.R.drawable.mt_pull02, com.liaoinstan.springview.R.drawable.mt_pull03, com.liaoinstan.springview.R.drawable.mt_pull04, com.liaoinstan.springview.R.drawable.mt_pull05};
    private int[] refreshAnimSrcs = new int[]{com.liaoinstan.springview.R.drawable.mt_refreshing01, com.liaoinstan.springview.R.drawable.mt_refreshing02, com.liaoinstan.springview.R.drawable.mt_refreshing03, com.liaoinstan.springview.R.drawable.mt_refreshing04, com.liaoinstan.springview.R.drawable.mt_refreshing05, com.liaoinstan.springview.R.drawable.mt_refreshing06};
    private AnimationDrawable animationPull;
    private AnimationDrawable animationPullFan;
    private AnimationDrawable animationRefresh;
    public MyHeader(Context context){
        this(context,null,null);
    }
    public MyHeader(Context context, int[] pullAnimSrcs, int[] refreshAnimSrcs){
        this.context= context;
        if (pullAnimSrcs!=null) this.pullAnimSrcs = pullAnimSrcs;
        if (refreshAnimSrcs!=null) this.refreshAnimSrcs = refreshAnimSrcs;
        animationPull = new AnimationDrawable();
        animationPullFan = new AnimationDrawable();
        animationRefresh = new AnimationDrawable();
        for (int i=1;i< this.pullAnimSrcs.length;i++) {
            animationPull.addFrame(ContextCompat.getDrawable(context, this.pullAnimSrcs[i]),100);
            animationRefresh.setOneShot(true);
        }
        for (int i= this.pullAnimSrcs.length-1;i>=0;i--){
            animationPullFan.addFrame(ContextCompat.getDrawable(context, this.pullAnimSrcs[i]), 100);
            animationRefresh.setOneShot(true);
        }
        for (int src: this.refreshAnimSrcs) {
            animationRefresh.addFrame(ContextCompat.getDrawable(context, src),150);
            animationRefresh.setOneShot(false);
        }
    }
    //获取Header
    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(com.liaoinstan.springview.R.layout.meituan_header, viewGroup, true);
        header_img = (ImageView) view.findViewById(com.liaoinstan.springview.R.id.meituan_header_img);
        if (pullAnimSrcs !=null&& pullAnimSrcs.length>0)
            header_img.setImageResource(pullAnimSrcs[0]);
        return view;
    }

    //拖拽开始前回调
    @Override
    public void onPreDrag(View rootView) {}

    //手指拖拽过程中不断回调，dy为拖拽的距离，可以根据拖动的距离添加拖动过程动画
    @Override
    public void onDropAnim(View rootView, int dy) {
        int maxw = DensityUtil.dip2px(context,45);
        float w = maxw*Math.abs(dy)/rootView.getMeasuredHeight();
        if (w>maxw) return;
        ViewGroup.LayoutParams layoutParams = header_img.getLayoutParams();
        layoutParams.width = (int) w;

    }

    //手指拖拽过程中每次经过临界点时回调，upORdown是向上经过还是向下经过
    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        if (!upORdown){
            header_img.setImageDrawable(animationPull);
            animationPull.start();
        }else {
            header_img.setImageDrawable(animationPullFan);
            animationPullFan.start();
        }
    }

    //拉动超过临界点后松开时回调
    @Override
    public void onStartAnim() {
        header_img.setImageDrawable(animationRefresh);
        animationRefresh.start();

    }

    //头部已经全部弹回时回调
    @Override
    public void onFinishAnim() {
        if (pullAnimSrcs !=null&& pullAnimSrcs.length>0)
            header_img.setImageResource(pullAnimSrcs[0]);

    }
}