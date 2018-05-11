package com.androidcai.administrator.piggymath;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class NewGameFragment extends Fragment {

    private String userLoginString;
    private int modeAnInt, levelAnInt, timesAnInt = 0,
            scoreAnInt = 0, indexFalse = 0, countTime = 0;
    private MyConstant myConstant;
    private int[] questionInts, answerInts;
    private String[] choice1Strings, choice2Strings, choice3Strings, choice4Strings;
    private boolean aBoolean = true;
    private MediaPlayer backgroundMediaPlayer, answerTrueMediaPlayer, answerFalseMediaPlayer;

    public static NewGameFragment newGameInstance(int indexLevel) {
        NewGameFragment newGameFragment = new NewGameFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Level", indexLevel);
        newGameFragment.setArguments(bundle);
        return newGameFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Get Value
        getValue();

//        Choose Question
        chooseQuestion();

//        Show View
        showView(0);

//        Show Time
        showTime();

//        Setup MediaPlayer
        setupMediaPlayer();


    }   // Main Method

    private void setupMediaPlayer() {

        try {

            backgroundMediaPlayer = MediaPlayer.create(getActivity(), R.raw.bgm);
            backgroundMediaPlayer.setLooping(true);
            backgroundMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
            backgroundMediaPlayer.start();

            answerTrueMediaPlayer = MediaPlayer.create(getActivity(), R.raw.ding);
//            answerTrueMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    mp.release();
//                }
      //      });


            answerFalseMediaPlayer = MediaPlayer.create(getActivity(), R.raw.wrong);
//            answerFalseMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    mp.release();
//                }
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }




    }   // setup Media

    private void showTime() {

        TextView textView = getView().findViewById(R.id.txtShowTime);
        final int[] endTimesInts = myConstant.getTimeDelayInts();//endTimesInts[leg]
        textView.setText("Time = " + endTimesInts[levelAnInt]);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (aBoolean && (endTimesInts[levelAnInt] > 0)) {
                    endTimesInts[levelAnInt] -= 1;
                    showTime();
                } else {
                    finishGame();
                }


            }
        }, 1000);


    }

    private void showView(int indexInt) {

        ImageView imageView = getView().findViewById(R.id.imvQuestion);
        imageView.setImageResource(questionInts[indexInt]);

        Button choice1Button = getView().findViewById(R.id.btnChoice1);
        Button choice2Button = getView().findViewById(R.id.btnChoice2);
        Button choice3Button = getView().findViewById(R.id.btnChoice3);
        Button choice4Button = getView().findViewById(R.id.btnChoice4);


        choice1Button.setText(choice1Strings[indexInt]);
        choice2Button.setText(choice2Strings[indexInt]);
        choice3Button.setText(choice3Strings[indexInt]);
        choice4Button.setText(choice4Strings[indexInt]);

        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                answer(1);
            }
        });
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer(2);
            }
        });
        choice3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer(3);
            }
        });
        choice4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer(4);
            }
        });


    }

    private void answer(int answerInt) {

        checkScore(timesAnInt, answerInt);

        timesAnInt += 1;

        if (timesAnInt < questionInts.length) {


            showView(timesAnInt);


        } else {
            finishGame();
        }

    }

    private void checkScore(int timesAnInt, int answerInt) {

        TextView textView = getView().findViewById(R.id.txtShowscore);
        ImageView imageView = getView().findViewById(R.id.imvEat);

        int[] ints = myConstant.getEatInts();

        if (answerInt == answerInts[timesAnInt]) {

            answerTrueMediaPlayer.start();
            scoreAnInt += 1;
            textView.setText("Score = " + Integer.toString(scoreAnInt));
        } else {

            answerFalseMediaPlayer.start();
            indexFalse += 1;

            if (indexFalse < ints.length) {
                imageView.setImageResource(ints[indexFalse]);
            } else {
                finishGame();
            }


        }   //if


    }

    private void finishGame() {

        backgroundMediaPlayer.stop();

        Toast.makeText(getActivity(), "Finish Game",
                Toast.LENGTH_SHORT).show();

        String[] strings = new String[]{"Easy", "Normal", "Hard"};
        String strFileName = "score.txt";
        String strFileContents = userLoginString + "\t\t\t\t\t\t " + strings[levelAnInt] + " \t\t\t\t\t\t\t\t" + Integer.toString(scoreAnInt) + "\n";

        try {

            FileOutputStream fileOutputStream = getActivity().openFileOutput(strFileName, Context.MODE_APPEND);
            fileOutputStream.write(strFileContents.getBytes());
            fileOutputStream.close();

            aBoolean = false;
            getActivity().finish();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }   // FinishGame

    private void chooseQuestion() {
        switch (modeAnInt) {
            case 0:
                questionInts = myConstant.getModeGame0Ints();
                choice1Strings = myConstant.getChoice1Mode0Strings();
                choice2Strings = myConstant.getChoice2Mode0Strings();
                choice3Strings = myConstant.getChoice3Mode0Strings();
                choice4Strings = myConstant.getChoice4Mode0Strings();
                answerInts = myConstant.getAnswerMode0Ints();
                break;
            case 1:
                questionInts = myConstant.getModeGame1Ints();
                choice1Strings = myConstant.getChoice1Mode1Strings();
                choice2Strings = myConstant.getChoice2Mode1Strings();
                choice3Strings = myConstant.getChoice3Mode1Strings();
                choice4Strings = myConstant.getChoice4Mode1Strings();
                answerInts = myConstant.getAnswerMode1Ints();
                break;
            case 2:
                questionInts = myConstant.getModeGame2Ints();
                choice1Strings = myConstant.getChoice1Mode2Strings();
                choice2Strings = myConstant.getChoice2Mode2Strings();
                choice3Strings = myConstant.getChoice3Mode2Strings();
                choice4Strings = myConstant.getChoice4Mode2Strings();
                answerInts = myConstant.getAnswerMode2Ints();
                break;
            case 3:
                questionInts = myConstant.getModeGame3Ints();
                choice1Strings = myConstant.getChoice1Mode3Strings();
                choice2Strings = myConstant.getChoice2Mode3Strings();
                choice3Strings = myConstant.getChoice3Mode3Strings();
                choice4Strings = myConstant.getChoice4Mode3Strings();
                answerInts = myConstant.getAnswerMode3Ints();
                break;
        }
    }

    private void getValue() {
        levelAnInt = getArguments().getInt("Level", 0);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        userLoginString = sharedPreferences.getString("Username", "");
        modeAnInt = sharedPreferences.getInt("Mode", 0);
        Log.d("10MayV2", "User ==> " + userLoginString);
        Log.d("10MayV2", "Mode ==> " + modeAnInt);
        Log.d("10MayV2", "Level ==> " + levelAnInt);
        myConstant = new MyConstant();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_game, container, false);
        return view;
    }
}
