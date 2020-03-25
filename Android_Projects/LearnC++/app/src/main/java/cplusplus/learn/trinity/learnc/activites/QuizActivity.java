package cplusplus.learn.trinity.learnc.activites;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar;

import java.util.ArrayList;
import java.util.Random;

import cplusplus.learn.trinity.learnc.R;
import cplusplus.learn.trinity.learnc.db.DatabaseAccess;
import cplusplus.learn.trinity.learnc.model.SingleQuestion;

public class QuizActivity extends AppCompatActivity {
    DatabaseAccess databaseAccess;
    TextView txtQuestions, txtExplanation, txtCurrentScore, txtCurrentLives, txtCorrectorNot,txtCorrectOption;
    RadioGroup radioGroupAnswers;
    RadioButton radioButtonOptionA, radioButtonOptionB, radioButtonOptionC, radioButtonOptionD;
    Button buttonSubmit, buttonCancel, buttonNext;
    LinearLayout linearLayoutButtons, linearLayoutNext;
    int currentQuestion = 0, currentScore = 0, currentLives = 3, previousCorrect = 0;
    ArrayList<SingleQuestion> fiftyQuestions;
    TextRoundCornerProgressBar progressBar;
    final int maxQuestions = 50;
    String selectedDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        selectedDifficulty = getIntent().getStringExtra("difficulty");
        ArrayList<SingleQuestion> filteredQuestions = getQuestionsDifficulty(selectedDifficulty);
        fiftyQuestions = new ArrayList<>();
        for (int i = 1; i <= maxQuestions; i++) {
            fiftyQuestions.add(filteredQuestions.get(getRandomNumberInRange(0, filteredQuestions.size() - 1)));
        }
        declare();
        alertRules();
        setQuestionAttributes();
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = checkValid(fiftyQuestions.get(currentQuestion).getAnswer());

