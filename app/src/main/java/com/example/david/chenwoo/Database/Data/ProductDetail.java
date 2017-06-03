package com.example.david.chenwoo.Database.Data;

/**
 * Created by David on 21/05/2017.
 */

public class ProductDetail {
    private String id;

    private String batchnumber;

    public String toString() {
        return batchnumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatch_number() {
        return batchnumber;
    }

    public void setBatch_number(String batch_number) {
        this.batchnumber = batch_number;
    }

}
