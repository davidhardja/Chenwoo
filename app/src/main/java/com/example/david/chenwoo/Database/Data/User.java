package com.example.david.chenwoo.Database.Data;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by David on 31/03/2017.
 */

public class User {

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private String username;

    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
