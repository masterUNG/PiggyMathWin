package com.androidcai.administrator.piggymath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class ShowAnswer extends AppCompatActivity {
    private TextView txtShowScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_answer);
        txtShowScore  =  (TextView) findViewById(R.id.txtShowscore);
        txtShowScore.setText(String.valueOf(getIntent().getExtras().getInt("score")));

    }
    public void ClickPlayAgain(View view) {
        Intent objintent = new Intent(ShowAnswer.this, Time.class);
        startActivity(objintent);
        finish();

    }//Playagain
    public void ClickExit(View view){
        finish();
    }//Exit

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
