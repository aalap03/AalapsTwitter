package com.example.aalap.aalapstwitter.RetrofitUtils;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by Aalap on 2017-09-17.
 */

public class CustomTwitterApiCLient extends TwitterApiClient {

    public CustomTwitterApiCLient(TwitterSession twitterSession){
        super(twitterSession);
    }

    public PlacesService getCustomService() {
        return getService(PlacesService.class);
    }

    public interface PlacesService{
        @GET("https://api.twitter.com/1.1/trends/place.json")
        Call<com.squareup.okhttp.Response> getResponse(int id);
    }
}
