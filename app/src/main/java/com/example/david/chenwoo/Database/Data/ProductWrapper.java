package com.example.david.chenwoo.Database.Data;

import java.util.List;

/**
 * Created by David on 21/05/2017.
 */

public class ProductWrapper {
    private String id;
    private String name;
    private List<ProductDetail> product_detail;
    private boolean dummy = false;

    public ProductWrapper(boolean dummy) {
        this.dummy = dummy;
    }

    public List<ProductDetail> getProduct_detail() {
        return product_detail;
    }

    public void setProduct_detail(List<ProductDetail> product_detail) {
        this.product_detail = product_detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDummy() {
        return dummy;
    }

    public String toString() {

        return "";
    }
}
