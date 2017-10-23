package com.example.aalap.aalapstwitter.RetrofitUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Aalap on 2017-09-16.
 */

public interface RetrofitService {


    @GET("trends/place.json")
    Call<ResponseBody> getresponse(@Query("id") int id);
}
