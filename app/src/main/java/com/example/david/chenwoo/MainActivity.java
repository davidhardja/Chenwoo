package com.example.david.chenwoo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.david.chenwoo.Common.Constant;
import com.example.david.chenwoo.Database.Data.Wrapper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by David on 01/04/2017.
 */

public class MainActivity extends BaseActivity{

    @InjectView(R.id.tv_selamat_datang)
    TextView tvSelamatDatang;
    @InjectView(R.id.tv_name)
    TextView tvNama;
    @InjectView(R.id.tv_jabatan)
    TextView tvJabatan;
    @InjectView(R.id.tv_id)
    TextView tvId;
    @InjectView(R.id.b_keluar)
    Button bKeluar;
    @InjectView(R.id.b_load)
    Button bLoad;
    @InjectView(R.id.b_tracking)
    Button bTracking;
    @InjectView(R.id.iv_profile)
    ImageView ivProfile;
    @InjectView(R.id.b_update)
    Button bUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        customizeFontsDefault(tvId,tvJabatan,tvSelamatDatang,tvNama,bKeluar,bLoad,bTracking,bUpdate);
        setListener();
        setView();
    }

    private void setView(){
        try{
            Glide.with(MainActivity.this).load(getSession().getProfile()).bitmapTransform(new CropCircleTransformation(MainActivity.this)).into(ivProfile);
        }catch (Exception e){}
        tvId.setText(getSession().getNik());
        tvJabatan.setText(getSession().getJabatan());
        tvNama.setText(getSession().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Call<Wrapper> call = getService().getProfile(getSession().getAccessToken());
        call.enqueue(new Callback<Wrapper>() {
            @Override
            public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
                if(response.body().getStatus()== Constant.UNAUTORIZED){
                    logOut();
                }else if(response.isSuccessful()){
                    getSession().setJabatan(response.body().getData().getJabatan());
                    getSession().setNik(response.body().getData().getNik());
                    getSession().setProfile(response.body().getData().getPp());
                    getSession().setName(response.body().getData().getFull_name());
                    setView();
                }
            }

            @Override
            public void onFailure(Call<Wrapper> call, Throwable t) {

            }
        });
    }

    private void setListener(){

        bKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                getSession().clearData();
                finish();
            }
        });

        bLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoadScannerActivity.class);
                startActivity(intent);
            }
        });

        bTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TrackingScannerActivity.class);
                startActivity(intent);
            }
        });

        bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateScannerActivity.class);
                startActivity(intent);
            }
        });
    }
}
