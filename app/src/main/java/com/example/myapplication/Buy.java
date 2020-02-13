package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class Buy extends AppCompatActivity {
    String s1, s2, s3, userID, amount, quantity, stockname, current, balance, newbalance, result;
    Spinner spinner;
    TextView curr;
    int n;
    EditText qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        s1 = getIntent().getStringExtra("maruti");
        s2 = getIntent().getStringExtra("motilal");
        s3 = getIntent().getStringExtra("yesbank");
        userID = getIntent().getStringExtra("userID");
        amount = getIntent().getStringExtra("amount");
        spinner = findViewById(R.id.spinner);
        qty = findViewById(R.id.qty);
        curr = findViewById(R.id.curr);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                n = spinner.getSelectedItemPosition();
                if(i == 0){
                    curr.setText(s1);
                    stockname = "maruti";
                } else if(i == 1){
                    curr.setText(s2);
                    stockname = "motilal";
                } else if(i == 2){
                    curr.setText(s3);
                    stockname = "yesbank";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                curr.setText(s1);
                stockname = "maruti";
            }
        });


    }

    public void buyy(View view) {
        quantity = qty.getText().toString();
        current = curr.getText().toString();
        BackgroundWorker6 backgroundWorker6 = new BackgroundWorker6(getApplicationContext());
        backgroundWorker6.execute("buy", userID, quantity, stockname, current);
        try {
            result = backgroundWorker6.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(result);

        BackgroundWorker7 backgroundWorker7 = new BackgroundWorker7(getApplicationContext());
        backgroundWorker7.execute("getbalance", userID);
        try {
            balance = backgroundWorker7.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(balance);

        newbalance = String.valueOf(Float.parseFloat(balance) - Float.parseFloat(current) * Float.parseFloat(quantity));

        BackgroundWorker8 backgroundWorker8 = new BackgroundWorker8(getApplicationContext());
        backgroundWorker8.execute("updatebalance", userID, newbalance);
        try {
            result = backgroundWorker6.get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(result);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Stocks Bought");
        alert.create().show();
        Intent intent = new Intent(getApplicationContext(), Game.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }
}
