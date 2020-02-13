package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;

public class BackgroundWorker6 extends AsyncTask<String, Void, String> {

    int j = 0;
    String line = "";

    Context context;

    TextView tv;

    BackgroundWorker6(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        final String type = strings[0];
        final String getstock_url = "http://10.0.2.2:8080/buy.php";


        if (type.equals("buy")) {
            try {
                String userID = strings[1];
                String quantity = strings[2];
                String stockname = strings[3];
                String current = strings[4];
                System.out.println(userID);
                System.out.println(quantity);
                System.out.println(stockname);
                System.out.println(current);
                URL url = new URL(getstock_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setInstanceFollowRedirects(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("userID", "UTF-8") + "=" + URLEncoder.encode(userID, "UTF-8") + "&" +
                        URLEncoder.encode("quantity", "UTF-8") + "=" + URLEncoder.encode(quantity, "UTF-8") + "&" +
                        URLEncoder.encode("stockname", "UTF-8") + "=" + URLEncoder.encode(stockname, "UTF-8") + "&" +
                        URLEncoder.encode("current", "UTF-8") + "=" + URLEncoder.encode(current, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                line = bufferedReader.readLine();
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                System.out.println("OUTPUT" + line);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return line;

    }
}