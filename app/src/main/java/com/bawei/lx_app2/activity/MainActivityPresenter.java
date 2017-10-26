package com.bawei.lx_app2.activity;

import com.bawei.xiangmutwo.bean.IndexBean;

/**
 * Created by lenovo on 2017/10/23.
 */

public class MainActivityPresenter {

    private MainActivityViewListener listener;
    private MainActivityModel mainActivityModel;

    public MainActivityPresenter(MainActivityViewListener listener){

        this.listener = listener;
        this.mainActivityModel = new MainActivityModel();
    }


    public void getData(boolean up){

        mainActivityModel.getData(up, new MainActivityModelListener() {
            @Override
            public void callBackSuccess(IndexBean bean) {
                listener.callBackSuccess(bean);
            }

            @Override
            public void callBackFailure(int code) {
                listener.callBackFailure(code);
            }
        });

    }



}
