package com.androidcai.administrator.piggymath;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class NewGameActivity extends AppCompatActivity {

    private int modeAnInt;
    private String userLoginString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

        modeAnInt = getIntent().getIntExtra("Mode", 0);
        Log.d("9May", "Mode ==> " + modeAnInt);
        userLoginString = getIntent().getStringExtra("Username");

        SharedPreferences sharedPreferences = NewGameActivity.this.getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Mode", modeAnInt);
        editor.putString("Username", userLoginString);
        editor.commit();

//        Add Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentNewGameFragment, new ChooseLevelFragment())
                    .commit();
        }





    }
}
