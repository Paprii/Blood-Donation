package com.example.thingstodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    /**
     * setting up things
     */
    private Button falseButton;
    private Button trueButton;
    private TextView questionTextView;
    private int correct = 0;
    /**
     * to keep current question track
     */
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[] {
            /**
             * array of objects of class Question
             * providing questions from string
             * resource and the correct ans
             */
            new Question(R.string.a, true),
            new Question(R.string.b, false),
            new Question(R.string.c, true),
            new Question(R.string.d, true),
            new Question(R.string.e, true),
            new Question(R.string.f, false),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /** setting up the buttons
         * associated with id
        */
        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);
        /** register our buttons to listen to
         * click events
         */
        questionTextView
                = findViewById(R.id.answer_text_view);
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v)
    {
        /** checking which button is
         * clicked by user
         * in this case user choose false
         */
        switch (v.getId()) {
            case R.id.false_button:
                checkAnswer(false);
                break;

            case R.id.true_button:
                checkAnswer(true);
                break;

            case R.id.next_button:
                /** go to next question
                 *limiting question bank range
                 */
                if (currentQuestionIndex < 7) {
                    currentQuestionIndex
                            = currentQuestionIndex + 1;
                    /**last question reached
                     * making buttons
                     * invisible
                     */
                    if (currentQuestionIndex == 6) {
                        questionTextView.setText(getString(
                                R.string.correct, correct));
                        nextButton.setVisibility(
                                View.INVISIBLE);
                        prevButton.setVisibility(
                                View.INVISIBLE);
                        trueButton.setVisibility(
                                View.INVISIBLE);
                        falseButton.setVisibility(
                                View.INVISIBLE);
                        if (correct > 3){

                            questionTextView.setText(
                                    "CORRECTNESS IS " + correct
                                            + " "
                                            + "OUT OF 6");
                            /** showing correctness
                             */
                    }
                    else {
                        updateQuestion();
                    }
                }

                break;
            case R.id.prev_button:
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex
                            = (currentQuestionIndex - 1)
                            % questionBank.length;
                    updateQuestion();
                }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateQuestion()
    {
        Log.d("Current",
                "onClick: " + currentQuestionIndex);

        questionTextView.setText(
                questionBank[currentQuestionIndex]
                        .getAnswerResId());
        /** setting the textview with new question
         */

    }
    private void checkAnswer(boolean userChooseCorrect)
    {
        boolean answerIsTrue
                = questionBank[currentQuestionIndex]
                .isAnswerTrue();
        /** getting correct ans of current question
         */
        int toastMessageId;
        /** if ans matches with the
         *button clicked
         */

        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
            correct++;
        }
        else {
            /** showing toast
             * message correct
             */
            toastMessageId = R.string.wrong_answer;
        }

        Toast
                .makeText(MainActivity.this, toastMessageId,
                        Toast.LENGTH_SHORT)
                .show();
    }
}
