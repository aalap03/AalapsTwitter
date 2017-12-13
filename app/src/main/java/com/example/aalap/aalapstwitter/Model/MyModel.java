package com.example.aalap.aalapstwitter.Model;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aalap.aalapstwitter.R;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
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
    TwitterApiClient twitterApiClient;
    private static final String TAG = "MyModel";

    public MyModel(Activity activity) {
        this.activity = activity;
        twitterApiClient = TwitterCore.getInstance().getApiClient();
    }

    public void showTweets(final LinearLayout linearLayout, String query) {
        Call<Search> tweets = twitterApiClient.getSearchService().tweets(query, null, null, null, null, 10, null, null, null, false);
        tweets.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                if (responseProcess(response)) {
                    displayTweetView(response.body().tweets, linearLayout);
                }
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    public void showReTweets(ViewGroup linearLayout){
        twitterApiClient.getStatusesService().retweetsOfMe(5, null, null, false, false, false)
                .enqueue(new Callback<List<Tweet>>() {
                    @Override
                    public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                        if(responseProcess(response)){
                            displayTweetView(response.body(), linearLayout);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Tweet>> call, Throwable t) {
                        Log.d(TAG, "onFailure: ");
                    }
                });
    }

    private void displayTweetView(List<Tweet> tweetList, ViewGroup linearLayout) {

        for (Tweet tweet : tweetList){
            TweetUtils.loadTweet(tweet.getId(), new com.twitter.sdk.android.core.Callback<Tweet>() {
                @Override
                public void success(Result<Tweet> result) {
                    linearLayout.addView(new TweetView(activity, result.data, R.style.tw__TweetDarkWithActionsStyle));
                }

                @Override
                public void failure(TwitterException exception) {
                    Toast.makeText(activity,
                            "Show tweet failure "+exception.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public boolean responseProcess(final Response response) {
        if (!response.isSuccessful()) {
            activity.runOnUiThread(() -> {
                try {
                    Log.d(TAG, "run: "+response.errorBody().string());
                    Toast.makeText(activity, "Error:" + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return false;
        } else {
            return true;
        }
    }
}
