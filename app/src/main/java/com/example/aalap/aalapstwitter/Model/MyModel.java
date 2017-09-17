package com.example.aalap.aalapstwitter.Model;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aalap.aalapstwitter.AfterLogIn;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aalap on 2017-09-16.
 */

public class MyModel {

    Activity activity;

    public MyModel(Activity activity) {
        this.activity = activity;
    }

    public void showTweets(final LinearLayout linearLayout, String query) {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        Call<Search> tweets = twitterApiClient.getSearchService().tweets(query, null, null, null, null, null, null, null, null, false);
        tweets.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if (responseProcess(response)) {
                    List<Tweet> tweets1 = response.body().tweets;
                    for (Tweet tweet : tweets1) {

                        TweetUtils.loadTweet(tweet.getId(), new com.twitter.sdk.android.core.Callback<Tweet>() {
                            @Override
                            public void success(final Result<Tweet> result) {
                                linearLayout.addView(new CompactTweetView(activity, result.data));
                            }

                            @Override
                            public void failure(TwitterException exception) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
    }

    boolean responseProcess(final Response response) {
        if (!response.isSuccessful()) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Toast.makeText(activity, "Error:" + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return false;
        } else {
            return true;
        }
    }


}
