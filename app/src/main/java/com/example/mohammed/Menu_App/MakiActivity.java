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

public class MakiActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maki);


        final ImageView catgegory_menu = findViewById(R.id.category_menu);
        final TextView ura_maki = findViewById(R.id.ura_maki);
        ura_maki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MakiActivity.this, "Open Maki Menu", Toast.LENGTH_SHORT).show();
                catgegory_menu.setImageResource(R.drawable.maki_category);
                ura_maki.setTextColor(Color.RED);
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(MakiActivity.this, R.raw.ura_maki_menu);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Intent intent = new Intent(MakiActivity.this, MakiSubActivity.class);
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



