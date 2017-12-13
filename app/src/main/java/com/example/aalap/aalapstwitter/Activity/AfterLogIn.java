package com.example.aalap.aalapstwitter.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.aalap.aalapstwitter.Model.MyModel;
import com.example.aalap.aalapstwitter.R;

public class AfterLogIn extends AppCompatActivity {

    private static final String TAG = "AfterLogIn";
    LinearLayout linearLayout;
    MyModel myModel;
    ScrollView viewGroup;
    EditText query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_log_in);

        myModel = new MyModel(this);
        linearLayout = findViewById(R.id.tweet_handler);
        viewGroup = findViewById(R.id.tweets);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        Log.d(TAG, "onCreate: " + bundle);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        linearLayout.removeAllViews();
        switch (item.getItemId()) {
            case R.id.hash_tag:
                myModel.showTweets(linearLayout, "#RohitSharma");
                break;
            case R.id.re_tweet:
                myModel.showReTweets(linearLayout);
                break;
        }
        return true;
    }
}
