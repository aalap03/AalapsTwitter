package com.example.aalap.aalapstwitter;

import android.app.Application;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

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

    }
}
