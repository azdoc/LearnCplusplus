package cplusplus.learn.trinity.learnc.activites;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cplusplus.learn.trinity.learnc.R;
import cplusplus.learn.trinity.learnc.adapter.QuizRecyclerItemsAdapter;
import cplusplus.learn.trinity.learnc.adapter.RecyclerItemsAdapter;
import cplusplus.learn.trinity.learnc.model.QuizRecyclerItems;
import cplusplus.learn.trinity.learnc.model.RecyclerItems;
import cplusplus.learn.trinity.learnc.utilities.ClickListener;
import cplusplus.learn.trinity.learnc.utilities.CommonActivity;
import cplusplus.learn.trinity.learnc.utilities.RecyclerTouchListener;

public class QuizHomeActivity extends CommonActivity {

    private RecyclerView recyclerView;
    private QuizRecyclerItemsAdapter quizRecyclerItemsAdapter;
    private List<QuizRecyclerItems> quizRecyclerItemsList;
    int easyHighScore,mediumHighScore,difficultHighScore,easyAttempts,mediumAttempts,difficultAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferencesQuizDetails=getSharedPreferences("QuizDetails", Context.MODE_PRIVATE);
        easyHighScore= sharedPreferencesQuizDetails.getInt("easyHighScore",0);
        mediumHighScore= sharedPreferencesQuizDetails.getInt("mediumHighScore",0);
        difficultHighScore= sharedPreferencesQuizDetails.getInt("difficultHighScore",0);
        easyAttempts= sharedPreferencesQuizDetails.getInt("easyAttempts",0);
        mediumAttempts= sharedPreferencesQuizDetails.getInt("mediumAttempts",0);
        difficultAttempts= sharedPreferencesQuizDetails.getInt("difficultAttempts",0);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        quizRecyclerItemsList = new ArrayList<>();
        quizRecyclerItemsAdapter = new QuizRecyclerItemsAdapter(this, quizRecyclerItemsList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(quizRecyclerItemsAdapter);

        prepareRecyclerItems();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(QuizHomeActivity.this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if (position == 0) {
                    Intent intent = new Intent(getBaseContext(), QuizActivity.class);
                    intent.putExtra("difficulty", "Easy");
                    startActivity(intent);
                } else if (position == 1) {
                    if (easyHighScore>=50){
                        Intent intent = new Intent(getBaseContext(), QuizActivity.class);
                        intent.putExtra("difficulty", "Medium");
                        startActivity(intent);
                    }
                    else {
                        alertScoreLess("Score 50 or more in Easy Mode to unlock");
                    }


                } else if (position == 2) {
                    if (mediumAttempts>=50) {
                        Intent intent = new Intent(getBaseContext(), QuizActivity.class);
                        intent.putExtra("difficulty", "Difficult");
                        startActivity(intent);
                    }
                    else{
                        alertScoreLess("Score 50 or more in Medium Mode to unlock");
                    }

                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void prepareRecyclerItems() {

        int mediumIcon,difficultIcon;
        if (easyHighScore>=50) mediumIcon=R.mipmap.ic_play;
        else mediumIcon = R.mipmap.ic_lock;
        if (mediumHighScore>=50) difficultIcon=R.mipmap.ic_play;
        else difficultIcon=R.mipmap.ic_lock;


        QuizRecyclerItems recyclerItems = new QuizRecyclerItems("Basic", "Best Score : " + easyHighScore, "No of Attempts : " + easyAttempts, R.mipmap.ic_play);
        quizRecyclerItemsList.add(recyclerItems);

        recyclerItems = new QuizRecyclerItems("Medium", "Best Score : " + mediumHighScore, "No of Attempts : " + mediumAttempts, mediumIcon);
        quizRecyclerItemsList.add(recyclerItems);

        recyclerItems = new QuizRecyclerItems("Expert", "Best Score : " + difficultHighScore, "No of Attempts : " + difficultAttempts, difficultIcon);
        quizRecyclerItemsList.add(recyclerItems);

        quizRecyclerItemsAdapter.notifyDataSetChanged();
    }


    public void alertScoreLess(String message) {

        new AlertDialog.Builder(QuizHomeActivity.this)
                .setTitle("Quiz Locked")
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

}
