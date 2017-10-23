package com.example.aalap.aalapstwitter.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.aalap.aalapstwitter.Model.MyModel;
import com.example.aalap.aalapstwitter.R;
import com.example.aalap.aalapstwitter.RetrofitUtils.RetrofitClient;
import com.example.aalap.aalapstwitter.RetrofitUtils.RetrofitService;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        query = findViewById(R.id.query);

        query.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE){
                    query.setVisibility(View.GONE);
                    viewGroup.setVisibility(View.VISIBLE);
                    myModel.showTweets(linearLayout, query.getText().toString());
                }
                return false;
            }
        });


        //myModel.showTweets(linearLayout, "#congleaderabusespm");

//        RetrofitService retrofitService = RetrofitClient.retrofitInstance().create(RetrofitService.class);
//        Log.d(TAG, "onCreate: ");
//        retrofitService.getresponse(1).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if(myModel.responseProcess(response)){
//                    try {
//                        Log.d(TAG, "onResponse: "+response.body().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            }
//        });

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
        switch(item.getItemId()){
            case R.id.search:
                Log.d(TAG, "onOptionsItemSelected: ");
                break;
        }
        return true;
    }
}
