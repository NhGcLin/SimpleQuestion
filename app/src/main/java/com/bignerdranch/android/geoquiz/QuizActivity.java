package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionTextView;
    private Button mCheatButton;
    private boolean mIsCheater;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private static final String Key_CHEAT="isCheat";

    /*private Question[] mQuestionBank = new Question[]{
            new Question(0,R.string.question_oceans, true,false),
            new Question(1,R.string.question_mideast, false,false),
            new Question(2,R.string.question_africa, false,false),
            new Question(3,R.string.question_americas, true,false),
            new Question(4,R.string.question_asia, true,false),
    };*/
    private QuestionLab questionLab=QuestionLab.get();

    private int mCurrentIndex = 0;

    private void updateQestion() {
        int question=questionLab.getQuestion(mCurrentIndex).getTextResId();
        //int question = mQuestionBank[mCurrentIndex].getTextResId();
        //Toast.makeText(this,question,Toast.LENGTH_SHORT).show();
        mQuestionTextView.setTextSize(36);
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = questionLab.getQuestion(mCurrentIndex).isAnswerTrue();
        //boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;
        if (questionLab.getQuestion(mCurrentIndex).isCheat()){
            messageResId = R.string.judgment_toast;
        }else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);


        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
     /*   int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);*/
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % questionLab.getLength();
                updateQestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start CheatActivity
                //Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                //startActivity(i);
                boolean answerIsTrue = questionLab.getQuestion(mCurrentIndex).isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this,answerIsTrue,mCurrentIndex);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % questionLab.getLength();
                mIsCheater = false;
                updateQestion();
            }
        });

        mPrevButton = (Button) findViewById(R.id.prev_buuton);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == 0)
                    mCurrentIndex = questionLab.getLength() - 1;
                else
                    mCurrentIndex = mCurrentIndex - 1;
                updateQestion();
            }
        });

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            //mIsCheater=savedInstanceState.getBoolean(Key_CHEAT,false);
            //mQuestionBank[mCurrentIndex].setCheat(mIsCheater);
        }

        updateQestion();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        //savedInstanceState.putBoolean(Key_CHEAT,mQuestionBank[mCurrentIndex].isCheat());

    }


    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode!= Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE_CHEAT){
            if(data==null){
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
            int number = CheatActivity.AnswerNumber(data);
            //mQuestionBank[number].setCheat(mIsCheater);
            //questionLab.Set(number,mQuestionBank[number]);
            Question Change=questionLab.getQuestion(number);
            Change.setCheat(mIsCheater);
            questionLab.Set(number,Change);
        }

    }


}
