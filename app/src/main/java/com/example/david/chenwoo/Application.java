package com.example.david.chenwoo;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.david.chenwoo.Common.Constant;
import com.example.david.chenwoo.Database.DatabaseHelper;
import com.example.david.chenwoo.Service.ApiService;
import com.example.david.chenwoo.Tools.SessionManagement;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by David on 01/04/2017.
 */

public class Application extends MultiDexApplication {
    private ApiService service;
    private DatabaseHelper helper;
    private SessionManagement session;
    Retrofit retrofit;
    OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();

        session = new SessionManagement(this);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        String url = Constant.API_URL;
        if(!session.getMasterUrl().matches("")){
            url = session.getMasterUrl();
        }

        try {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(ApiService.class);
        }catch (Exception e){
            session.setKeyMasterUrl("");
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(ApiService.class);
        }

        System.out.println("CHECK BASE URL: "+url);



        helper = new DatabaseHelper(this);
    }

    public void changeUrl(String s){
        session.setKeyMasterUrl(s);
        retrofit.newBuilder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(s)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        service = retrofit.create(ApiService.class);
        System.out.println("CHECK BASE URL@: "+ s);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public ApiService getService() {
        return service;
    }

    public DatabaseHelper getHelper() {
        return helper;
    }

    public SessionManagement getSession() {
        return session;
    }
}
