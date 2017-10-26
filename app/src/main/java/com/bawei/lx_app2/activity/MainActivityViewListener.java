package com.bawei.lx_app2.activity;

import com.bawei.xiangmutwo.bean.IndexBean;

/**
 * Created by lenovo on 2017/10/23.
 */

interface MainActivityViewListener {
    public void callBackSuccess(IndexBean bean);
    public void callBackFailure(int code);
}