                if (result.equals("Correct")) {
                    previousCorrect = previousCorrect + 1;
                    if (previousCorrect == 0) currentScore = currentScore + 1;
                    else {
                        currentScore = currentScore + 1 * previousCorrect;
                        Toast.makeText(getApplicationContext(), previousCorrect + "X score", Toast.LENGTH_SHORT).show();
                    }
                    showCorrect();
                    setScoreAttribute();
                } else if (result.equals("Incorrect")) {
                    txtCorrectOption.setVisibility(View.VISIBLE);
                    txtExplanation.setVisibility(View.VISIBLE);
                    previousCorrect = 0;
                    currentLives = currentLives - 1;
                    showInCorrect();
                    if (currentLives>0)setScoreAttribute();
                    else {
                        setSharedPreferences();
                        alertOver();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Select one option", Toast.LENGTH_SHORT).show();
                }


            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestion >= fiftyQuestions.size() - 1) {
                    Toast.makeText(getApplicationContext(), "Congratulations you have attempted all the questions", Toast.LENGTH_SHORT).show();
                } else {
                    currentQuestion = currentQuestion + 1;
                    setQuestionAttributes();
                    linearLayoutButtons.setVisibility(View.VISIBLE);
                    linearLayoutNext.setVisibility(View.GONE);
                }
                radioButtonOptionA.setBackground(getResources().getDrawable(R.drawable.card));
                radioButtonOptionB.setBackground(getResources().getDrawable(R.drawable.card));
                radioButtonOptionC.setBackground(getResources().getDrawable(R.drawable.card));
                radioButtonOptionD.setBackground(getResources().getDrawable(R.drawable.card));

            }
        });

       radioGroupAnswers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
               if (checkedId == R.id.optionA){
                   radioButtonOptionA.setBackground(getResources().getDrawable(R.drawable.card_selected));
                   radioButtonOptionB.setBackground(getResources().getDrawable(R.drawable.card));
                   radioButtonOptionC.setBackground(getResources().getDrawable(R.drawable.card));
                   radioButtonOptionD.setBackground(getResources().getDrawable(R.drawable.card));
               }else if (checkedId == R.id.optionB){
                   radioButtonOptionA.setBackground(getResources().getDrawable(R.drawable.card));
                   radioButtonOptionB.setBackground(getResources().getDrawable(R.drawable.card_selected));
                   radioButtonOptionC.setBackground(getResources().getDrawable(R.drawable.card));
                   radioButtonOptionD.setBackground(getResources().getDrawable(R.drawable.card));
               }else if (checkedId == R.id.optionC){
                   radioButtonOptionA.setBackground(getResources().getDrawable(R.drawable.card));
                   radioButtonOptionB.setBackground(getResources().getDrawable(R.drawable.card));
                   radioButtonOptionC.setBackground(getResources().getDrawable(R.drawable.card_selected));
                   radioButtonOptionD.setBackground(getResources().getDrawable(R.drawable.card));
               }else if (checkedId == R.id.optionD){
                   radioButtonOptionA.setBackground(getResources().getDrawable(R.drawable.card));
                   radioButtonOptionB.setBackground(getResources().getDrawable(R.drawable.card));
                   radioButtonOptionC.setBackground(getResources().getDrawable(R.drawable.card));
                   radioButtonOptionD.setBackground(getResources().getDrawable(R.drawable.card_selected));
               }

           }
       });

    }

    public void setQuestionAttributes() {
        txtQuestions.setText(fiftyQuestions.get(currentQuestion).getQuestion());
        txtExplanation.setText("Explanation : "+fiftyQuestions.get(currentQuestion).getExplanation());
        txtCorrectOption.setText("Correct option : "+fiftyQuestions.get(currentQuestion).getAnswer());
        radioGroupAnswers.clearCheck();
        radioButtonOptionA.setText(fiftyQuestions.get(currentQuestion).getOptionA());
        radioButtonOptionB.setText(fiftyQuestions.get(currentQuestion).getOptionB());
        radioButtonOptionC.setText(fiftyQuestions.get(currentQuestion).getOptionC());
        radioButtonOptionD.setText(fiftyQuestions.get(currentQuestion).getOptionD());
        txtCorrectorNot.setVisibility(View.GONE);
        txtCorrectOption.setVisibility(View.GONE);
        txtExplanation.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void setScoreAttribute() {
        txtCurrentLives.setText(currentLives + " Lives");
        txtCurrentScore.setText("Score : " + currentScore + "");
        progressBar.setProgress(currentQuestion + 1);
        progressBar.setProgressText("Progress");
        if (currentQuestion < 10) {
            progressBar.setProgressColor(getResources().getColor(R.color.color_less_than_20));
        } else if (currentQuestion >= 10 && currentQuestion < 20) {
            progressBar.setProgressColor(getResources().getColor(R.color.color_less_than_40));
        } else if (currentQuestion >= 20 && currentQuestion < 30) {
            progressBar.setProgressColor(getResources().getColor(R.color.color_less_than_60));
        } else if (currentQuestion >= 30 && currentQuestion < 40) {
            progressBar.setProgressColor(getResources().getColor(R.color.color_less_than_80));
        } else {
            progressBar.setProgressColor(getResources().getColor(R.color.custom_progress_green_progress));
        }
        linearLayoutButtons.setVisibility(View.GONE);
        linearLayoutNext.setVisibility(View.VISIBLE);
    }

    public void declare() {
        txtQuestions = (TextView) findViewById(R.id.txtQuestion);
        txtExplanation = (TextView) findViewById(R.id.txtExplanation);
        txtCurrentLives = (TextView) findViewById(R.id.txtLives);
        txtCurrentScore = (TextView) findViewById(R.id.txtCurrentScore);
        txtCorrectorNot = (TextView) findViewById(R.id.txtCorrectOrNot);
        txtCorrectOption=(TextView)findViewById(R.id.txtCorrectOption);
        radioGroupAnswers = (RadioGroup) findViewById(R.id.radioGroupAnswers);
        radioButtonOptionA = (RadioButton) findViewById(R.id.optionA);
        radioButtonOptionB = (RadioButton) findViewById(R.id.optionB);
        radioButtonOptionC = (RadioButton) findViewById(R.id.optionC);
        radioButtonOptionD = (RadioButton) findViewById(R.id.optionD);
        buttonSubmit = (Button) findViewById(R.id.btnSubmit);
        buttonNext = (Button) findViewById(R.id.btnNext);
        linearLayoutNext = (LinearLayout) findViewById(R.id.llNext);
        linearLayoutButtons = (LinearLayout) findViewById(R.id.llButtons);
        progressBar = (TextRoundCornerProgressBar) findViewById(R.id.progress_id);
        progressBar.setRadius(50);
        progressBar.setPadding(8);
        progressBar.setMax(maxQuestions);
        progressBar.setProgressBackgroundColor(getResources().getColor(R.color.custom_progress_background));
        progressBar.setTextProgressSize(Math.round(10 * getResources().getDisplayMetrics().density));
        progressBar.setTextProgressMargin(20);
        progressBar.setTextProgressColor(getResources().getColor(R.color.white));
    }

    public void showCorrect() {
        txtCorrectorNot.setText("Correct");
        txtCorrectorNot.setBackgroundColor(getResources().getColor(R.color.green));
        txtCorrectorNot.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_check, 0);
        txtCorrectorNot.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.enter);
        txtCorrectorNot.startAnimation(anim);
    }

    public void showInCorrect() {
        txtCorrectorNot.setText("Incorrect");
        txtCorrectorNot.setBackgroundColor(getResources().getColor(R.color.red));
        txtCorrectorNot.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_lwrong, 0);
        txtCorrectorNot.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.enter);
        txtCorrectorNot.startAnimation(anim);
    }

    public String checkValid(String answer) {
        String selectedAnswer = "";
        if (radioGroupAnswers.getCheckedRadioButtonId() != -1) {
            int selectedId = radioGroupAnswers.getCheckedRadioButtonId();
            View radioButtonSelected = radioGroupAnswers.findViewById(selectedId);
            int radioSelectedIndex = radioGroupAnswers.indexOfChild(radioButtonSelected);
            if (radioSelectedIndex == 0) selectedAnswer = "A";
            else if (radioSelectedIndex == 1) selectedAnswer = "B";
            else if (radioSelectedIndex == 2) selectedAnswer = "C";
            else if (radioSelectedIndex == 3) selectedAnswer = "D";

            if (answer.equalsIgnoreCase(selectedAnswer)) return "Correct";
            return "Incorrect";
        } else {
            return "Noting Selected";
        }

    }

    public void alertRules() {

        new AlertDialog.Builder(QuizActivity.this)
                .setTitle("Rules")
                .setMessage("1. You have maximum of 3 life or 50 questions in each test\n" +
                        "2. Each wrong answer will reduce 1 life.\n" +
                        "3. Each correct answer will give 1 point and consecutive correct answers will give bonus multipliers.\n" +
                        "4. Try scoring maximum points.\n" +
                        "5. Attempting a question is compulsory.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    public void alertOver() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layoutView = inflater.inflate(R.layout.test_over_layout,
                null, false);

        SharedPreferences sharedPreferences = getSharedPreferences("QuizDetails", Context.MODE_PRIVATE);
        int highScore=0;
        if(selectedDifficulty.equals("Easy")){
            highScore= sharedPreferences.getInt("easyHighScore",0);
        }
        else if(selectedDifficulty.equals("Medium")) {
            highScore= sharedPreferences.getInt("mediumHighScore",0);
        }
        else if(selectedDifficulty.equals("Difficult")){
            highScore= sharedPreferences.getInt("difficultHighScore",0);
        }

        final TextView txtScore = (TextView) layoutView.findViewById(R.id.txtScore);
        final TextView txtBestScore = (TextView) layoutView.findViewById(R.id.txtBestScore);
        final Button btnShareScore = (Button) layoutView.findViewById(R.id.btnShareScore);
        txtScore.setText("Score : "+currentScore);
        txtBestScore.setText("Best Score : "+highScore);

        new AlertDialog.Builder(QuizActivity.this).setView(layoutView)
                .setTitle("0 Lives Left")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        startActivity(new Intent(getBaseContext(), QuizHomeActivity.class));
                    }

                }).show();
    }

    public void setSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("QuizDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(selectedDifficulty.equals("Easy")){
            int highScore= sharedPreferences.getInt("easyHighScore",0);
            if (highScore<currentScore) editor.putInt("easyHighScore", currentScore);
            int attempts= sharedPreferences.getInt("easyAttempts",0);
            editor.putInt("easyAttempts", attempts+1);
        }
        else if(selectedDifficulty.equals("Medium")) {
            int highScore= sharedPreferences.getInt("mediumHighScore",0);
            if (highScore<currentScore) editor.putInt("easyHighScore", currentScore);
            int attempts= sharedPreferences.getInt("mediumAttempts",0);
            editor.putInt("mediumAttempts", attempts+1);
        }
        else if(selectedDifficulty.equals("Difficult")){
            int highScore= sharedPreferences.getInt("difficultHighScore",0);
            if (highScore<currentScore) editor.putInt("difficultHighScore", currentScore);
            int attempts= sharedPreferences.getInt("difficultAttempts",0);
            editor.putInt("difficultAttempts", attempts+1);
        }
        editor.apply();
        editor.commit();
    }

    public ArrayList<SingleQuestion> getQuestionsDifficulty(String difficultySelected) {
        ArrayList<SingleQuestion> arrayList = new ArrayList<>();
        databaseAccess = DatabaseAccess.getInstance(QuizActivity.this);
        databaseAccess.open();
        arrayList = databaseAccess.getAllQuestions(difficultySelected);
        databaseAccess.close();

        return arrayList;
    }


    private static int getRandomNumberInRange(int min, int max) {

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    public void onBackPressed() {
        alertCancel();
        //super.onBackPressed();
    }

    public void alertCancel() {
        new AlertDialog.Builder(QuizActivity.this)
                .setTitle("Exit Quiz")
                .setMessage("Are you sure you want to exit Test?")
                .setIcon(R.mipmap.ic_check)
                .setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                //setSharedPreferences();
                                startActivity(new Intent(getBaseContext(), QuizHomeActivity.class));
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
}
