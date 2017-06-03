package com.example.david.chenwoo.Database.Data;

import java.util.List;

/**
 * Created by David on 21/05/2017.
 */

public class Sale {
    private String id;
    private String sale_no;
    private boolean dummy = false;
    private List<ProductWrapper> product;

    public void setDummy(boolean dummy) {
        this.dummy = dummy;
    }

    public List<ProductWrapper> getProduct() {
        return product;
    }

    public void setProduct(List<ProductWrapper> product) {
        this.product = product;
    }

    public Sale(boolean dummy){
        this.dummy = dummy;
    }

    public boolean isDummy() {
        return dummy;
    }


    public String getId() {
        return id;
    }

    public String toString(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSale_no() {
        return sale_no;
    }

    public void setSale_no(String sale_no) {
        this.sale_no = sale_no;
    }
}
