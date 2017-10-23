package com.example.aalap.aalapstwitter.Activity;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aalap.aalapstwitter.RetrofitUtils.CustomTwitterApiCLient;
import com.example.aalap.aalapstwitter.Model.MyModel;
import com.example.aalap.aalapstwitter.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetui.internal.VideoView;

public class MainActivity extends AppCompatActivity {

    private static final int CAM_CAPTURE = 1;
    TwitterLoginButton loginButton;
    private static final String TAG = "MainActivity";
    MyModel myModel;
    ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myModel = new MyModel(this);
        loginButton = findViewById(R.id.twitter_login);
        viewGroup = findViewById(R.id.root);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession twitterSession = result.data;
                CustomTwitterApiCLient apiCLient = new CustomTwitterApiCLient(twitterSession);

            }

            @Override
            public void failure(TwitterException exception) {
                Log.d(TAG, "failure: " + exception);
                exception.printStackTrace();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAM_CAPTURE) {
            Log.d(TAG, "onActivityResult: "+data);
            VideoView videoView = new VideoView(this);
            videoView.setVideoURI(data.getData(), true);
            videoView.start();
            viewGroup.addView(videoView);

        } else {
            loginButton.onActivityResult(requestCode, resultCode, data);
            Intent intent = new Intent(this, AfterLogIn.class);
            intent.putExtra("bundle", data.getExtras());
            startActivity(intent);
        }
    }

    //        button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
//                startActivityForResult(intent, CAM_CAPTURE);
//            }
//        });
}
