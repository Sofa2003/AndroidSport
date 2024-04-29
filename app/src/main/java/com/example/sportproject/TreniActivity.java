package com.example.sportproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sportproject.databinding.ActivityTreniBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TreniActivity extends AppCompatActivity {
    ActivityTreniBinding binding;
    public ProgressBar progressbar;
    public CountDownTimer timer;
    private Button btnstart;
    private TextView texttime;
    Boolean isTimerOn = false;
    ImageView imageback;
    boolean isColorChanged  =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTreniBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        btnstart = findViewById(R.id.btnstart);
        progressbar = findViewById(R.id.progress_bar);
        texttime = findViewById(R.id.texttime);
        imageback = findViewById(R.id.imageback);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent image = new Intent(TreniActivity.this, GlavMenu.class);
                startActivity(image);
            }
        });



        Intent intent = this.getIntent();
        if (intent != null) {

            String name = intent.getStringExtra("name");
            String timetreni = intent.getStringExtra("timetreni");
            int imageId = intent.getIntExtra("imageId", R.drawable.sportkobra);


            binding.textname.setText(name);
            binding.texttime.setText("0:"+timetreni);
            binding.menuimage.setImageResource(imageId);

                        long milisinfuture = Long.parseLong(timetreni) * 1000;
                        timer = new CountDownTimer(milisinfuture, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {

                                int minutes = (int) millisUntilFinished / 1000 / 60;
                                int seconds = (int) millisUntilFinished / 1000 - minutes * 60;

                                if (seconds < 10) {
                                    texttime.setText(minutes + ":0" + seconds);
                                } else {
                                    texttime.setText(minutes + ":" + seconds);
                                }
                                progressbar.setMax((int) (milisinfuture / 1000 - minutes * 60));
                                progressbar.setProgress((int) (millisUntilFinished / 1000));


                            }

                            private String getCurrentDateTime() {
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault());
                                return sdf.format(calendar.getTime());
                            }


                            @Override
                            public void onFinish() {
                                texttime.setText("Обратный отсчет завершен!");
                                String currentDateTime = getCurrentDateTime(); // Получаем текущую дату и время
                                String datanow = "Дата и время порождения: " + currentDateTime;
                                SharedPreferences sharedPreferences = getSharedPreferences("TrainingResults", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("name", binding.textname.getText().toString());
                                editor.putString("datanow", datanow);
                                editor.apply();

                                Intent i = new Intent(TreniActivity.this, Otchet.class);
                                i.putExtra("name", "Упражнение завершено: " + binding.textname.getText().toString());
                                i.putExtra("datanow", datanow);
                                //startActivity(i);
                            }
                        };

                        btnstart.setOnClickListener(new View.OnClickListener()

                            {
                                @SuppressLint("ResourceAsColor")
                                @Override
                                public void onClick (View v){
                                if (!isColorChanged) {
                                    btnstart.setBackgroundColor(Color.RED);
                                } else {
                                    btnstart.setBackgroundColor(Color.GREEN);
                                }


                                isColorChanged = !isColorChanged;
                                if (!isTimerOn) {
                                    btnstart.setText("Стоп");
                                    isTimerOn = true;
                                    timer.start();
                                } else {
                                    onBackPressed();
                                    isTimerOn = false;
                                    progressbar.setProgress(0);
                                    timer.cancel();
                                }
                            }




                public void onDestroy() {
                    TreniActivity.super.onDestroy();
                    if (timer != null) {
                        timer.cancel();
                    }
                }
            });



        }

    }

}
