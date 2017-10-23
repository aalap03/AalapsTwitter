package com.example.aalap.aalapstwitter.App;

import android.app.Application;
import android.util.Log;

import com.example.aalap.aalapstwitter.R;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by aalap on 2017-09-13.
 */

public class AalapsTwitter extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getResources().getString(R.string.twitter_api_key),
                        getResources().getString(R.string.twitter_secret_key)))
                .debug(true)
                .build();

        Twitter.initialize(config);

        TwitterSession activeSession = TwitterCore.getInstance()
                .getSessionManager().getActiveSession();
        // example of custom OkHttpClient with logging HttpLoggingInterceptor
        //HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient customClient = new OkHttpClient.Builder()
          //      .addInterceptor(loggingInterceptor)
                .build();
        TwitterApiClient customApiClient;

        if (activeSession != null) {
            customApiClient = new TwitterApiClient(activeSession, customClient);
            TwitterCore.getInstance().addApiClient(activeSession, customApiClient);
        } else {
            customApiClient = new TwitterApiClient(customClient);
            TwitterCore.getInstance().addGuestApiClient(customApiClient);
        }

    }
}
