package com.bignerdranch.android.geoquiz;



/**
 * Created by Administrator on 2017/3/2.
 */

public class Question {


    private int mId;
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mIsCheat;

    public int getId() {
        return mId;
    }

    public boolean isCheat() {
        return mIsCheat;
    }

    public void setCheat(boolean cheat) {
        mIsCheat = cheat;
    }
    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public Question(int id,int textResId, boolean answerTrue,boolean IsCheat) {
        mId =id;
        mIsCheat=IsCheat;
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
}
