package com.example.david.chenwoo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.david.chenwoo.Common.Constant;
import com.example.david.chenwoo.Database.Data.Wrapper;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by David on 16/06/2017.
 */

public class CancelScannerActivity  extends BaseActivity implements ZXingScannerView.ResultHandler {

    Dialog dialog;
    private ZXingScannerView mScannerView;
    String nomer = new String();

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view

        dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_alert_cancel);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        mScannerView.stopCamera();
        super.onPause();
        // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        if (dialog != null) {
            dialog.dismiss();
        }
        nomer = rawResult.getText();
        Call<Wrapper> call = getService().cancelPackage(getSession().getAccessToken(), rawResult.getText());
        call.enqueue(new Callback<Wrapper>() {
            @Override
            public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
                if(response.body().getStatus()== Constant.UNAUTORIZED){
                    logOut();
                }else if (response.body().getStatus() == Constant.STATUS_BERUBAH) {
                    showDialog(true, response.body().getMessage());
                } else {
                    showDialog(false,response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<Wrapper> call, Throwable t) {
                showDialog(false,"");
            }
        });



    }

    private void showDialog(boolean b, String s) {
        RelativeLayout rlWrapper = (RelativeLayout) dialog.findViewById(R.id.rl_wrapper);
        ImageView ivStatus = (ImageView) dialog.findViewById(R.id.iv_alert);
        TextView tvStatus = (TextView) dialog.findViewById(R.id.tv_alert);
        TextView tvNomor = (TextView) dialog.findViewById(R.id.tv_nomor);
        if (b) {
            ivStatus.setBackgroundResource(R.drawable.ic_berhasil);
            if(!nomer.matches("")){
                tvNomor.setText(nomer);
            }
        } else {
            ivStatus.setBackgroundResource(R.drawable.ic_gagal);
        }
        tvStatus.setText(s);
        rlWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScannerView.resumeCameraPreview(CancelScannerActivity.this);
                dialog.dismiss();
            }
        });

        dialog.show();


    }
}