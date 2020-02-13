package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class Game extends AppCompatActivity {

    String price, userID, amount;
    TextView tv1, tv2, tv3, amt;
    int i = 0;
    String[] rates;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        userID = getIntent().getStringExtra("userID");
        System.out.println(userID + "yayyyyyyy");
        amt = findViewById(R.id.amount);
        System.out.println(amt);

        BackgroundWorker5 backgroundWorker5 = new BackgroundWorker5(getApplicationContext());
        backgroundWorker5.execute("getamt", userID);
        try {
            amount = backgroundWorker5.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        amt.setText(amount);

        BackgroundWorker backgroundWorker = new BackgroundWorker(getApplicationContext());
        backgroundWorker.execute("getstock1");
        try {
            price = backgroundWorker.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rates = price.split(":");
//        tv1 = findViewById(R.id.price1);
        //tv.setText(rates[0]);
//        i = 1;
        i = 0;

        handler.postDelayed(new Runnable() {
            public void run() {

                tv1 = findViewById(R.id.price1);
                tv1.setText(rates[i % 240]);
                i++;
                handler.postDelayed(this, 5000);
            }
            }, 5000);

        BackgroundWorker2 backgroundWorker2 = new BackgroundWorker2(getApplicationContext());
        backgroundWorker2.execute("getstock2");
        try {
            price = backgroundWorker2.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rates = price.split(":");
//        tv = findViewById(R.id.price1);
        //tv.setText(rates[0]);
        //i = 1;
        i = 0;
        handler.postDelayed(new Runnable() {
            public void run() {

                tv2 = findViewById(R.id.price2);
                tv2.setText(rates[i % 250]);
                i++;
                handler.postDelayed(this, 5000);
            }
        }, 5000);


        BackgroundWorker3 backgroundWorker3 = new BackgroundWorker3(getApplicationContext());
        backgroundWorker3.execute("getstock3");
        try {
            price = backgroundWorker3.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rates = price.split(":");
//        tv = findViewById(R.id.price1);
        //tv.setText(rates[0]);
        //i = 1;
        i = 0;

        handler.postDelayed(new Runnable() {
            public void run() {

                tv3 = findViewById(R.id.price3);
                tv3.setText(rates[i % 243]);
                i++;
                handler.postDelayed(this, 5000);
            }
        }, 5000);

    }

    public void buy(View view) {
        String s1, s2, s3;
        s1 = tv1.getText().toString();
        s2 = tv2.getText().toString();
        s3 = tv3.getText().toString();
        Intent intent = new Intent(getApplicationContext(), Buy.class);
        intent.putExtra("userID", userID);
        intent.putExtra("maruti", s1);
        intent.putExtra("motilal", s2);
        intent.putExtra("yesbank", s3);
        intent.putExtra("amount", amount);
        startActivity(intent);
    }

    public void sell(View view) {
        String s1, s2, s3;
        s1 = tv1.getText().toString();
        s2 = tv2.getText().toString();
        s3 = tv3.getText().toString();
        Intent intent = new Intent(getApplicationContext(), Sell.class);
        intent.putExtra("userID", userID);
        intent.putExtra("maruti", s1);
        intent.putExtra("motilal", s2);
        intent.putExtra("yesbank", s3);
        intent.putExtra("amount", amount);
        startActivity(intent);
    }
}

