package cplusplus.learn.trinity.learnc.fragments.favoutites;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

import cplusplus.learn.trinity.learnc.R;
import cplusplus.learn.trinity.learnc.adapter.FrequentQuestionsRecyclerItemsAdapter;
import cplusplus.learn.trinity.learnc.db.DatabaseAccess;
import cplusplus.learn.trinity.learnc.model.interviewFaqModel;


public class FavInterviewQuesFragment extends Fragment {

    private RecyclerView recyclerView;
    DatabaseAccess databaseAccess;
    private FrequentQuestionsRecyclerItemsAdapter frequentQuestionsRecyclerItemsAdapter;
    private List<interviewFaqModel> frequentQuestionsRecyclerItemsList;
    private TableLayout noDataTableLayout;

    public FavInterviewQuesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        noDataTableLayout=(TableLayout)rootView.findViewById(R.id.noData);

        frequentQuestionsRecyclerItemsList = new ArrayList<>();
        frequentQuestionsRecyclerItemsAdapter = new FrequentQuestionsRecyclerItemsAdapter(getContext(), frequentQuestionsRecyclerItemsList,"Interview");

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(frequentQuestionsRecyclerItemsAdapter);

        prepareRecyclerItems();

        if(frequentQuestionsRecyclerItemsList.size()==0){
            noDataTableLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            recyclerView.setVisibility(View.VISIBLE);
            noDataTableLayout.setVisibility(View.GONE);
        }

        return rootView;
    }

    private void prepareRecyclerItems() {
        ArrayList<interviewFaqModel> arrayList = new ArrayList<>();
        databaseAccess = DatabaseAccess.getInstance(getContext());
        databaseAccess.open();
        arrayList = databaseAccess.getFavInterviewQuestion();
        databaseAccess.close();
        for(interviewFaqModel singleFAQ:arrayList){
            frequentQuestionsRecyclerItemsList.add(new interviewFaqModel(singleFAQ.getId(),singleFAQ.getQuestion(),singleFAQ.getAnswer(),singleFAQ.getFavourite()));
        }

        frequentQuestionsRecyclerItemsAdapter.notifyDataSetChanged();
    }

}
