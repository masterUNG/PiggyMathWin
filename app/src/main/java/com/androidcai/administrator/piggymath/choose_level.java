package com.androidcai.administrator.piggymath;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class choose_level extends AppCompatActivity {
    public MediaPlayer startsound, clicksound;
    private ImageView img1, img2, img3, img4;
    private String Username;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_choose_level);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            Username = getIntent().getStringExtra("Username");
            startsound = MediaPlayer.create(getApplicationContext(), R.raw.bgm);
            clicksound = MediaPlayer.create(getApplicationContext(), R.raw.click);
            startsound.setLooping(true);
            startsound.start();
            img1 = (ImageView) findViewById(R.id.imageEasy);
            img2 = (ImageView) findViewById(R.id.imageNormal);
            img3 = (ImageView) findViewById(R.id.imageHard);
            img4 = (ImageView) findViewById(R.id.imageHard2);

            img1.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            clicksound.start();
                            img1.setImageResource(R.drawable.easy);
                            return true;
                        }
                        case MotionEvent.ACTION_UP: {
                            img1.setImageResource(R.drawable.easy);
                            startsound.stop();
                            Intent i = new Intent(choose_level.this, easy_mode.class);
                            i.putExtra("Username", Username);
                            startActivity(i);
                            return true;
                        }
                    }
                    return false;

                }
            });
            img2.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            clicksound.start();
                            img2.setImageResource(R.drawable.normal);
                            return true;
                        }
                        case MotionEvent.ACTION_UP: {
                            img2.setImageResource(R.drawable.normal);
                            startsound.stop();
                            Intent i = new Intent(choose_level.this, normal_mode.class);
                            i.putExtra("Username", Username);
                            startActivity(i);
                            return true;
                        }
                    }
                    return false;
                }
            });
            img3.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            clicksound.start();
                            img3.setImageResource(R.drawable.hard);
                            return true;
                        }
                        case MotionEvent.ACTION_UP: {
                            img3.setImageResource(R.drawable.hard);
                            startsound.stop();
                            Intent i = new Intent(choose_level.this, hard_mode.class);
                            i.putExtra("Username", Username);
                            startActivity(i);
                            return true;
                        }
                    }
                    return false;
                }
            });
            img4.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            clicksound.start();
                            img4.setImageResource(R.drawable.hard2_03);
                            return true;
                        }
                        case MotionEvent.ACTION_UP: {
                            img4.setImageResource(R.drawable.hard2_03);
                            startsound.stop();
                            Intent i = new Intent(choose_level.this, Time.class);
                            i.putExtra("Username", Username);
                            startActivity(i);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }

    public void onBackPressed() {
        android.os.Process.killProcess(android.os.Process.myPid());
        super.onBackPressed();
    }

    public void  onPause() {
        //When the screen is about to turn off
        startsound.pause();
        super.onPause();
    }

    protected void onResume(){
        //When the screen turn on
        startsound.start();
        super.onResume();
    }

}
