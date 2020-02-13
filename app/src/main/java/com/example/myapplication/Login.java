package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {

    Button login;
    EditText username,password;
    String username1,password1, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login);
        username=findViewById(R.id.username);
        password=findViewById(R.id.pass);
        
    }

    public void login(View view) {
        username1=username.getText().toString();
        password1=password.getText().toString();
        BackgroundwWorker1 backgroundwWorker1 = new BackgroundwWorker1(getApplicationContext());
        backgroundwWorker1.execute("login", username1, password1);

        try {
            result = backgroundwWorker1.get();
            System.out.println(result);
            if(result != null){
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setMessage("Login Successful");
                alertDialog.create().show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("userID", username1);
                intent.putExtra("password", password1);
                startActivity(intent);
            }
        } catch(ExecutionException e){
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
