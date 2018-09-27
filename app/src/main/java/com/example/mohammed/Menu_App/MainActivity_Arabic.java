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

public class MainActivity_Arabic extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__arabic);

        final ImageView catgegory_menu = findViewById(R.id.category_menu);
        final TextView maki = findViewById(R.id.maki);
        maki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity_Arabic.this, "قائمة ماكي", Toast.LENGTH_SHORT).show();
                catgegory_menu.setImageResource(R.drawable.maki_category);
                maki.setTextColor(Color.RED);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(MainActivity_Arabic.this, R.raw.maki_menu);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent intent = new Intent(MainActivity_Arabic.this, MakiActivity_Arabic.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }


    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}




