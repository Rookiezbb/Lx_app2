package com.bawei.lx_app2.activity;

import com.bawei.xiangmutwo.bean.IndexBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/10/23.
 */

public class MainActivityModel {
    public void getData(boolean up,final MainActivityModelListener listener){


        OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder().url("http://m.yunifang.com/yunifang/mobile/home").build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // 1 网络
                        listener.callBackFailure(1);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String result = response.body().string();


                        Gson gson = new Gson();
                        IndexBean bean =  gson.fromJson(result, IndexBean.class);

                        listener.callBackSuccess(bean);


                    }
                });


    }

}
