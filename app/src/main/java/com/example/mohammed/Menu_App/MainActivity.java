package com.example.mohammed.Menu_App;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView catgegory_menu = findViewById(R.id.category_menu);
        final TextView maki = findViewById(R.id.maki);
        maki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Open Maki Menu", Toast.LENGTH_SHORT).show();
                catgegory_menu.setImageResource(R.drawable.maki_category);
                maki.setTextColor(Color.RED);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.maki_menu);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent intent = new Intent(MainActivity.this, MakiActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

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
                        Intent intent = new Intent(MainActivity.this, SoupActivity.class);
                        startActivity(intent);
                    }});

            }});*/

       /* TextView family = findViewById(R.id.tampura);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "open tempura activity", Toast.LENGTH_SHORT).show();


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
                Toast.makeText(MainActivity.this, "open donburi activity", Toast.LENGTH_SHORT).show();

                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.donburi_menu);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent intent = new Intent(MainActivity.this, DonburiActivity.class);
                        startActivity(intent);
                    }
                });


            }
        });*/

    public void releaseMediaPlayer(){if(mediaPlayer != null){mediaPlayer.release();mediaPlayer = null;}}

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}




