package com.example.david.chenwoo;

import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.david.chenwoo.Adapter.DataPackingAdapter;
import com.example.david.chenwoo.Adapter.NameProductAdapter;
import com.example.david.chenwoo.Adapter.NomerPenjualanAdapter;
import com.example.david.chenwoo.Common.Constant;
import com.example.david.chenwoo.Database.Data.ProductWrapper;
import com.example.david.chenwoo.Database.Data.Sale;
import com.example.david.chenwoo.Database.Data.Wrapper;
import com.victor.loading.rotate.RotateLoading;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by David on 03/05/2017.
 */

public class UpdateResultActivity extends BaseActivity {

    @InjectView(R.id.tv_detail_data)
    TextView tvDetailData;
    @InjectView(R.id.tv_title_trackcode)
    TextView tvTrackcodeTitle;
    @InjectView(R.id.tv_trackcode)
    TextView tvTrackcode;
    @InjectView(R.id.tv_title_nama_produk)
    TextView tvNamaProdukTitle;
    @InjectView(R.id.s_nama_produk)
    Spinner sNamaProduk;
    @InjectView(R.id.tv_title_nomer_penjualan)
    TextView tvNomerPenjualanTitle;
    @InjectView(R.id.s_nomer_penjualan)
    Spinner sNomerPenjualan;


    @InjectView(R.id.rv_data_packing)
    RecyclerView rvDataPacking;
    @InjectView(R.id.b_add)
    Button bAdd;

    @InjectView(R.id.rotateloading)
    RotateLoading rotateLoading;
//    @InjectView(R.id.ib_simpan)
//    ImageButton ibSimpan;
    @InjectView(R.id.ib_simpan_plus)
    ImageButton ibSimpanPlus;

    String packageNo;
    String idPackage;

    NameProductAdapter namaProdukAdapter;
    ArrayAdapter<ProductWrapper> nomerPenjualanAdapter;
    DataPackingAdapter dataPackingAdapter;
    List<ProductWrapper> listProductWrapper = new ArrayList<>();
    List<ProductWrapper> productDetails = new ArrayList<>();
    List<Sale> sales;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.inject(this);
        customizeFontsDefault(tvDetailData, tvTrackcodeTitle, tvTrackcode, tvNamaProdukTitle);


        packageNo = getIntent().getStringExtra("package_no");
        dataPackingAdapter = new DataPackingAdapter(UpdateResultActivity.this, productDetails);
        rvDataPacking.setLayoutManager(new LinearLayoutManager(UpdateResultActivity.this, LinearLayoutManager.VERTICAL, false));
        rvDataPacking.setAdapter(dataPackingAdapter);
        dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.dialog_alert);

        setView();
        setListener();


        rotateLoading.start();
    }

    private void setListener() {
        sNomerPenjualan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!sales.get(i).isDummy()){
                    listProductWrapper = sales.get(i).getProduct();
                    //System.out.println("CHECK SIZE PRODUCT: "+sales.get(i).getProduct().get(0).getProductDetailList().get(0).getBatch_number());
                    namaProdukAdapter.reset(sales.get(i).getProduct());
//                    if(Constant.positionListProductWrapper!=-1){
//                        sNamaProduk.setSelection(Constant.positionListProductWrapper);
//                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sNamaProduk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dataPackingAdapter.reset(listProductWrapper.get(sNamaProduk.getSelectedItemPosition()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataPackingAdapter.addProductDetail();
                rvDataPacking.setLayoutManager(new LinearLayoutManager(UpdateResultActivity.this, LinearLayoutManager.VERTICAL, false));
                rvDataPacking.scrollToPosition(dataPackingAdapter.getItemCount() - 1);
            }
        });

        ibSimpanPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sNamaProduk.getSelectedItemPosition() == sNamaProduk.getCount()) {
                    showToast(getString(R.string.pilih_nama_produk));
                }else if (sNomerPenjualan.getSelectedItemPosition() == nomerPenjualanAdapter.getCount()) {
                    showToast(getString(R.string.pilih_nomor_penjualan));
                } else {
                    rotateLoading.start();
                    List<String> productDetailIdList = dataPackingAdapter.getProductDetailIdList();
                    List<String> nettWeightList = dataPackingAdapter.getNettWeightList();
                    List<String> brutWeightList = dataPackingAdapter.getBrutWeightList();
                    List<String> quantityPerPackList = dataPackingAdapter.getQuantityPerPackList();

                    Call<Wrapper> call = getService().postUpdatePackage(getSession().getAccessToken(), idPackage, productDetailIdList, nettWeightList, brutWeightList, quantityPerPackList, sNomerPenjualan.getSelectedItem().toString(), namaProdukAdapter.getProduckId(sNamaProduk.getSelectedItemPosition()));
                    call.enqueue(new Callback<Wrapper>() {
                        @Override
                        public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
                            if (response.isSuccessful()) {
//                                Constant.listProductWrapper = listProductWrapper;
//                                Constant.sales = sales;
//                                Constant.positionListProductWrapper = sNamaProduk.getSelectedItemPosition();
//                                Constant.positionListSales = sNomerPenjualan.getSelectedItemPosition();

                                Constant.lastIdProductSelected = sNomerPenjualan.getSelectedItem().toString();

                                if (response.body().getStatus() == Constant.UNAUTORIZED) {
                                    logOut();
                                } else if (response.body().getStatus() == Constant.DATA_NOT_FOUND) {
                                    showDialog(false,getString(R.string.data_not_found));
                                    //showToast(getString(R.string.data_not_found));
                                    rotateLoading.stop();
                                } else if (response.body().getStatus() == Constant.PARAMETER_MISSING) {
                                    //showToast(getString(R.string.data_gagal_diubah));
                                    showDialog(false,getString(R.string.data_gagal_diubah));
                                    rotateLoading.stop();
                                } else if (response.isSuccessful() && response.body().getStatus() != Constant.DATA_NOT_FOUND) {
                                    showDialog(true,getString(R.string.data_berhasil_diupdate));
                                    rotateLoading.stop();
                                    //finish();
                                }
                            } else {
                                rotateLoading.stop();
                                showDialog(false,getString(R.string.data_gagal_diubah));
                            }

                        }

                        @Override
                        public void onFailure(Call<Wrapper> call, Throwable t) {

                        }
                    });
                }
            }
        });

