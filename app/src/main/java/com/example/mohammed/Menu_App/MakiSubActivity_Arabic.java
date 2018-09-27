package com.example.mohammed.Menu_App;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
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
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
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
        myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Menu_Item> menuItems = new ArrayList<Menu_Item>();
        menuItems.add(new Menu_Item("اورا ماكي", "أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي ", R.drawable.ura_grilled_salmon, R.raw.ura_grilled_salmon));
        menuItems.add(new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي", R.drawable.ura_grilled_salmon, R.raw.ura_grilled_salmon));
        menuItems.add(new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي", R.drawable.ura_grilled_salmon, R.raw.ura_grilled_salmon));
        menuItems.add(new Menu_Item("اورا ماكي ممتاز", "Ura Grilled Salmon   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي", R.drawable.ura_grilled_salmon, R.raw.ura_grilled_salmon));
        menuItems.add(new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي", R.drawable.ura_grilled_salmon, R.raw.ura_grilled_salmon));
        menuItems.add(new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي", R.drawable.ura_grilled_salmon, R.raw.ura_grilled_salmon));
        menuItems.add(new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي", R.drawable.ura_grilled_salmon, R.raw.ura_grilled_salmon));
        menuItems.add(new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي", R.drawable.ura_grilled_salmon, R.raw.ura_grilled_salmon));
        menuItems.add(new Menu_Item("أورا سمك السلمون المشوي   30.00", "سلمون مشوي ، خيار ، مغطاة بسمسم وبذور ترياكي", R.drawable.ura_grilled_salmon, R.raw.ura_grilled_salmon));

        MenuItemAdapter adapter = new MenuItemAdapter(this, menuItems, R.color.category_maki);
        gridView = findViewById(R.id.list);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Menu_Item menuItem = menuItems.get(position);

                ImageView imageview = findViewById(R.id.image_view);
                zoomImageFromThumb(imageview, R.drawable.ura_grilled_salmon);
                releaseMediaPlayer();
                int result = myAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    mMediaPlayer = MediaPlayer.create(MakiSubActivity_Arabic.this, menuItem.getmAudioResourceId());
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
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void zoomImageFromThumb(final View thumbView, int imageResId) {
        // Hold a reference to the current animator,
        // so that it can be canceled mid-way.
        final Animator[] mCurrentAnimator = {null};

        // The system "short" animation time duration, in milliseconds. This
        // duration is ideal for subtle animations or animations that occur
        // very frequently.
        final int mShortAnimationDuration = 0;
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (mCurrentAnimator[0] != null) {
            mCurrentAnimator[0].cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) findViewById(
                R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container)
                .getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView,
                        View.SCALE_Y, startScale, 1f));

        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator[0] = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator[0] = null;
            }
        });
        set.start();
        mCurrentAnimator[0] = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator[0] != null) {
                    mCurrentAnimator[0].cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator[0] = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator[0] = null;
                    }
                });
                set.start();
                mCurrentAnimator[0] = set;
            }
        });
    }
}
