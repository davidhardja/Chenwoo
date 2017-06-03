package com.example.david.chenwoo;

import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.david.chenwoo.Common.Constant;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by David on 01/04/2017.
 */

public class SettingActivity extends BaseActivity {

    @InjectView(R.id.et_url)
    EditText etUrl;
    @InjectView(R.id.b_save)
    Button bSave;
    @InjectView(R.id.tv_setting)
    TextView tvSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.inject(this);
        customizeFontsOswald(etUrl, bSave, tvSetting);
        setListener();
    }

    private void setListener() {
        etUrl.setHint(Constant.API_URL);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    ((Application) getApplication()).changeUrl(etUrl.getText().toString());
                    finish();
                    System.exit(0);
                }catch (Exception e){
                    showToast("CHANGE URL FAILD TRY AGAIN!");
                }
            }
        });

        etUrl.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    bSave.performClick();
                    return true;
                }
                return false;
            }
        });
    }

}

