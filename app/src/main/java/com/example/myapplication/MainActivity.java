package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button tutorial, game;
    String userID, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tutorial = findViewById(R.id.tutorial);
        game = findViewById(R.id.game);
        userID = getIntent().getStringExtra("userID");
        password = getIntent().getStringExtra("password");
    }

    public void tutorial(View view) {
        Intent intent = new Intent(getApplicationContext(), Tutorial.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }

    public void game(View view) {
        Intent intent = new Intent(getApplicationContext(), Game.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }
}
