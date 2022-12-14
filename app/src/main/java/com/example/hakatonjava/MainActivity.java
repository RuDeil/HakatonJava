package com.example.hakatonjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private OkHttpClient client;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        client = new OkHttpClient();
        Flow.Subscription subscription;



        final EditText ETLoginNumber = findViewById(R.id.ETLoginNumber);
        final EditText ETLoginPass = findViewById(R.id.ETLoginPass);
        final Button btnLogin = findViewById(R.id.btnLogin);
        textView = findViewById(R.id.result);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    public void run() {
                        getWebservice();
                    }
                }).start();






    }

    private void getWebservice() {
        String UserNumber = ETLoginNumber.getText().toString();
        String PassNumber = ETLoginPass.getText().toString();

        RequestBuilder requestBuilder = new RequestBuilder();
        RequestBody requestBody = requestBuilder.LoginBody(UserNumber, PassNumber, "");
        HttpUrl url = requestBuilder.buildURL();
        ApiCall apiCall = new ApiCall();

        client = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        try {
            apiCall.POST(client, url,requestBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        });

    }


}