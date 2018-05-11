package com.androidcai.administrator.piggymath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CategoryActivity extends AppCompatActivity implements View.OnClickListener {

    String userString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        userString = getIntent().getStringExtra("Username");

        ImageView imageView1 = (ImageView) findViewById(R.id.imvCat1);
        ImageView imageView2 = (ImageView) findViewById(R.id.imvCat2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imvCat3);
        ImageView imageView4 = (ImageView) findViewById(R.id.imvCat4);
        ImageView imageView5 = (ImageView) findViewById(R.id.imvCat5);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Old Game
                Intent intent = new Intent(CategoryActivity.this, choose_level.class);
                intent.putExtra("Username", userString);
                startActivity(intent);
            }
        });



        imageView2.setOnClickListener(CategoryActivity.this);
        imageView3.setOnClickListener(CategoryActivity.this);
        imageView4.setOnClickListener(CategoryActivity.this);
        imageView5.setOnClickListener(CategoryActivity.this);


    }

    @Override
    public void onClick(View v) {

        int modeInt = 0;
        Intent intent = new Intent(CategoryActivity.this, NewGameActivity.class);

        switch (v.getId()) {

            case R.id.imvCat2:
                modeInt = 0;
                break;
            case R.id.imvCat3:
                modeInt = 1;
                break;
            case R.id.imvCat4:
                modeInt = 2;
                break;
            case R.id.imvCat5:
                modeInt = 3;
                break;
        }

        intent.putExtra("Mode", modeInt);
        intent.putExtra("Username", userString);
        startActivity(intent);


    }
}
