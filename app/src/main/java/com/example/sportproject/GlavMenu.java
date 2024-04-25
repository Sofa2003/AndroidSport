package com.example.sportproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportproject.databinding.ActivityGlavmenuBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class GlavMenu extends AppCompatActivity {
    TextView textdate;
    TextView textprivet;

    ActivityGlavmenuBinding binding;
    int[] imageId = {R.drawable.sportkobra,R.drawable.sportotjimanikoleni,R.drawable.sportplanka};
    String[] name ={"Кобра","Отжимание с упором на колени","Планка"};

    String[] timetreni = {"30","60","60"};
    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        binding = ActivityGlavmenuBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        final Calendar calendar = Calendar.getInstance();

        textdate = findViewById(R.id.textdate);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateTime = simpleDateFormat.format(calendar.getTime());
        textdate.setText("Сегодня: "+dateTime);
        textprivet= findViewById(R.id.textprivet);
        final Handler handler = new Handler();
        Runnable updateTime = new Runnable() {
            public void run() {
                int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

                if (timeOfDay >= 5 && timeOfDay < 11) {
                    textprivet.setText("Доброе утро");
                } else if (timeOfDay >= 11 && timeOfDay < 16) {
                    textprivet.setText("Добрый день");
                } else if (timeOfDay >= 16 && timeOfDay < 21) {
                    textprivet.setText("Добрый вечер");
                } else {
                    textprivet.setText("Доброй ночи");
                }

                handler.postDelayed(this, 60000); // Обновление каждую минуту
            }
        };updateTime.run();






        ArrayList<Treni> treniArrayList =new ArrayList<>();
        for(int i = 0;i<imageId.length;i++){
            Treni treni = new Treni(name[i],timetreni[i],imageId[i]);
            treniArrayList.add(treni);

        }
        ListAdapter listAdapter = new ListAdapter(GlavMenu.this,treniArrayList);
        binding.listspisok.setAdapter(listAdapter);
        binding.listspisok.setClickable(true);
        binding.listspisok.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(GlavMenu.this,TreniActivity.class);
                i.putExtra("name",name[position]);
                i.putExtra("timetreni",timetreni[position]);
                i.putExtra("imageId",imageId[position]);
                startActivity(i);

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.treniprofi) {
                return true;
            } else if (id == R.id.otchetprofi) {
                startActivity(new Intent(getApplicationContext(), Otchet.class));
                finish();
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
