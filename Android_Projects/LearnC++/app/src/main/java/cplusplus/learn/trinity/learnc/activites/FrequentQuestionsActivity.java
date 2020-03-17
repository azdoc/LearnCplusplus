package cplusplus.learn.trinity.learnc.activites;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class FrequentQuestionsActivity extends CommonActivity {
    DatabaseAccess databaseAccess;
    private RecyclerView recyclerView;
    private FrequentQuestionsRecyclerItemsAdapter frequentQuestionsRecyclerItemsAdapter;
    private List<interviewFaqModel> frequentQuestionsRecyclerItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frequent_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        frequentQuestionsRecyclerItemsList = new ArrayList<>();
        frequentQuestionsRecyclerItemsAdapter = new FrequentQuestionsRecyclerItemsAdapter(this, frequentQuestionsRecyclerItemsList,"FAQ");

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(frequentQuestionsRecyclerItemsAdapter);

        prepareRecyclerItems();

        /*recyclerView.addOnItemTouchListener(new RecyclerTouchListener(FrequentQuestionsActivity.this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ImageView imageView = (ImageView) view.findViewById(R.id.imageViewArrow);
                ExpandableLayout expandableLayout = (ExpandableLayout) view.findViewById(R.id.expandable_layout);
                if (frequentQuestionsRecyclerItemsList.get(position).getExpanded()) {
                    imageView.setImageResource(R.drawable.down_arrow);
                    frequentQuestionsRecyclerItemsList.get(position).setExpanded(false);
                    expandableLayout.collapse();
                } else {
                    imageView.setImageResource(R.drawable.up_arrow);
                    frequentQuestionsRecyclerItemsList.get(position).setExpanded(true);
                    expandableLayout.expand();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/
    }

    private void prepareRecyclerItems() {
        ArrayList<interviewFaqModel> arrayList = new ArrayList<>();
        databaseAccess = DatabaseAccess.getInstance(FrequentQuestionsActivity.this);
        databaseAccess.open();
        arrayList = databaseAccess.getAllFAQ();
        databaseAccess.close();
        for(interviewFaqModel singleFAQ:arrayList){
            frequentQuestionsRecyclerItemsList.add(new interviewFaqModel(singleFAQ.getId(),singleFAQ.getQuestion(),singleFAQ.getAnswer(),singleFAQ.getFavourite()));
        }

        frequentQuestionsRecyclerItemsAdapter.notifyDataSetChanged();
    }


}
