package com.example.david.chenwoo.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.david.chenwoo.Database.Data.ProductWrapper;
import com.example.david.chenwoo.Database.Data.Sale;
import com.example.david.chenwoo.R;

import java.util.List;

/**
 * Created by David on 21/05/2017.
 */

public class NomerPenjualanAdapter extends ArrayAdapter {
    private List<Sale> sales;

    public NomerPenjualanAdapter(Context context, int textViewResourceId, List<Sale> objects) {
        super(context, textViewResourceId, objects);
        sales = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return super.getCount() - 1;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.simple_spinner_item, parent, false);
        TextView label = (TextView) row.findViewById(R.id.tv_text);

        if (position == getCount()) {
            label.setText(getContext().getString(R.string.pilih_nomor_penjualan));
        } else {
            label.setText(sales.get(position).getSale_no());
        }

        return row;
    }
}