//        ibSimpan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (sNamaProduk.getSelectedItemPosition() == sNamaProduk.getCount()) {
//                    showToast(getString(R.string.pilih_nama_produk));
//                }else if (sNomerPenjualan.getSelectedItemPosition() == nomerPenjualanAdapter.getCount()) {
//                    showToast(getString(R.string.pilih_nomor_penjualan));
//                }  else {
//                    rotateLoading.start();
//                    List<String> productDetailIdList = dataPackingAdapter.getProductDetailIdList();
//                    List<String> nettWeightList = dataPackingAdapter.getNettWeightList();
//                    List<String> brutWeightList = dataPackingAdapter.getBrutWeightList();
//                    List<String> quantityPerPackList = dataPackingAdapter.getQuantityPerPackList();
//
//                    Call<Wrapper> call = getService().postUpdatePackage(getSession().getAccessToken(), idPackage, productDetailIdList, nettWeightList, brutWeightList, quantityPerPackList, sNomerPenjualan.getSelectedItem().toString(), namaProdukAdapter.getProduckId(sNamaProduk.getSelectedItemPosition()));
//                    call.enqueue(new Callback<Wrapper>() {
//                        @Override
//                        public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
//                            if (response.isSuccessful()) {
//                                Constant.listProductWrapper=null;
//                                Constant.sales = null;
//                                Constant.positionListProductWrapper = -1;
//                                Constant.positionListSales = -1;
//                                if (response.body().getStatus() == Constant.UNAUTORIZED) {
//                                    logOut();
//                                } else if (response.body().getStatus() == Constant.DATA_NOT_FOUND) {
//                                    showDialog(false,getString(R.string.data_not_found));
//                                    //showToast(getString(R.string.data_not_found));
//                                    rotateLoading.stop();
//                                } else if (response.body().getStatus() == Constant.PARAMETER_MISSING) {
//                                    //showToast(getString(R.string.data_gagal_diubah));
//                                    showDialog(false,getString(R.string.data_gagal_diubah));
//                                    rotateLoading.stop();
//                                } else if (response.isSuccessful() && response.body().getStatus() != Constant.DATA_NOT_FOUND) {
//                                    showDialog(true,getString(R.string.data_berhasil_diupdate));
//                                    rotateLoading.stop();
//                                    //finish();
//                                }
//                            } else {
//                                rotateLoading.stop();
//                                showDialog(false,getString(R.string.data_gagal_diubah));
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(Call<Wrapper> call, Throwable t) {
//
//                        }
//                    });
//                }
//            }
//        });
    }

    private void setView() {
//        try {
//            Field popup = Spinner.class.getDeclaredField("mPopup");
//            popup.setAccessible(true);
//
//            // Get private mPopup member variable and try cast to ListPopupWindow
//            android.widget.ListPopupWindow popupWindow = (android.widget.Spinner) popup.get(sNomerPenjualan);
//
//            // Set popupWindow height to 500px
//            popupWindow.setHeight(30);
//        }
//        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
//            System.out.println("ERROR FAILED "+ e.getMessage());
//            // silently fail...
//        }
//
//        try {
//            Field popup = Spinner.class.getDeclaredField("mPopup");
//            popup.setAccessible(true);
//
//            // Get private mPopup member variable and try cast to ListPopupWindow
//            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(sNamaProduk);
//
//            // Set popupWindow height to 500px
//            popupWindow.setHeight(30);
//        }
//        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
//            System.out.println("ERROR FAILED "+ e.getMessage());
//            // silently fail...
//        }


        Call<Wrapper> call = getService().getUpdatePackage(getSession().getAccessToken(), this.packageNo);
        call.enqueue(new Callback<Wrapper>() {
            @Override
            public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
                if(response.isSuccessful()){
                    if (response.body().getStatus() == Constant.UNAUTORIZED) {
                        logOut();
                    } else if (response.body().getStatus() == Constant.FAIL) {
                        rotateLoading.stop();
                        showToast(getString(R.string.packet_already_load));
                        showDialog(false,getString(R.string.packet_already_load));
                    } else if (response.isSuccessful() && response.body().getStatus() != Constant.DATA_NOT_FOUND) {
                        rotateLoading.stop();
                        tvTrackcode.setText(response.body().getData().getPackage_info().getTrackcode());
                        idPackage = response.body().getData().getPackage_info().getId_package();
//                        if(Constant.listProductWrapper!=null){
//                            listProductWrapper = Constant.listProductWrapper;
//                        }else {
//                            listProductWrapper = response.body().getData().getProduct();
//                            listProductWrapper.add(new ProductWrapper(true));
//                        }

//                        if(Constant.sales!=null){
//                            sales = Constant.sales;
//                        }else {
                            sales = response.body().getData().getSale();
                            sales.add(new Sale(true));
                            //System.out.println("ASDASD: "+ response.body().getData().getSale().get(0).getProduct().get(0).getProductDetailList().get(0).getBatch_number() );
                        //}
                        nomerPenjualanAdapter = new NomerPenjualanAdapter(UpdateResultActivity.this, R.layout.simple_spinner_item, sales);
                        namaProdukAdapter = new NameProductAdapter(UpdateResultActivity.this, R.layout.simple_spinner_item, listProductWrapper);
                        sNomerPenjualan.setAdapter(nomerPenjualanAdapter);
                        sNamaProduk.setAdapter(namaProdukAdapter);

//                        if(Constant.positionListProductWrapper!=-1){
//                            sNamaProduk.setSelection(Constant.positionListProductWrapper);
//                        }else{
//                            sNamaProduk.setSelection(namaProdukAdapter.getCount());
//                        }

                        if(Constant.lastIdProductSelected.matches("-1")){
                            sNomerPenjualan.setSelection(nomerPenjualanAdapter.getCount());
                        }else{
                            int position = nomerPenjualanAdapter.getCount();
                            System.out.println("CHECK ID SALES0: "+sales.size());
                            for(int i=0;i<sales.size();i++){
                                if(sales.get(i).getId()!=null){
                                    if(Constant.lastIdProductSelected.matches(sales.get(i).getId())){
                                        position = i;
                                    }
                                }
                            }
                            sNomerPenjualan.setSelection(position);
                        }


//                        if(Constant.positionListSales!=-1){
//                            sNomerPenjualan.setSelection(Constant.positionListSales);
//                        }else{
//                            sNomerPenjualan.setSelection(nomerPenjualanAdapter.getCount());
//                        }

//                        productDetails.add(listProductWrapper.get(0));
                        dataPackingAdapter.notifyDataSetChanged();

                    } else if (response.body().getStatus() == Constant.DATA_NOT_FOUND) {
                        showToast(getString(R.string.data_not_found));
                        showDialog(false,getString(R.string.data_not_found));
                        rotateLoading.stop();
                    }
                }else{
                    rotateLoading.stop();
                    showDialog(false,getString(R.string.error_coba_lagi));
                }
            }

            @Override
            public void onFailure(Call<Wrapper> call, Throwable t) {
                showDialog(false,getString(R.string.error_coba_lagi));
                rotateLoading.stop();
            }
        });
    }

    Dialog dialog;

    private void showDialog(final boolean b, String s) {
        RelativeLayout rlWrapper = (RelativeLayout) dialog.findViewById(R.id.rl_wrapper);
        ImageView ivStatus = (ImageView) dialog.findViewById(R.id.iv_alert);
        TextView tvStatus = (TextView) dialog.findViewById(R.id.tv_alert);
        if (b) {
            ivStatus.setBackgroundResource(R.drawable.ic_berhasil);
        } else {
            ivStatus.setBackgroundResource(R.drawable.ic_gagal);
        }
        tvStatus.setText(s);
        rlWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dialog.dismiss();
                UpdateResultActivity.this.finish();
            }
        });

        dialog.show();


    }
}
