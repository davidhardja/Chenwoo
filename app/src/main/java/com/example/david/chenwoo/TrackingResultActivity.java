package com.example.david.chenwoo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.david.chenwoo.Common.Constant;
import com.example.david.chenwoo.Database.Data.Wrapper;
import com.victor.loading.rotate.RotateLoading;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.grantland.widget.AutofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by David on 01/04/2017.
 */

public class TrackingResultActivity extends BaseActivity {

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_trackcode_title)
    TextView tvTrackcodeTitle;
    @InjectView(R.id.tv_trackcode)
    TextView tvTrackode;
    @InjectView(R.id.tv_nama_produk_title)
    TextView tvNamaProdukTitle;
    @InjectView(R.id.tv_nama_produk)
    TextView tvNamaProduk;
    @InjectView(R.id.tv_batch_number_title)
    TextView tvBatchNumberTitle;
    @InjectView(R.id.tv_batch_number)
    TextView tvBatchNumber;
    @InjectView(R.id.tv_berat_bersih_title)
    TextView tvBeratBersihTitle;
    @InjectView(R.id.tv_berat_bersih)
    TextView tvBeratBersih;
    @InjectView(R.id.tv_jumlah_pcs_title)
    TextView tvJumlahPcsTitle;
    @InjectView(R.id.tv_jumlah_pcs)
    TextView tvJumlahPcs;

    @InjectView(R.id.tv_nomor_po_title)
    TextView tvNomorPoTitle;
    @InjectView(R.id.tv_nomor_po)
    TextView tvNomorPo;
    @InjectView(R.id.tv_nomor_invoice_title)
    TextView tvNomorInvoiceTitle;
    @InjectView(R.id.tv_nomor_invoice)
    TextView tvNomorInvoice;

    @InjectView(R.id.tv_nama_supplier_title)
    TextView tvNamaSuplierTitle;
    @InjectView(R.id.tv_nama_supplier)
    TextView tvNamaSuplier;
    @InjectView(R.id.tv_nomor_nota_timbang_title)
    TextView tvNomorNotaTimbangTitle;
    @InjectView(R.id.tv_nomor_nota_timbang)
    TextView tvNomorNotaTimbang;
    @InjectView(R.id.tv_tanggal_produksi_title)
    TextView tvTanggalProduksiTitle;
    @InjectView(R.id.tv_tanggal_produksi)
    TextView tvTanggalProduksi;
    @InjectView(R.id.tv_nama_pembeli_title)
    TextView tvNamaPembeliTitle;
    @InjectView(R.id.tv_nama_pembeli)
    TextView tvNamaPembeli;
    @InjectView(R.id.tv_tanggal_pengiriman_title)
    TextView tvTanggalPengirimanTitle;
    @InjectView(R.id.tv_tanggal_pengiriman)
    TextView tvTanggalPengiriman;
    @InjectView(R.id.tv_shipped_by_title)
    TextView tvShippedByTitle;
    @InjectView(R.id.tv_shipped_by)
    TextView tvShippedBy;
    @InjectView(R.id.ib_back)
    ImageButton ibBack;

    @InjectView(R.id.rotateloading)
    RotateLoading rotateLoading;

    String packageNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        ButterKnife.inject(this);
        customizeFontsDefault(tvTitle,tvTrackcodeTitle,tvTrackode,tvNamaProdukTitle,tvNamaProdukTitle,tvNamaProduk);
        customizeFontsDefault(tvBatchNumber,tvBatchNumberTitle,tvBeratBersihTitle,tvBeratBersih,tvJumlahPcsTitle,tvJumlahPcs);
        customizeFontsDefault(tvNomorPoTitle,tvNomorPo,tvNomorInvoiceTitle,tvNomorInvoice);
        customizeFontsDefault(tvNamaSuplierTitle,tvNamaSuplier,tvNomorNotaTimbangTitle,tvNomorNotaTimbang,tvTanggalProduksiTitle,tvTanggalProduksi);
        customizeFontsDefault(tvNamaPembeliTitle,tvNamaPembeli,tvTanggalPengirimanTitle,tvTanggalPengiriman,tvShippedByTitle,tvShippedBy);
        packageNo = getIntent().getStringExtra("package_no");
        setListener();
        setView();

        rotateLoading.start();
    }

    private void setListener(){
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setView(){
        Call<Wrapper> call = getService().detailPackage(getSession().getAccessToken(),this.packageNo);
        call.enqueue(new Callback<Wrapper>() {
            @Override
            public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
                if(response.body().getStatus()== Constant.UNAUTORIZED){
                    logOut();
                }else if(response.isSuccessful()&&response.body().getStatus()!= Constant.DATA_NOT_FOUND){
                    rotateLoading.stop();
                    tvTrackode.setText(response.body().getData().getPackage_no());
                    tvNamaProduk.setText(response.body().getData().getNama_produk());
                    tvBatchNumber.setText(response.body().getData().getBatch_number());
                    tvBeratBersih.setText(response.body().getData().getBerat_bersih());
                    tvNomorPo.setText(response.body().getData().getNomor_po());
                    tvNomorInvoice.setText(response.body().getData().getNomor_invoice());
                    tvNamaSuplier.setText(response.body().getData().getNama_supplier());
                    tvNomorNotaTimbang.setText(response.body().getData().getNomor_nota_timbang());
                    tvTanggalProduksi.setText(response.body().getData().getTanggal_produksi());
                    tvNamaPembeli.setText(response.body().getData().getNama_pembeli());
                    tvTanggalPengiriman.setText(response.body().getData().getTanggal_pengiriman());
                    tvShippedBy.setText(response.body().getData().getShipped_by());
                    tvJumlahPcs.setText(response.body().getData().getPcs());

                    AutofitHelper.create(tvTrackode);
                    AutofitHelper.create(tvNamaProduk);
                    AutofitHelper.create(tvBatchNumber);
                    AutofitHelper.create(tvBeratBersih);
                    AutofitHelper.create(tvNomorPo);
                    AutofitHelper.create(tvNomorInvoice);
                    AutofitHelper.create(tvNamaSuplier);
                    AutofitHelper.create(tvNomorNotaTimbang);
                    AutofitHelper.create(tvTanggalProduksi);
                    AutofitHelper.create(tvNamaPembeli);
                    AutofitHelper.create(tvTanggalPengiriman);
                    AutofitHelper.create(tvShippedBy);

                }else if(response.body().getStatus()==Constant.DATA_NOT_FOUND){
                    showToast(getString(R.string.data_not_found));
                    rotateLoading.stop();
                }
            }

            @Override
            public void onFailure(Call<Wrapper> call, Throwable t) {
                rotateLoading.stop();
            }
        });
    }
}
