package co.manny.bnr.geoquiz;

/**
 * Created by Emmanuel on 9/10/17.
 */

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mAnswerShown;

    public Question(int textResId, boolean answerTrue, boolean answerShown) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mAnswerShown = answerShown;
    }
    public boolean isAnswerShown() {
        return mAnswerShown;
    }

    public void setAnswerShown(boolean answerShown) {
        mAnswerShown = answerShown;
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
}
