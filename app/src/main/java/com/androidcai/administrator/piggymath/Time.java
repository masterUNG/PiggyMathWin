package com.androidcai.administrator.piggymath;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Time extends AppCompatActivity {
    private ImageView imvAnimal ;
    private Button  button2;
    private RadioGroup radAnswer;
    private String strAnswer;
    private MyAlertDialog objMyAlert;
    private Question objQuestion;
    private MyAlertDialog objMyAlertDialog;
    private int intTime = 1  ,intUserchoose ,intUserScore ;
    private MediaPlayer objMediaPlayerButton, objMediaPlayerRadioButton;
    private int intUserChooseArray[] , intTrueAnswerArray[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        initialWidget();
        intUserChooseArray = new int[5];
        intTrueAnswerArray = new int[5];
        setVauleTrueAnswer();


        objQuestion = new Question();
        objQuestion.setOnQuestionChageListener(new Question.OnQuestionChageListener() {
            @Override
            public void onQuestionChangeListener(Question question) {
                switch (question.getIntQuestion()){
                    case 1:
                        imvAnimal.setImageResource(R.drawable.imagess_02);
                        break;
                    case 2:
                        imvAnimal.setImageResource(R.drawable.imagess_05);
                        break;
                    case 3:
                        imvAnimal.setImageResource(R.drawable.imagess_07);
                        break;
                    case 4:
                        imvAnimal.setImageResource(R.drawable.imagess_10);
                        break;
                    case 5:
                        imvAnimal.setImageResource(R.drawable.imagess_12);
                        break;
                    case 6:
                        imvAnimal.setImageResource(R.drawable.imagess_15);
                        break;
                    case 7 :
                        imvAnimal.setImageResource(R.drawable.imagess_17);
                        break;
                    case 8 :
                        imvAnimal.setImageResource(R.drawable.imagess_18);
                        break;
                    case 9 :
                        imvAnimal.setImageResource(R.drawable.imagess_21);
                        break;
                    case 10 :
                        imvAnimal.setImageResource(R.drawable.imagess_23);
                        break;
                    case 11:
                        imvAnimal.setImageResource(R.drawable.imagess_25);
                        break;
                    case 12:
                        imvAnimal.setImageResource(R.drawable.imagess_28);
                        break;
                        default:
                            break;
                }

            }
        });
        radAnswer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radbtn1:
                        strAnswer = "แปดนาฬิกา สิบห้านาที";
                        intUserchoose = 1;
                        break;
                    case R.id.radbtn2:
                        strAnswer = "เก้านาฬิกา สิบห้านาที";
                        intUserchoose = 2;
                        break;
                    case R.id.radbtn3:
                        strAnswer = "แปดนาฬิกา ยี่สิบนาที";
                        intUserchoose = 3;
                        break;
                    case R.id.radbtn4:
                        strAnswer = "เจ็ดนาฬิกา สิบห้านาที";
                        intUserchoose = 4;
                        break;
                    default:
                        strAnswer = null;
                        break;

                }
            }

        });
        soundRadioButton();

        ToaseMessage();
    } //end oncreate

    private void setVauleTrueAnswer() {
        intTrueAnswerArray[1] = 1;
        intTrueAnswerArray[2] = 2;
        intTrueAnswerArray[3] = 3;
        intTrueAnswerArray[4] = 4;
    }


    @SuppressLint("WrongConstant")
    private  void  ToaseMessage(){
        Toast.makeText(Time.this, "Are You Sure Your Answer is " + strAnswer,5000).show();
    }//end Toast
    private void soundRadioButton(){
        objMediaPlayerRadioButton = MediaPlayer.create(getBaseContext(), R.raw.click);
        objMediaPlayerRadioButton.start();
    }
    private void initialWidget(){
        imvAnimal = (ImageView) findViewById(R.id.imvAnimal);
        radAnswer = (RadioGroup) findViewById(R.id.radAnswer); //radAnswer
        button2 = (Button) findViewById(R.id.button2);

    }


//----------------------------------------------
//    when Click Answer Button
//----------------------------------------------
public void ClickAnswer (View view){ checkChooseAnswer();

    }
    private void checkChooseAnswer(){
        if(strAnswer != null){
            Log.d("GGGUYS", "strAnswer = "+ strAnswer);
            sentValueToQuestion();
            checkScore();
            intTime++;
        } else{
            Log.d("GGGUYS", "โปรดเลือกคำตอบ");
            objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.NoChooseEveryThing(Time.this);

        }
        soundButton();
    }//end
    private void checkScore(){
        intUserChooseArray[intTime] = intUserchoose;
            Log.d("GGGUYS","intUserChooseArray["+String.valueOf(intTime)+ " ] = " + String.valueOf(intUserchoose));
            if (intUserChooseArray[intTime] == intTrueAnswerArray[intTime]) {
                intUserScore++;

            }
            Log.d( "GGGUYS","intUserScore = " + String.valueOf(intUserScore));
            //end check score
    }
private void soundButton(){
        objMediaPlayerButton = MediaPlayer.create(getBaseContext(), R.raw.ding);
        objMediaPlayerButton.start();

}//end soundbutton

    private void sentValueToQuestion(){
        if (intTime == 4){
           // intTime = 0;
            checkScore();
            Intent objIntent = new Intent(Time.this,ShowAnswer.class);
            objIntent.putExtra("Score", intUserScore);
            startActivity(objIntent);
            finish();
        }

        //intTime++;
        // set value to model
        objQuestion.setIntQuestion(intTime + 1);
    } //end




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }//end of ClickAnswer

    public MyAlertDialog getObjMyAlert() {
        return objMyAlert;
    }

    public void setObjMyAlert(MyAlertDialog objMyAlert) {
        this.objMyAlert = objMyAlert;
    }
}
