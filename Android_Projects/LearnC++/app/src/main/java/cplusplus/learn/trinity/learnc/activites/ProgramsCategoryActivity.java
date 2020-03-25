package cplusplus.learn.trinity.learnc.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cplusplus.learn.trinity.learnc.R;
import cplusplus.learn.trinity.learnc.adapter.RecyclerItemsAdapter;
import cplusplus.learn.trinity.learnc.model.RecyclerItems;
import cplusplus.learn.trinity.learnc.utilities.ClickListener;
import cplusplus.learn.trinity.learnc.utilities.CommonActivity;
import cplusplus.learn.trinity.learnc.utilities.RecyclerTouchListener;

public class ProgramsCategoryActivity extends CommonActivity {

    private RecyclerView recyclerView;
    private RecyclerItemsAdapter recyclerItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        final List<RecyclerItems> recyclerItemsList = prepareRecyclerItems();
        recyclerItemsAdapter = new RecyclerItemsAdapter(this, recyclerItemsList);
        //recyclerItemsAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerItemsAdapter);

        prepareRecyclerItems();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(ProgramsCategoryActivity.this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                RecyclerItems recyclerItem= recyclerItemsList.get(position);
                Intent intent = new Intent(getBaseContext(), ProgramListActivity.class);
                intent.putExtra("programCategory",recyclerItem.getTitle() );
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private List<RecyclerItems> prepareRecyclerItems() {
        List<RecyclerItems> recyclerItemsList = new ArrayList<>();
        recyclerItemsList.add(new RecyclerItems("Basics", "Understand C++ programming basics", R.mipmap.ic_tutorials));
        recyclerItemsList.add(new RecyclerItems("String Operations", "Programs to master string operations", R.mipmap.ic_tutorials));
        recyclerItemsList.add(new RecyclerItems("Conversion", "Programs on unit conversions", R.mipmap.ic_tutorials));
        recyclerItemsList.add(new RecyclerItems("Arrays", "Master C++ Array operations", R.mipmap.ic_tutorials));
        recyclerItemsList.add(new RecyclerItems("Patterns", "Programs to master programing logic", R.mipmap.ic_tutorials));
        recyclerItemsList.add(new RecyclerItems("Operator Overloading", "", R.mipmap.ic_tutorials));
        recyclerItemsList.add(new RecyclerItems("Inheritance", "", R.mipmap.ic_tutorials));
        recyclerItemsList.add(new RecyclerItems("Pointers", "", R.mipmap.ic_tutorials));
        /*recyclerItemsList.add(new RecyclerItems("Functions", "Understand how funcions", R.mipmap.ic_tutorials));*/
        recyclerItemsList.add(new RecyclerItems("Files", "Programs to master file operations", R.mipmap.ic_tutorials));
        recyclerItemsList.add(new RecyclerItems("Streams", "Master stream operations", R.mipmap.ic_tutorials));
        recyclerItemsList.add(new RecyclerItems("Exception Handling", "Understand how to handel exceptions", R.mipmap.ic_tutorials));
        recyclerItemsList.add(new RecyclerItems("Data Structures", "Master Data Structures using C++", R.mipmap.ic_programs));
        recyclerItemsList.add(new RecyclerItems("Computer Graphics", "Programs related to Computer Graphics", R.mipmap.ic_interview));
        return recyclerItemsList;
    }

}
