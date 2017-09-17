package com.example.aalap.aalapstwitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aalap.aalapstwitter.Model.MyModel;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.internal.TwitterApi;
import com.twitter.sdk.android.core.models.Configuration;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.TwitterCollection;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.AccountService;
import com.twitter.sdk.android.core.services.ConfigurationService;
import com.twitter.sdk.android.core.services.FavoriteService;
import com.twitter.sdk.android.core.services.ListService;
import com.twitter.sdk.android.core.services.MediaService;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AfterLogIn extends AppCompatActivity {

    private static final String TAG = "AfterLogIn";
    LinearLayout linearLayout;
    MyModel myModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_log_in);
        myModel = new MyModel(this);

        linearLayout = findViewById(R.id.tweet_handler);
        myModel.showTweets(linearLayout, "#WarHeroCritical");

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        Log.d(TAG, "onCreate: " + bundle);
    }
}
