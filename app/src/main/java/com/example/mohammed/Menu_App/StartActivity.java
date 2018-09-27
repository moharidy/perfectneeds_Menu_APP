package com.example.mohammed.Menu_App;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    ImageView langauge_image_view;
    TextView arabic_text_view;
    TextView english_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        arabic_text_view = findViewById(R.id.arabic_text_view);
        english_text_view = findViewById(R.id.english_text_view);
        langauge_image_view = findViewById(R.id.lang_image_view);
        langauge_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arabic_text_view.setVisibility(View.VISIBLE);
                english_text_view.setVisibility(View.VISIBLE);

                english_text_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(StartActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

                arabic_text_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(StartActivity.this, MainActivity_Arabic.class);
                        startActivity(intent);

                    }
                });


            }
        });
    }
}
