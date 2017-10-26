package com.bawei.lx_app2.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.xiangmutwo.R;
import com.bawei.xiangmutwo.bean.IndexBean;
import com.bawei.xiangmutwo.utils.BannerImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class MyIndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int TYPE_BANNER = 0;
    private int TYPE_GRID = 1;
    private int TYPE_GALLERY = 2;
    private int TYPE_GALLERYS = 3;
    private Context context;
    private IndexBean.DataBean dataBean;

    public MyIndexAdapter(Context context, IndexBean.DataBean dataBean) {
        this.context = context;
        this.dataBean = dataBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_BANNER){
            View view1 = LayoutInflater.from(context).inflate(R.layout.index_banner, parent, false);
            return new MyBannerViewHolder(view1);
        }else if(viewType == TYPE_GRID){
            View view2 = LayoutInflater.from(context).inflate(R.layout.index_grid, parent, false);
            return new MyGridViewHolder(view2);
        }else if(viewType == TYPE_GALLERY){
            View view3 = LayoutInflater.from(context).inflate(R.layout.index_staggered, parent, false);
            return new MyStagViewHolder(view3);
        }else if(viewType == TYPE_GALLERYS){
            View view4 = LayoutInflater.from(context).inflate(R.layout.liner_layout, parent, false);
            return new MyLinerViewHolder(view4);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyBannerViewHolder){
            MyBannerViewHolder viewHolder = (MyBannerViewHolder) holder;
            viewHolder.banner.setImageLoader(new BannerImageLoader());
            List<String> bannerList = new ArrayList<>();
            for (int i = 0; i < dataBean.getSubjects().size(); i++) {
                bannerList.add(dataBean.getSubjects().get(i).getImage());
            }
            viewHolder.banner.setImages(bannerList);
            viewHolder.banner.setDelayTime(3000);
            viewHolder.banner.start();
        }else if(holder instanceof MyGridViewHolder){
            MyGridViewHolder gridViewHolder = (MyGridViewHolder) holder;
            gridViewHolder.rv_grid.setLayoutManager(new GridLayoutManager(context,4));
            MyIndexGridAdapter myIndexGridAdapter = new MyIndexGridAdapter(context,dataBean.getAd5());
            gridViewHolder.rv_grid.setAdapter(myIndexGridAdapter);
        }else if(holder instanceof MyStagViewHolder) {
                MyStagViewHolder myStagViewHolder = (MyStagViewHolder) holder;
               myStagViewHolder.rv_like.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL));
                MyIndexStagAdapter myIndexStagAdapter = new MyIndexStagAdapter(dataBean.getSubjects(),context);
                myStagViewHolder.rv_like.setAdapter(myIndexStagAdapter);
        }else if(holder instanceof MyLinerViewHolder) {
            MyLinerViewHolder myLinerViewHolder = (MyLinerViewHolder) holder;
            myLinerViewHolder.recycler_liner.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            MyIndexLinerAdapter myIndexLinerAdapter = new MyIndexLinerAdapter(dataBean.getDefaultGoodsList(),context);
            myLinerViewHolder.recycler_liner.setAdapter(myIndexLinerAdapter);
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_GRID;
        } else if (position == 2){
            return TYPE_GALLERY;
        }else if (position == 3){
            return TYPE_GALLERYS;
        }
        return 4;
    }
    @Override
    public int getItemCount() {
        return 4;
    }
    public class MyBannerViewHolder extends RecyclerView.ViewHolder{
        private final Banner banner;
        public MyBannerViewHolder(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.index_banner);
        }
    }
    public class MyGridViewHolder extends RecyclerView.ViewHolder{
        private final RecyclerView rv_grid;
        public MyGridViewHolder(View itemView) {
            super(itemView);
            rv_grid = (RecyclerView) itemView.findViewById(R.id.rv_grid);
        }
    }
    public class MyStagViewHolder extends RecyclerView.ViewHolder{

        private final RecyclerView rv_like;

        public MyStagViewHolder(View itemView) {
            super(itemView);
            rv_like = (RecyclerView) itemView.findViewById(R.id.rv_like);

        }
    }
    public class MyLinerViewHolder extends RecyclerView.ViewHolder{

        private final RecyclerView recycler_liner;

        public MyLinerViewHolder(View itemView) {
            super(itemView);
            recycler_liner = (RecyclerView) itemView.findViewById(R.id.recycler_liner);

        }
    }
}
