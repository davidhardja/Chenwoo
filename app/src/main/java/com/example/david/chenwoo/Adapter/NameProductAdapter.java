package com.example.david.chenwoo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.david.chenwoo.Database.Data.ProductWrapper;
import com.example.david.chenwoo.R;

import java.util.List;

/**
 * Created by David on 21/05/2017.
 */

public class NameProductAdapter extends ArrayAdapter {
    List<ProductWrapper> productWrapperList;
    public NameProductAdapter(Context context, int textViewResourceId,  List<ProductWrapper> objects) {
        super(context, textViewResourceId, objects);
        productWrapperList = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public void reset(List<ProductWrapper> productWrapper) {
        productWrapperList.clear();

        for (int i=0;i<productWrapper.size();i++){
            productWrapperList.add(productWrapper.get(i));
        }
        productWrapperList.add(new ProductWrapper(true));
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public String getProduckId(int position){
        return productWrapperList.get(position).getId();
    }

    @Override
    public int getCount() {
        return super.getCount()-1;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_batchnumber, parent, false);
        TextView label = (TextView) row.findViewById(R.id.tv_text);
        if(position==getCount()){
            label.setText(getContext().getString(R.string.pilih_nama_produk));
        }else{
            label.setText(productWrapperList.get(position).getName());

        }

        return row;
    }
}
