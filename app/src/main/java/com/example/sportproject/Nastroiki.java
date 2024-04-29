package com.example.sportproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Nastroiki extends AppCompatActivity {
    ImageView imageyvedo;
    ImageView imagesbros;
    @Override
    @SuppressLint("MissingInflatedId")


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nastroiki);
        Switch keepScreenOnSwitch = findViewById(R.id.switchscrin);
        keepScreenOnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                } else {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nastroiprofi);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.treniprofi) {
                startActivity(new Intent(getApplicationContext(),GlavMenu.class));
                finish();
                return true;
            } else if (id == R.id.otchetprofi) {
                startActivity(new Intent(getApplicationContext(), Otchet.class));
                finish();
                return true;
            } else if (id == R.id.nastroiprofi) {

                return true;
            }
            return false;
        });
        imageyvedo = findViewById(R.id.imageyvedo);
        imageyvedo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Nastroiki.this, Yvedo.class);
                startActivity(intent);
            }
        });
        imagesbros = findViewById(R.id.imagesbros);
        imagesbros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("TrainingResults", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Устанавливаем результат для очистки списка в активности Otchet
                Intent intent = new Intent();
                intent.putExtra("clearList", true);
                setResult(RESULT_OK, intent);
            }
        });


    }






}
