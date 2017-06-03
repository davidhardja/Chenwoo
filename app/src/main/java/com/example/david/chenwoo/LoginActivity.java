package com.example.david.chenwoo;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.david.chenwoo.Common.Constant;
import com.example.david.chenwoo.Database.Data.Wrapper;
import com.victor.loading.rotate.RotateLoading;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.b_login)
    Button bLogin;
    @InjectView(R.id.tv_app_name)
    TextView tvAppName;
    @InjectView(R.id.rotateloading)
    RotateLoading rotateLoading;
    @InjectView(R.id.b_setting)
    ImageButton ibSetting;
    @InjectView(R.id.b_lupa_password)
    Button bLupaPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        customizeFontsOswald(etUsername, etPassword, bLogin, tvAppName);
        if(!getSession().getAccessToken().matches("")){
            goToMainActivity();
        }
        setListener();
    }

    private void setListener() {
        bLupaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(getString(R.string.fitur_belum_tersedia));
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateLoading.start();
                Call<Wrapper> call = getService().login(etUsername.getText().toString(), etPassword.getText().toString());
                call.enqueue(new Callback<Wrapper>() {
                    @Override
                    public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
                        if (response.isSuccessful()&&response.body().getStatus()== Constant.LOGIN_SUCCESS) {
                            rotateLoading.stop();
                            getSession().setAccessToken(response.body().getData().getToken());
                            goToMainActivity();
                            finish();
                        } else {
                            showToast(getString(R.string.login_failed));
                            rotateLoading.stop();
                        }
                    }

                    @Override
                    public void onFailure(Call<Wrapper> call, Throwable t) {
                        rotateLoading.stop();
                    }
                });
            }
        });

        ibSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        etPassword.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    bLogin.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    private void goToMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
