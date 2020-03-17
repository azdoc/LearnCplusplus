package cplusplus.learn.trinity.learnc.activites;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cplusplus.learn.trinity.learnc.R;
import cplusplus.learn.trinity.learnc.adapter.FrequentQuestionsRecyclerItemsAdapter;
import cplusplus.learn.trinity.learnc.db.DatabaseAccess;
import cplusplus.learn.trinity.learnc.model.interviewFaqModel;
import cplusplus.learn.trinity.learnc.utilities.CommonActivity;

public class InterviewQuestionsActivity extends CommonActivity {

    DatabaseAccess databaseAccess;
    private RecyclerView recyclerView;
    private FrequentQuestionsRecyclerItemsAdapter frequentQuestionsRecyclerItemsAdapter;
    private List<interviewFaqModel> frequentQuestionsRecyclerItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_interview_questions);

        frequentQuestionsRecyclerItemsList = new ArrayList<>();
        frequentQuestionsRecyclerItemsAdapter = new FrequentQuestionsRecyclerItemsAdapter(this, frequentQuestionsRecyclerItemsList,"Interview");

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(frequentQuestionsRecyclerItemsAdapter);

        prepareRecyclerItems();

    }

    private void prepareRecyclerItems() {
        ArrayList<interviewFaqModel> arrayList = new ArrayList<>();
        databaseAccess = DatabaseAccess.getInstance(InterviewQuestionsActivity.this);
        databaseAccess.open();
        arrayList = databaseAccess.getAllInterviewQuestion();
        databaseAccess.close();
        for(interviewFaqModel singleFAQ:arrayList){
            frequentQuestionsRecyclerItemsList.add(new interviewFaqModel(singleFAQ.getId(),singleFAQ.getQuestion(),singleFAQ.getAnswer(),singleFAQ.getFavourite()));
        }

        frequentQuestionsRecyclerItemsAdapter.notifyDataSetChanged();
    }



}
