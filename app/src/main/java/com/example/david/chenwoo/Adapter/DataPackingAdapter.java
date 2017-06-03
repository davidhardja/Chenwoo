package com.example.david.chenwoo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.david.chenwoo.Database.Data.ProductDetail;
import com.example.david.chenwoo.Database.Data.ProductWrapper;
import com.example.david.chenwoo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by David on 21/05/2017.
 */

public class DataPackingAdapter extends BaseAdapter {
    private List<ProductWrapper> productList = new ArrayList<>();
    List<String> productDetailIdList = new ArrayList<String>();
    List<String> nettWeightList = new ArrayList<String>();
    List<String> brutWeightList = new ArrayList<String>();
    List<String> quantityPerPackList = new ArrayList<String>();

    public DataPackingAdapter(Context context, List<ProductWrapper> list) {
        super(context, list);
        productList = list;
    }

//    public void clear() {
//        int size = this.productList.size();
//        if (size > 0) {
//            for (int i = 0; i < size; i++) {
//                this.productList.remove(0);
//            }
//
//            this.notifyItemRangeRemoved(0, size);
//        }
//    }
//
//    public void addAll(List<ProductDetail> list){
//        int size = list.size();
//        if (size > 0) {
//            for (int i = 0; i < size; i++) {
//                this.productList.add(list.get(i));
//                this.notifyItemInserted(productList.size());
//            }
//        }
//    }

    public void reset(ProductWrapper productWrapper) {
        productList.clear();
        productDetailIdList.clear();
        brutWeightList.clear();
        nettWeightList.clear();
        if(!productWrapper.isDummy()){
            productList.add(productWrapper);
        }
        this.notifyDataSetChanged();
    }

    public void addProductDetail() {
        if (productList.size() > 0) {
            productList.add(productList.get(0));
            this.notifyItemInserted(productList.size() - 1);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataPackingAdapter.ViewHolder v = (DataPackingAdapter.ViewHolder) holder;
        v.bind(position);
    }

    @Override
    public DataPackingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_packing, parent, false);
        return new DataPackingAdapter.ViewHolder(v);
    }

    public List<String> getProductDetailIdList() {
        return productDetailIdList;
    }

    public List<String> getNettWeightList() {
        return nettWeightList;
    }

    public List<String> getBrutWeightList() {
        return brutWeightList;
    }

    public List<String> getQuantityPerPackList() {
        return quantityPerPackList;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.tv_batch_number)
        TextView tvBatchNumber;
        @InjectView(R.id.tv_isi_data_packing)
        TextView tvIsiDataPacking;
        @InjectView(R.id.tv_cari_batch_number)
        TextView tvCariBatchNumber;
        @InjectView(R.id.s_batch_number)
        Spinner sBatchNumber;
        @InjectView(R.id.tv_berat_bersih)
        TextView tvBeratBersih;
        @InjectView(R.id.et_berat_bersih)
        EditText etBeratBersih;
        @InjectView(R.id.tv_berat_kotor)
        TextView tvBeratKotor;
        @InjectView(R.id.et_berat_kotor)
        EditText etBeratKotor;
        @InjectView(R.id.tv_jumlah_potongan)
        TextView tvJumlahPotongan;
        @InjectView(R.id.et_jumlah_potongan)
        EditText etJumlahPotongan;
        ArrayAdapter arrayAdapter;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            customizeFontsDefault(tvIsiDataPacking, tvCariBatchNumber, tvBeratBersih, etBeratBersih, tvBeratKotor, etBeratKotor, tvJumlahPotongan, etJumlahPotongan);
        }

        private void bind(final int position) {
            if(!productList.get(position).isDummy()){
                arrayAdapter = new ArrayAdapter<ProductDetail>(context, R.layout.spinner_batchnumber, productList.get(position).getProduct_detail());
                sBatchNumber.setAdapter(arrayAdapter);
                sBatchNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if(productList.size()>0){
                            if(productDetailIdList.size()!=productList.size()){
                                productDetailIdList.add(position, productList.get(position).getProduct_detail().get(sBatchNumber.getSelectedItemPosition()).getId());
                                System.out.println("CHECK ID: "+productList.get(position).getProduct_detail().get(sBatchNumber.getSelectedItemPosition()).getId());
                            }else {
                                productDetailIdList.set(position, productList.get(position).getProduct_detail().get(sBatchNumber.getSelectedItemPosition()).getId());
                                System.out.println("CHECK ID: "+productList.get(position).getProduct_detail().get(sBatchNumber.getSelectedItemPosition()).getId());
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                if(productList.size()>0){
//                if(productDetailIdList.size()!=productList.size()){
//                    productDetailIdList.add(position, productList.get(position).getProductDetailList().get(sBatchNumber.getSelectedItemPosition()).getId());
//                }else {
//                    productDetailIdList.set(position, productList.get(position).getProductDetailList().get(sBatchNumber.getSelectedItemPosition()).getId());
//                }

                    if (nettWeightList.size() > 0) {
                        etBeratBersih.setText(nettWeightList.get(position));
                    }
                    if (brutWeightList.size() > 0) {
                        etBeratKotor.setText(brutWeightList.get(position));
                    }
                    if (quantityPerPackList.size() > 0) {
                        etJumlahPotongan.setText(quantityPerPackList.get(position));
                    }
                }

                etBeratBersih.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(nettWeightList.size()!=productList.size()){
                            nettWeightList.add(position, editable.toString());
                        }else {
                            nettWeightList.set(position, editable.toString());
                        }
                    }
                });

                etBeratKotor.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(brutWeightList.size()!=productList.size()){
                            brutWeightList.add(position, editable.toString());
                        }else {
                            brutWeightList.set(position, editable.toString());
                        }
                    }
                });

                etJumlahPotongan.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        if(quantityPerPackList.size()!=productList.size()){
                            quantityPerPackList.add(position, editable.toString());
                        }else {
                            quantityPerPackList.set(position, editable.toString());
                        }
                    }
                });
            }
        }

        @Override
        public void onClick(View view) {

        }
    }
}
