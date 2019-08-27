package com.agile.mylibr;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.agile.mylibrarylogin.LoginLibr;
import com.agile.mylibrarylogin.dto.Tokencreds;
import com.agile.mylibrarylogin.dto.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    Tokencreds tokencreds;
    SharedPreferences sharedPreferences;
    LoginLibr loginLibr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         loginLibr = new LoginLibr();
        //LoginLibr.login();
        Call<Tokencreds> call  = loginLibr.getToken();
        call.enqueue(new Callback<Tokencreds>() {
            @Override
            public void onResponse(Call<Tokencreds> call, Response<Tokencreds> response) {
                Log.e("Token111", response.body().toString());
                if (response.code() == 200) {

                    // Toast.makeText(MainActivity.this, "resp Code" + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("TokenLib2", response.body().toString());
                     String token = response.body().getAccessToken();

                     Log.e("token","token" + token);
                    getUser(token);

                }
            }

            @Override
            public void onFailure(Call<Tokencreds> call, Throwable t) {

            }
        });




}

    public void getUser(String token) {

        User creds = new User();
        creds.setUsername("admin");
        creds.setPassword("admin");
        Call<Tokencreds> usercredentials =loginLibr.authenticateuser(token, creds);

        usercredentials.enqueue(new Callback<Tokencreds>() {
            @Override
            public void onResponse(Call<Tokencreds> call, Response<Tokencreds> response) {
                Log.e("Login122", response.body().toString());
            }

            @Override
            public void onFailure(Call<Tokencreds> call, Throwable t) {

            }
        });
    }
}
