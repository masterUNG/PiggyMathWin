package com.androidcai.administrator.piggymath;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AbsoluteLayout;

import java.io.FileOutputStream;
import java.util.Random;



public class normal_mode extends AppCompatActivity {
    public int ans = 0, misAns1 = 0, misAns2 = 0, misAns3 = 0 ;
    public int anspos = 0;
    public int miss = 1;
    public int score = 0;
    public ImageView imgNum1, imgNum2, imgEq, ice;
    public TextView ans1, ans2, ans3, ans4, timer, scoreT;
    public AlertDialog.Builder dlgAlert;
    public CountDownTimer count;
    public MediaPlayer startsound, bgsound, correctsound, wrongsound, endsound;
    private String Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode);

        AbsoluteLayout ab = (AbsoluteLayout) findViewById(R.id.AbsoluteLayout);
        ab.setBackgroundResource(R.drawable.basicscene1);
        Username = getIntent().getStringExtra("Username");
        imgNum1  = (ImageView)  findViewById(R.id.imageView1);
        imgNum2  = (ImageView)  findViewById(R.id.imageView3);
        imgEq    = (ImageView)  findViewById(R.id.imageView2);
        ans1     = (TextView)   findViewById(R.id.textView2);
        ans2     = (TextView)   findViewById(R.id.textView3);
        ans3     = (TextView)   findViewById(R.id.textView4);
        ans4     = (TextView)   findViewById(R.id.textView5);
        scoreT   = (TextView)   findViewById(R.id.textView6);
        timer    = (TextView)   findViewById(R.id.textView7);
        ice      = (ImageView)  findViewById(R.id.icecream);
        dlgAlert = new AlertDialog.Builder(this);
        startsound = MediaPlayer.create(getApplicationContext(),R.raw.littleidea);
        bgsound   = MediaPlayer.create(getApplicationContext(),R.raw.bgm);
        correctsound = MediaPlayer.create(getApplicationContext(),R.raw.ding);
        wrongsound  = MediaPlayer.create(getApplicationContext(),R.raw.wrong);
        endsound   = MediaPlayer.create(getApplicationContext(),R.raw.endsound);

        startsound.setLooping(true);
        bgsound.setLooping(true);
        bgsound.start();

        count = new CountDownTimer(50000, 1) {
            @Override
            public void onTick(long millisUntiFinished) {
                timer.setText(String.valueOf(millisUntiFinished / 1000));

            }

            @Override
            public void onFinish() {
                endsound.start();
                dlgAlert.setTitle("TIME UP!!!");
                dlgAlert.setCancelable(false);
                dlgAlert.setMessage("Your score is " + score);
                dlgAlert.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bgsound.stop();
                        startsound.start();
                        finish();
                    }
                });
                saveScore();
                dlgAlert.create().show();
            }
        }.start();
        ans1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        return true;
                    }
                    case MotionEvent.ACTION_UP:{
                        CheckAns(1);
                        return true;
                    }
                }
                return false;
            }
        });
        ans2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        return true;
                    }
                    case MotionEvent.ACTION_UP:{
                        CheckAns(2);
                        return true;
                    }
                }
                return false;
            }
        });
        ans3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        return true;
                    }
                    case MotionEvent.ACTION_UP:{
                        CheckAns(3);
                        return true;
                    }
                }
                return false;
            }
        });
        ans4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        return true;
                    }
                    case MotionEvent.ACTION_UP:{
                        CheckAns(4);
                        return true;
                    }
                }
                return false;
            }
        });

        RandAnswer();
    }
    private void RandAnswer(){
        int a, b, eq;
        float tans = 0;
        Random rnd = new Random();

        eq = rnd.nextInt(4) + 1;
        do{
            a = rnd.nextInt(8) + 1;
            b = rnd.nextInt(8) + 1 ;
            switch (eq){
                case 1:
                    tans = a + b;
                    break;
                case 2:
                    tans = a - b;
                    break;
                case 3:
                    tans = a * b;
                    break;
                case 4:
                    tans = a / (float) b;
                    break;
            }
        }while (tans != 1.0 && tans != 2.0 && tans != 3.0 && tans != 4.0
                && tans != 5.00 && tans != 6.00 && tans != 7.00 && tans != 8.00
                && tans != 9.00 && tans != 10.00);
        ans = (int) tans;
        do{
            misAns1 = rnd.nextInt(10) + 1;
        }while (ans == misAns1);
        do{
            misAns2 = rnd.nextInt(10) + 1;
        }while (ans == misAns2 || misAns1 == misAns2);
        do{
            misAns3 = rnd.nextInt(10) + 1;
        }while (ans == misAns3 || misAns1 == misAns3 || misAns2 == misAns3);
        imgNum1.setImageResource(getResourcesId(this, "num" + a, "drawable"));
        imgNum2.setImageResource(getResourcesId(this, "num" + b, "drawable"));
        switch (eq){
            case 1:
                imgEq.setImageResource(getResourcesId(this, "plus", "drawable"));
                break;
            case 2:
                imgEq.setImageResource(getResourcesId(this, "minus", "drawable"));
                break;
            case 3:
                imgEq.setImageResource(getResourcesId(this, "multiply", "drawable"));
                break;
            case 4:
                imgEq.setImageResource(getResourcesId(this, "devide", "drawable"));
                break;
        }
        anspos = rnd.nextInt(4) + 1;
        if(anspos == 1){
            ans1.setText(String.valueOf(ans));
            ans2.setText(String.valueOf(misAns1));
            ans3.setText(String.valueOf(misAns2));
            ans4.setText(String.valueOf(misAns3));
        }else if(anspos == 2){
            ans1.setText(String.valueOf(misAns1));
            ans2.setText(String.valueOf(ans));
            ans3.setText(String.valueOf(misAns2));
            ans4.setText(String.valueOf(misAns3));
        }else if (anspos == 3){
            ans1.setText(String.valueOf(misAns1));
            ans2.setText(String.valueOf(misAns2));
            ans3.setText(String.valueOf(ans));
            ans4.setText(String.valueOf(misAns3));
        }else if (anspos == 4){
            ans1.setText(String.valueOf(misAns1));
            ans2.setText(String.valueOf(misAns2));
            ans3.setText(String.valueOf(misAns3));
            ans4.setText(String.valueOf(ans));
        }
    }
    public void CheckAns(int val){
        if(val != anspos){
            wrongsound.start();
            ice.setImageResource(getResourcesId(this, /*"normal"*/"eat" + ++miss, "drawable"));
            if(miss == 6){
                count.cancel();
                endsound.start();
                dlgAlert.setTitle("GAME OVER!!");
                dlgAlert.setCancelable(false);
                dlgAlert.setMessage("Your score is " + score);
                dlgAlert.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bgsound.stop();
                        startsound.start();
                        finish();
                    }
                });
                saveScore();
                dlgAlert.create().show();
            }
            RandAnswer();
        }else {
            correctsound.start();
            scoreT.setText("score : " + ++score);
            RandAnswer();
        }
    }

    private void saveScore(){
        FileOutputStream fos;
        String  strFileName = "score.txt";
        String strFileContents = Username + "\t\t\t\t\t\t Normal \t\t\t\t\t\t" + score +"\n";
        try {
            fos = openFileOutput(strFileName, MODE_APPEND);
            fos.write(strFileContents.getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static int getResourcesId(Context context, String name, String resourceType) {
        return  context.getResources().getIdentifier(name, resourceType, context.getPackageName());
    }
    public  void  onBackPressed(){
        count.cancel();
        bgsound.stop();
        startsound.start();
        super.onBackPressed();
    }
    protected void onPause(){
        bgsound.pause();
        super.onPause();
    }
    protected void  onResume(){
        bgsound.start();
        super.onResume();



    }

}
