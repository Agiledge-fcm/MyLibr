package com.agile.mylibrarylogin.interfaces;

import com.agile.mylibrarylogin.dto.Tokencreds;
import com.agile.mylibrarylogin.dto.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RestService {

    @POST("oauth/token")
    @FormUrlEncoded
    Call<Tokencreds> getToken(@Field("grant_type") String grant_type, @Field("scope") String scope,
                              @Field("username") String username, @Field("password") String password
    );

    @POST("auth/login")
    Call<Tokencreds> postlogin(@Header("Authorization")String Oauth_Token, @Body User user);




}
