package com.example.sportproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportproject.databinding.ActivityGlavmenuBinding;
import com.example.sportproject.databinding.ActivityOtchetBinding;
import com.example.sportproject.databinding.ActivityTreniBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Otchet extends AppCompatActivity {
    ActivityOtchetBinding binding;
    ImageView imageback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            binding = ActivityOtchetBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());




        SharedPreferences sharedPreferences = getSharedPreferences("TrainingResults", Context.MODE_PRIVATE);
        List<String> data = new ArrayList<>();



        data.add(sharedPreferences.getString("name", "") + "\n" + sharedPreferences.getString("datanow", ""));

        ListAdapterIstoria adapterIstoria = new ListAdapterIstoria(this,data);
        ListView listView = findViewById(R.id.lististoria);
        listView.setAdapter(adapterIstoria);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.otchetprofi);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.treniprofi) {
                startActivity(new Intent(getApplicationContext(),GlavMenu.class));
                finish();
                return true;
            } else if (id == R.id.otchetprofi) {

                return true;
            } else if (id == R.id.nastroiprofi) {
                startActivity(new Intent(getApplicationContext(), Nastroiki.class));
                finish();
                return true;
            }
            return false;
        });
    }
}
