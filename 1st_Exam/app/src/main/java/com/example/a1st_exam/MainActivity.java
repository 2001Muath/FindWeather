package com.example.a1st_exam;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView TV1;
    private TextView TV2;
    private TextView TV3;
    private TextView TV4;
    private Button BTN;
    private ViewDataBinding binding;
    final String URL = "https://jsonplaceholder.typicode.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        progressBar = findViewById(R.id.PB1);
        TV1 = findViewById(R.id.TV1);
        TV2 = findViewById(R.id.TV2);
        TV3 = findViewById(R.id.TV3);
        TV4 = findViewById(R.id.TV4);
        BTN = findViewById(R.id.BTN);



        JSONObject jsonObject;
        jsonObject = new JSONObject();
        String nameOfCountry = (String)
                jsonObject.get("country");
        long population = (Long) jsonObject.get("population");
        JSONArray listOfCities = (JSONArray)
                jsonObject.get("cities");
        Iterator<String> iterator = listOfCities.iterator();
        while (iterator.hasNext()) {

        }















        HttpURLConnection conn =
                (HttpURLConnection) requestURL.openConnection();


        ConnectivityManager connMgr = (ConnectivityManager)

                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            new DownloadWebpageTask().execute(stringUrl);

        } else {

            textView.setText("No network connection available.");

        }




    }

}
