package com.example.aalap.aalapstwitter;

import android.app.Application;

import com.twitter.sdk.android.core.Twitter;

/**
 * Created by aalap on 2017-09-13.
 */

public class AalapsTwitter extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Twitter.initialize(this);
    }
}
