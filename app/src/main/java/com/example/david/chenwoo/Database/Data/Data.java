package com.example.david.chenwoo.Database.Data;

import java.util.List;

/**
 * Created by David on 31/03/2017.
 */

public class Data {
    private String token;
    private String package_no;
    private String nama_produk;
    private String batch_number;
    private String berat_bersih;
    private String pcs;
    private String nomor_po;
    private String nomor_invoice;
    private String nama_supplier;
    private String nomor_nota_timbang;
    private String tanggal_produksi;
    private String nama_pembeli;
    private String tanggal_pengiriman;
    private String shipped_by;

    private String full_name;
    private String nik;
    private String jabatan;
    private String pp;

    private PackageInfo package_info;

    private List<ProductWrapper>  product;//buang
    private List<Sale> sale;

    public List<ProductWrapper> getProduct() {
        return product;
    }

    public void setProduct(List<ProductWrapper>  product) {
        this.product = product;
    }

    public List<Sale> getSale() {
        return sale;
    }

    public void setSale(List<Sale> sale) {
        this.sale = sale;
    }

    public PackageInfo getPackage_info() {
        return package_info;
    }

    public void setPackage_info(PackageInfo package_info) {
        this.package_info = package_info;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getPp() {
        return pp;
    }

    public void setPp(String pp) {
        this.pp = pp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPackage_no() {
        return package_no;
    }

    public void setPackage_no(String package_no) {
        this.package_no = package_no;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    public String getBatch_number() {
        return batch_number;
    }

    public void setBatch_number(String batch_number) {
        this.batch_number = batch_number;
    }

    public String getBerat_bersih() {
        return berat_bersih;
    }

    public void setBerat_bersih(String berat_bersih) {
        this.berat_bersih = berat_bersih;
    }

    public String getPcs() {
        return pcs;
    }

    public void setPcs(String pcs) {
        this.pcs = pcs;
    }

    public String getNomor_po() {
        return nomor_po;
    }

    public void setNomor_po(String nomor_po) {
        this.nomor_po = nomor_po;
    }

    public String getNomor_invoice() {
        return nomor_invoice;
    }

    public void setNomor_invoice(String nomor_invoice) {
        this.nomor_invoice = nomor_invoice;
    }

    public String getNama_supplier() {
        return nama_supplier;
    }

    public void setNama_supplier(String nama_supplier) {
        this.nama_supplier = nama_supplier;
    }

    public String getNomor_nota_timbang() {
        return nomor_nota_timbang;
    }

    public void setNomor_nota_timbang(String nomor_nota_timbang) {
        this.nomor_nota_timbang = nomor_nota_timbang;
    }

    public String getTanggal_produksi() {
        return tanggal_produksi;
    }

    public void setTanggal_produksi(String tanggal_produksi) {
        this.tanggal_produksi = tanggal_produksi;
    }

    public String getNama_pembeli() {
        return nama_pembeli;
    }

    public void setNama_pembeli(String nama_pembeli) {
        this.nama_pembeli = nama_pembeli;
    }

    public String getTanggal_pengiriman() {
        return tanggal_pengiriman;
    }

    public void setTanggal_pengiriman(String tanggal_pengiriman) {
        this.tanggal_pengiriman = tanggal_pengiriman;
    }

    public String getShipped_by() {
        return shipped_by;
    }

    public void setShipped_by(String shipped_by) {
        this.shipped_by = shipped_by;
    }
}
