package com.example.mohammed.Menu_App;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MakiSubActivity_Arabic extends AppCompatActivity {

    GridView gridView;
    MediaPlayer mMediaPlayer;

    private AudioManager myAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ){
                        // Temp loss of audio focus
                        // Pause playback immediately
                        mMediaPlayer.pause();
                        // Restart Audio File by seeking to zero sec
                        mMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Strat Audio File
                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // stop mediaplayer and clear resources
                        releaseMediaPlayer();

                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_item_list);
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Menu_Item> menuItems  = new ArrayList<Menu_Item>();
        menuItems.add( new Menu_Item("اورا ماكي","أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي ",R.drawable.ura_grilled_salmon,R.raw.ura_grilled_salmon) );
        menuItems.add( new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي",R.drawable.ura_grilled_salmon,R.raw.ura_grilled_salmon) );
        menuItems.add( new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي",R.drawable.ura_grilled_salmon,R.raw.ura_grilled_salmon) );
        menuItems.add( new Menu_Item("اورا ماكي ممتاز","Ura Grilled Salmon   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي",R.drawable.ura_grilled_salmon,R.raw.ura_grilled_salmon) );
        menuItems.add( new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي",R.drawable.ura_grilled_salmon,R.raw.ura_grilled_salmon) );
        menuItems.add( new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي",R.drawable.ura_grilled_salmon,R.raw.ura_grilled_salmon) );
        menuItems.add( new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي",R.drawable.ura_grilled_salmon,R.raw.ura_grilled_salmon) );
        menuItems.add( new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي",R.drawable.ura_grilled_salmon,R.raw.ura_grilled_salmon) );
        menuItems.add( new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي",R.drawable.ura_grilled_salmon,R.raw.ura_grilled_salmon) );

        MenuItemAdapter adapter = new MenuItemAdapter (this,menuItems ,R.color.category_maki);
        gridView =  findViewById(R.id.list);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Menu_Item menuItem = menuItems.get(position);
                releaseMediaPlayer();
                int result = myAudioManager.requestAudioFocus( mOnAudioFocusChangeListener  ,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if ( result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mMediaPlayer = MediaPlayer.create(MakiSubActivity_Arabic.this,menuItem.getmAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    });
                }

            }
        });


    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            myAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        } }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
