package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_NUMBER="com.bignerdranch.android.geoquiz.number";
    private static final String EXTRA_ANSWER_NUMBER=
            "com.bignerdranch.android.geoquiz.answer_number";
    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final  String EXTRA_ANSWER_SHOWN =
            "com.bignerdranch.android.geoquiz.answer_shown";
    private static final String Ischeat_INDEX="ISCHEAT";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private int mCurrent;
    private boolean mIsCheat=false;

    public static Intent newIntent(Context packageContext,boolean answerIsTrue,int Current){
        Intent i = new Intent(packageContext,CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        i.putExtra(EXTRA_ANSWER_NUMBER,Current);
        return i;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }
    public static int AnswerNumber(Intent result){
        return result.getIntExtra(EXTRA_NUMBER,0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        if(savedInstanceState!=null){
            mIsCheat= savedInstanceState.getBoolean(Ischeat_INDEX);
        }

        mCurrent = getIntent().getIntExtra(EXTRA_ANSWER_NUMBER,0);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if (mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }
                else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                //setAnswerShownResult(true,mCurrent);
                mIsCheat=true;
            }
        });

        if (mIsCheat)  setAnswerShownResult(true,mCurrent); else
           setAnswerShownResult(false,mCurrent);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(Ischeat_INDEX,mIsCheat);
    }


    private void setAnswerShownResult(boolean isAnswerShown,int Current){
        Intent data= new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        data.putExtra(EXTRA_NUMBER,Current);
        setResult(RESULT_OK,data);
    }
}
