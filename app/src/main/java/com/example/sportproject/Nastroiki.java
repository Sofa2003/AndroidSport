package com.example.sportproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Nastroiki extends AppCompatActivity {
    ImageView imageyvedo;

    @Override
    @SuppressLint("MissingInflatedId")


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nastroiki);

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

    }






}