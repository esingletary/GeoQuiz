package co.manny.bnr.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button mTrueButton, mFalseButton, mNextButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;
    private int totalScore = mQuestionBank.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                areButtonsVisible(false);
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                areButtonsVisible(false);
                checkAnswer(false);
            }
        });
        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getTextResId());
        areButtonsVisible(true);

    }

    private void areButtonsVisible(boolean visibility) {
        View a = findViewById(R.id.true_button);
        View b = findViewById(R.id.false_button);
        if (visibility) {
            a.setVisibility(View.VISIBLE);
            b.setVisibility(View.VISIBLE);
        } else {
            a.setVisibility(View.GONE);
            b.setVisibility(View.GONE);
        }
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        if (mCurrentIndex == mQuestionBank.length - 1) {
            double score = (double) totalScore / (double) mQuestionBank.length;
            Toast.makeText(this, "Your total score is " + (int) (score * 100) + "%", Toast.LENGTH_SHORT).show();
            totalScore = mQuestionBank.length;

        } else if (userPressedTrue == answerIsTrue) {
            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            totalScore--;
        }
    }
}
