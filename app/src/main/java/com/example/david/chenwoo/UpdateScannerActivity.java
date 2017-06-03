package com.example.david.chenwoo;

import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by David on 03/05/2017.
 */

public class UpdateScannerActivity extends BaseActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
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
        // Do something with the result here
        // Prints scan results
        // Prints the scan format (qrcode, pdf417 etc.)
        System.out.println("RESULTXXX " + rawResult);
        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);

        Intent intent = new Intent(UpdateScannerActivity.this, com.example.david.chenwoo.UpdateResultActivity.class);
        intent.putExtra("package_no", rawResult.getText());
        startActivity(intent);
    }
}