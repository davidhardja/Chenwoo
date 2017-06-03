package com.example.david.chenwoo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.chenwoo.Common.Constant;
import com.example.david.chenwoo.Database.DatabaseHelper;
import com.example.david.chenwoo.Service.ApiService;
import com.example.david.chenwoo.Tools.SessionManagement;

import static java.security.AccessController.getContext;

/**
 * Created by David on 01/04/2017.
 */

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ApiService getService() {
        return ((Application) getApplication()).getService();
    }

    public DatabaseHelper getHelper() {
        return ((Application) getApplication()).getHelper();
    }

    public SessionManagement getSession() {
        return ((Application) getApplication()).getSession();
    }

    protected void customizeFontsOswald(TextView... textViews) {
        for (TextView textView : textViews) {
            Typeface typeFace = Typeface.createFromAsset(getAssets(), Constant.OSWALD_FONT);
            textView.setTypeface(typeFace);
        }
    }

    protected void customizeFontsDefault(TextView... textViews) {
        for (TextView textView : textViews) {
            Typeface typeFace = Typeface.createFromAsset(getAssets(), Constant.DEFAULT_FONT);
            textView.setTypeface(typeFace);
        }
    }

    protected void showToast(String message) {
        if (message != null && this != null)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void logOut(){
        getSession().clearData();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
