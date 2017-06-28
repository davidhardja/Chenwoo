package com.example.david.chenwoo.Service;

import com.example.david.chenwoo.Database.Data.User;
import com.example.david.chenwoo.Database.Data.Wrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by David on 31/03/2017.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("api/login")
    Call<Wrapper> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("api/loadpackage")
    Call<Wrapper> loadPackage(
            @Header("token") String token,
            @Field("package_no") String package_no
    );

    @FormUrlEncoded
    @POST("api/cancel")
    Call<Wrapper> cancelPackage(
            @Header("token") String token,
            @Field("package_no") String package_no
    );

    @FormUrlEncoded
    @POST("api/viewpackage")
    Call<Wrapper> detailPackage(
            @Header("token") String token,
            @Field("package_no") String package_no
    );

    @GET("api/profile")
    Call<Wrapper> getProfile(
            @Header("token") String token
    );

    @GET("api/updatepackage")
    Call<Wrapper> getUpdatePackage(
            @Header("token") String token,
            @Query("package_no") String package_no
    );

    @FormUrlEncoded
    @POST("api/updatepackage")
    Call<Wrapper> postUpdatePackage(
            @Header("token") String token,
            @Field("id") String id_package,
            @Field("product_detail_id[]") List<String> product_detail_id,
            @Field("nett_weight[]") List<String> nett_weight,
            @Field("brut_weight[]") List<String> brut_weight,
            @Field("quantity_per_pack[]") List<String> quantity_per_pack,
            @Field("sale_id") String sale_id,
            @Field("product_id") String product_id
    );
}
