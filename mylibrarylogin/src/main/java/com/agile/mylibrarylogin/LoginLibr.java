package com.agile.mylibrarylogin;

import android.content.SharedPreferences;
import android.util.Log;

import com.agile.mylibrarylogin.constants.CommenSettings;
import com.agile.mylibrarylogin.dto.Tokencreds;
import com.agile.mylibrarylogin.dto.User;
import com.agile.mylibrarylogin.interfaces.RestService;
import com.agile.mylibrarylogin.oAuth.ServiceGenerator;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginLibr {
    Tokencreds tokencreds;
    User creds;
    RestService service;
    SharedPreferences sharedPreferences;
    public static void login(String user, String password) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommenSettings.apiHostName)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public  Call<Tokencreds>  getToken() {

        RestService restService =
                ServiceGenerator.createService(RestService.class, "web_app", "changeit");
        Call<Tokencreds> call = restService.getToken("password", "openid", "admin", "admin");
       /* try {
            new NetworkCall().execute(call);
             tokencreds = call.execute().body();

        }catch (IOException ioe){
            ioe.printStackTrace();
        }*/
        /* call.enqueue(new Callback<Tokencreds>() {
            @Override
            public void onResponse(Call<Tokencreds> call, Response<Tokencreds> response) {
                if (response.code() == 200) {
                    Log.e("TokenLib1", response.body().toString());
                } else {

                    // Toast.makeText(MainActivity.this, "resp Code" + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("TokenLib2", response.raw().toString());
                    String responseHeaders = response.headers().get("Authorization");
                    tokencreds = new Tokencreds();
                    tokencreds.accessToken = responseHeaders;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Oauth_Token", tokencreds.accessToken);
                    editor.commit();

                   // authenticateuser();
                    //CommenSettings.Oauth_Token = tokencreds.accessToken;

                }

            }

            @Override
            public void onFailure(Call<Tokencreds> call, Throwable t) {
                Log.e("Throwable", t.toString());
            }

        });*/
        return call;
    }


    public   Call<Tokencreds>  authenticateuser(String Oauth_Token,User user) {

        creds = new User();
        creds.setUsername("admin");
        creds.setPassword("admin");
        Log.d("Creds", "" + creds.toString());
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommenSettings.apiHostName)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RestService.class);
        Call<Tokencreds> usercredentials = service.postlogin(Oauth_Token, creds);

        return usercredentials;
   /*     {
            usercredentials.enqueue(new Callback<Tokencreds>() {
                @Override
                public void onResponse(Call<Tokencreds> call, retrofit2.Response<Tokencreds> response) {
                    Log.d("Tokencreds***", "" + response.code());
                    Log.d("Login_Token", "" + response.body());
                    if (response.code() == 200) {


                        Log.e("Login_Token", response.body().toString());

                        String accessToken1 = response.body().getAccessToken();

                        Log.e("Login_Tokenget", accessToken1);

                        *//*final SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("Login_Token", "Bearer " + accessToken);
                        editor.putString("LoginId", email);
                        editor.commit();

                        SharedPreferences.Editor editor2 = getSharedPreferences("login", MODE_PRIVATE).edit();
                        editor2.putBoolean("loginval",true);

                        editor2.apply();

                        Intent iMain = new Intent(LoginUserActivity.this, MainActivity.class);
                        startActivity(iMain);
                        finish();*//*

                    } else {

                    }
                }

                @Override
                public void onFailure(Call<Tokencreds> call, Throwable t) {
                    // UIUtil.hideDialog();
                }
            });
            if (tokencreds!=null) {
                return tokencreds.accessToken;
            }else {
                return null;
            }
        }*/

    }
}
