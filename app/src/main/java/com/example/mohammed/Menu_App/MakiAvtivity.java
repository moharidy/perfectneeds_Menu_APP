package com.example.mohammed.Menu_App;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MakiAvtivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maki);

        /*TextView soup = findViewById(R.id.soup);
        soup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "open soup activity", Toast.LENGTH_SHORT).show();

                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.soup_menu);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent intent = new Intent(MainActivity.this, MakiAvtivity.class);
                        startActivity(intent);
                    }});

            }});*/

       /* TextView family = findViewById(R.id.tampura);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "open family activity", Toast.LENGTH_SHORT).show();


                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.tempura_menu);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent intent = new Intent(MainActivity.this, TempuraActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });*/

        /*TextView colors = findViewById(R.id.donburi);
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "open colors activity", Toast.LENGTH_SHORT).show();

                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.donburi_menu);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent intent = new Intent(MainActivity.this, Colorsactivity.class);
                        startActivity(intent);
                    }
                });


            }
        });*/

        final ImageView catgegory_menu = findViewById(R.id.category_menu);
        final TextView ura_maki = findViewById(R.id.ura_maki);
        ura_maki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MakiAvtivity.this, "Open Maki Menu", Toast.LENGTH_SHORT).show();
                catgegory_menu.setImageResource(R.drawable.maki_category);
                ura_maki.setTextColor(Color.RED);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(MakiAvtivity.this,R.raw.ura_maki_menu);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent intent = new Intent(MakiAvtivity.this, MakiSubActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }


    public void releaseMediaPlayer(){if(mediaPlayer != null){mediaPlayer.release();mediaPlayer = null;}}

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}



