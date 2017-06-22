package com.bignerdranch.android.geoquiz;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class QuestionLab {
    private static QuestionLab sQuestionLab;
    private List<Question> mQuestions;

    public static QuestionLab get(){
        if(sQuestionLab==null){
            sQuestionLab=new QuestionLab();

        }
        return sQuestionLab;
    }

    private QuestionLab(){
        mQuestions = new ArrayList<>();
        mQuestions.add(new Question(0,R.string.question_oceans, false,false));
        mQuestions.add(new Question(1,R.string.question_mideast, false,false));
        mQuestions.add(new Question(2,R.string.question_africa,true,false));
        mQuestions.add(new Question(3,R.string.question_americas, true,false));
        mQuestions.add(new Question(4,R.string.question_asia, true,false));
    }

    public Question getQuestion(int id){
        for (Question question:mQuestions){
            if (question.getId() == id){
                return question;
            }
        }
        return null;
    }
    public void Set(int index,Question question){
        mQuestions.set(index,question);
    }

    public int getLength(){
        return mQuestions.size();
    }
}
