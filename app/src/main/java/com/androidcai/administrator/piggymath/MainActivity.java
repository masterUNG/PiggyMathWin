package com.androidcai.administrator.piggymath;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> listitems = new ArrayList<String>();
    private ListView myListView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        myListView = (ListView) findViewById(R.id.ListView);
        adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_black_text, R.id.list_content, listitems);
        try {
            FileInputStream fis = openFileInput("score.txt");
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String strLine = "";
            while ((strLine = br.readLine())!= null){
                listitems.add(strLine);
            }
            Collections.reverse(listitems);
            adapter.notifyDataSetChanged();
            myListView.setAdapter(adapter);
            br.close();
            isr.close();
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void  btRegisterClicked(View v){
        final Dialog register = new Dialog(this);
        register.setContentView(R.layout.dialog);
        register.setTitle("Register".toUpperCase());

        Button btnRegister = (Button) register.findViewById(R.id.btnSubmit);
        Button btnCancel = (Button) register.findViewById(R.id.btnCancel);
        final EditText txtUsername = (EditText) register.findViewById(R.id.txtUsername);
        final EditText txtPassword = (EditText) register.findViewById(R.id.txtPassword);
        btnRegister.setText("Register");
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = txtUsername.getText().toString();
                String Password = txtPassword.getText().toString();

                if(fileExists(getApplicationContext(), Username)){
                    Toast.makeText(getApplicationContext(), "ไม่สามารถลงทะเบียนได้หรือชื่อผู้ใช้ซ้ำ",Toast.LENGTH_SHORT).show();
                }else{
                    FileOutputStream fos;
                    String strFileName = Username;
                    String strFileContents = Password;
                    try{
                        fos = openFileOutput(strFileName, MODE_PRIVATE);
                        fos.write(strFileContents.getBytes());
                        fos.close();
                        Toast.makeText(getApplicationContext(), "ลงทะเบียนเสร็จสมบูรณ์",Toast.LENGTH_SHORT).show();
                        register.dismiss();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register.dismiss();
            }
        });
        register.show();
    }

    public void  btLoginClicked(View v){
        final  Dialog login = new Dialog(this);
        login.setContentView(R.layout.dialog);
        login.setTitle("Login".toUpperCase());
        Button btnLogin = (Button) login.findViewById(R.id.btnSubmit);
        Button btnCancel = (Button) login.findViewById(R.id.btnCancel);
        final EditText txtUsername = (EditText) login.findViewById(R.id.txtUsername);
        final EditText txtPassword = (EditText) login.findViewById(R.id.txtPassword);
        btnLogin.setText("Login");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = txtUsername.getText().toString();
                String Password = txtPassword.getText().toString();

                String strFileName = Username;

                try{
                    FileInputStream fis = openFileInput(strFileName);
                    InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                    BufferedReader br = new BufferedReader(isr);

                    StringBuffer sBuffer = new StringBuffer();
                    String strLine = "";
                    sBuffer.setLength(0);
                    while ((strLine = br.readLine()) != null){
                        sBuffer.append(strLine);
                    }
                    if(sBuffer.toString().equals(Password)){
                        Intent intent = new Intent(MainActivity.this,CategoryActivity.class);
                        intent.putExtra("Username", Username);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "รหัสผ่านไม่ถูกต้องกรุณาตรวจสอบ",Toast.LENGTH_SHORT).show();
                    }

                    br.close();
                    isr.close();
                    fis.close();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "ชื่อผู้ใช้ไม่ถูกต้องหรือไม่มีชื่อผู้ใช้งานนี้" ,Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.dismiss();
            }
        });
        login.show();
    }
    public boolean fileExists(Context context, String filename){
        File file = context.getFileStreamPath(filename);
        if(file == null || !file.exists()){
            return false;
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
