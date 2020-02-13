package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tutorial extends AppCompatActivity {

    View view;
    TextView balance, stock1, stock2, stock3, price1, price2, price3;
    Context ctx;
    Button buy, sell;
    String userID;
    Intent intenty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        userID = getIntent().getStringExtra("userID");
        ctx = getApplicationContext();
        balance = findViewById(R.id.balance);
        stock1 = findViewById(R.id.stock1);
        stock2 = findViewById(R.id.stock2);
        stock3 = findViewById(R.id.stock3);
        price1 = findViewById(R.id.price1);
        price2 = findViewById(R.id.price2);
        price3 = findViewById(R.id.price3);
        buy = findViewById(R.id.buy);
        sell = findViewById(R.id.sell);
        intenty = new Intent(getApplicationContext(), Game.class);
        intenty.putExtra("userID", userID);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Welcome to the tutorial!");
        alert.setPositiveButton("Start", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case DialogInterface.BUTTON_POSITIVE:{
                                amount(view);
                            }
                        }
                    }
                });
        alert.setCancelable(true);
        alert.create().show();

    }

    public void FAQ(View view) {
        Intent intent = new Intent(getApplicationContext(), FAQ.class);
        startActivity(intent);
    }

    public void amount(View view) {
        balance.setBackgroundColor(Color.rgb(207,168,232));
        AlertDialog.Builder alert1 = new AlertDialog.Builder(this);
        final AlertDialog.Builder alert2 = new AlertDialog.Builder(this);
        final AlertDialog.Builder alert3 = new AlertDialog.Builder(this);
        final AlertDialog.Builder alert4 = new AlertDialog.Builder(this);
        final AlertDialog.Builder alert5 = new AlertDialog.Builder(this);
        alert1.setMessage("This shows your current balance after considering all trades");
        alert1.setPositiveButton("Next", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case DialogInterface.BUTTON_POSITIVE:{
                        balance.setBackgroundColor(Color.rgb(255, 255, 255));
                        stock1.setBackgroundColor(Color.rgb(207,168,232));
                        stock2.setBackgroundColor(Color.rgb(207,168,232));
                        stock3.setBackgroundColor(Color.rgb(207,168,232));

//                        AlertDialog.Builder alertt2 = new AlertDialog.Builder(ctx);
                        alert2.setMessage("These are the stocks that you can trade in");
                        alert2.setPositiveButton("Next", new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i){
                                    case DialogInterface.BUTTON_POSITIVE:{
                                        stock1.setBackgroundColor(Color.rgb(255, 255, 255));
                                        stock2.setBackgroundColor(Color.rgb(255, 255, 255));
                                        stock3.setBackgroundColor(Color.rgb(255, 255, 255));
                                        price1.setBackgroundColor(Color.rgb(207,168,232));
                                        price2.setBackgroundColor(Color.rgb(207,168,232));
                                        price3.setBackgroundColor(Color.rgb(207,168,232));

//                                        AlertDialog.Builder alert3 = new AlertDialog.Builder(ctx);
                                        alert3.setMessage("These are the prices corresponding to the stocks");
                                        alert3.setPositiveButton("Next", new DialogInterface.OnClickListener(){

                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                switch (i){
                                                    case DialogInterface.BUTTON_POSITIVE:{
                                                        price1.setBackgroundColor(Color.rgb(255, 255, 255));
                                                        price2.setBackgroundColor(Color.rgb(255, 255, 255));
                                                        price3.setBackgroundColor(Color.rgb(255, 255, 255));
                                                        buy.setBackgroundColor(Color.rgb(207,168,232));

//                                                        AlertDialog.Builder alert4 = new AlertDialog.Builder(ctx);
                                                        alert4.setMessage("The buy button is used to place an order for a specific quantity of a stock");
                                                        alert4.setPositiveButton("Next", new DialogInterface.OnClickListener(){

                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                switch (i){
                                                                    case DialogInterface.BUTTON_POSITIVE:{
                                                                        buy.setBackgroundColor(Color.rgb(255, 255, 255));
                                                                        sell.setBackgroundColor(Color.rgb(207,168,232));

//                                                                        AlertDialog.Builder alert5 = new AlertDialog.Builder(ctx);
                                                                        alert5.setMessage("The sell button is used to clear the position of an already bought stock.");
                                                                        alert5.setPositiveButton("Start Game", new DialogInterface.OnClickListener(){

                                                                            @Override
                                                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                                                switch (i){
                                                                                    case DialogInterface.BUTTON_POSITIVE:{
                                                                                        sell.setBackgroundColor(Color.rgb(255, 255, 255));
                                                                                        startActivity(intenty);
                                                                                    }
                                                                                }
                                                                            }
                                                                        });
                                                                        alert5.create().show();
                                                                    }
                                                                }
                                                            }
                                                        });
                                                        alert4.create().show();
                                                    }
                                                }
                                            }
                                        });
                                        alert3.create().show();
                                    }
                                }
                            }
                        });
                        alert2.create().show();
                    }
                }
            }
        });
        alert1.create().show();
    }

}
