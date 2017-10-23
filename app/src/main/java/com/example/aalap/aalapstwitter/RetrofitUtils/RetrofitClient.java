package com.example.aalap.aalapstwitter.RetrofitUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aalap on 2017-09-19.
 */

public class RetrofitClient {

    public static Retrofit retrofitInstance(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.twitter.com/1.1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }
}
