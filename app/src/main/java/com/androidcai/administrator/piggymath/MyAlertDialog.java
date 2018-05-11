package com.androidcai.administrator.piggymath;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class MyAlertDialog {
    private AlertDialog.Builder objAlert;
    public void NoChooseEveryThing(Time time) {
        objAlert.setTitle("โปรดเลือกคำตอบ");
        objAlert.setMessage("โปรดเลือกคำตอบจากตัวเลือก");
        objAlert.setCancelable(false);
        objAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        objAlert.show();
    }
}
