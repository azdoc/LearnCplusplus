package cplusplus.learn.trinity.learnc.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cplusplus.learn.trinity.learnc.R;
import cplusplus.learn.trinity.learnc.adapter.RecyclerItemsAdapter;
import cplusplus.learn.trinity.learnc.model.RecyclerItems;
import cplusplus.learn.trinity.learnc.utilities.ClickListener;
import cplusplus.learn.trinity.learnc.utilities.CommonActivity;
import cplusplus.learn.trinity.learnc.utilities.RecyclerTouchListener;

public class HomeActivity extends CommonActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerItemsAdapter recyclerItemsAdapter;
    private List<RecyclerItems> recyclerItemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerItemsList = new ArrayList<>();
        recyclerItemsAdapter = new RecyclerItemsAdapter(this, recyclerItemsList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerItemsAdapter);

        prepareRecyclerItems();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(HomeActivity.this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                switch (position){
                    case 0:
                        startActivity(new Intent(getBaseContext(), TutorialsHomeActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getBaseContext(), ProgramsCategoryActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getBaseContext(), InterviewQuestionsActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getBaseContext(), QuizHomeActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getBaseContext(), FrequentQuestionsActivity.class));
                        break;
                    case 5:
                        showToast("Coming Soon",HomeActivity.this);
                        break;
                }


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void prepareRecyclerItems() {

        RecyclerItems recyclerItems = new RecyclerItems("Tutorials", "Cover's most C++ theory", R.mipmap.ic_tutorials);
        recyclerItemsList.add(recyclerItems);

        recyclerItems = new RecyclerItems("Programs", "C++ programs divided into relevant topics", R.mipmap.ic_programs);
        recyclerItemsList.add(recyclerItems);

        recyclerItems = new RecyclerItems("Interview Questions", "Questions asked to a C++ developer", R.mipmap.ic_interview);
        recyclerItemsList.add(recyclerItems);

        recyclerItems = new RecyclerItems("Quiz", "Test your Knowledge", R.mipmap.ic_quiz);
        recyclerItemsList.add(recyclerItems);

        recyclerItems = new RecyclerItems("FAQ's", "Common issues faced by a C++ developer", R.mipmap.ic_faq);
        recyclerItemsList.add(recyclerItems);

        recyclerItems = new RecyclerItems("Mini Projects", "Small Projects for learning development", R.mipmap.ic_projects);
        recyclerItemsList.add(recyclerItems);

        recyclerItemsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_profile) {
            // Handle the camera action
        } else*/ if (id == R.id.nav_favourites) {
            startActivity(new Intent(getBaseContext(), FavouritesActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
