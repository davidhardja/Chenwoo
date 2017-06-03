package com.example.david.chenwoo.Tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by David on 31/03/2017.
 */

public class SessionManagement {

    private static final String PREF_NAME = "CHENWOO_PREF";
    private static final String KEY_ACCESS_TOKEN = "access_token";
    private static final String KEY_NAMA = "name";
    private static final String KEY_NIK = "nik";
    private static final String KEY_JABATAN = "position";
    private static final String KEY_FOTO = "picture";
    private static final String KEY_MASTER_URL = "master_url";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context _context;
    private int PRIVATE_MODE = 0;


    public SessionManagement(Context context) {
        this._context = context;
        preferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();

    }

    public String getAccessToken() {
        String theAccessToken = "";
        if (preferences.getString(KEY_ACCESS_TOKEN, "") != null) {
            theAccessToken = preferences.getString(KEY_ACCESS_TOKEN, "");
        }
        return theAccessToken;
    }

    public void setAccessToken(String username) {
        editor.putString(KEY_ACCESS_TOKEN, username);
        editor.commit();
    }

    public String getName() {
        String theAccessToken = "";
        if (preferences.getString(KEY_NAMA, "") != null) {
            theAccessToken = preferences.getString(KEY_NAMA, "");
        }
        return theAccessToken;
    }

    public void setName(String username) {
        editor.putString(KEY_NAMA, username);
        editor.commit();
    }

    public String getNik() {
        String theAccessToken = "";
        if (preferences.getString(KEY_NIK, "") != null) {
            theAccessToken = preferences.getString(KEY_NIK, "");
        }
        return theAccessToken;
    }

    public void setNik(String username) {
        editor.putString(KEY_NIK, username);
        editor.commit();
    }

    public String getJabatan() {
        String theAccessToken = "";
        if (preferences.getString(KEY_JABATAN, "") != null) {
            theAccessToken = preferences.getString(KEY_JABATAN, "");
        }
        return theAccessToken;
    }

    public void setJabatan(String username) {
        editor.putString(KEY_JABATAN, username);
        editor.commit();
    }

    public String getProfile() {
        String theAccessToken = "";
        if (preferences.getString(KEY_FOTO, "") != null) {
            theAccessToken = preferences.getString(KEY_FOTO, "");
        }
        return theAccessToken;
    }

    public void setProfile(String username) {
        editor.putString(KEY_FOTO, username);
        editor.commit();
    }

    public String getMasterUrl() {
        String theAccessToken = "";
        if (preferences.getString(KEY_MASTER_URL, "") != null) {
            theAccessToken = preferences.getString(KEY_MASTER_URL, "");
        }
        return theAccessToken;
    }

    public void setKeyMasterUrl(String username) {
        editor.putString(KEY_MASTER_URL, username);
        editor.commit();
    }

    public void clearData(){
        setAccessToken("");
        setName("");
        setProfile("");
        setNik("");
        setJabatan("");
    }

}





