package cplusplus.learn.trinity.learnc.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cplusplus.learn.trinity.learnc.R;
import cplusplus.learn.trinity.learnc.activites.TutorialDispalyActivity;
import cplusplus.learn.trinity.learnc.adapter.TutorialRecyclerItemsAdapter;
import cplusplus.learn.trinity.learnc.model.TutorialModel;
import cplusplus.learn.trinity.learnc.utilities.ClickListener;
import cplusplus.learn.trinity.learnc.utilities.CommonActivity;
import cplusplus.learn.trinity.learnc.utilities.RecyclerTouchListener;


public class AdvanceFragment extends Fragment {

    private RecyclerView recyclerView;
    private TutorialRecyclerItemsAdapter recyclerItemsAdapter;

    public AdvanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tutorials_home, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        List<TutorialModel>recyclerItemsList = createTutorials();
        recyclerItemsAdapter = new TutorialRecyclerItemsAdapter(getActivity(), recyclerItemsList);

        //RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2); // MAX NUMBER OF SPACES
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0 || position == 3 || position == 6 || position == 9) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerItemsAdapter);
        recyclerItemsAdapter.notifyDataSetChanged();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                List<TutorialModel>recyclerItemsList= createTutorials();
                if (recyclerItemsList.get(position).getList()){
                    final CharSequence[] items= new CharSequence[recyclerItemsList.get(position).getOptions().size()]; //array of topics for popup
                    final List<TutorialModel> options=recyclerItemsList.get(position).getOptions(); //get list of topics form selected topic
                    for (int i = 0; i < items.length; i++) {
                        items[i]=options.get(i).getTopic();// populate array of topics
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Select Topic");
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            for (int i=0;i<options.size();i++) // run a loop on options list
                            {
                                if (items[item].equals(options.get(i).getTopic())) //based on Topic selected from array search for topic from options lisgt
                                {
                                    Intent intent = new Intent(getContext(), TutorialDispalyActivity.class);
                                    intent.putExtra("tutorial", options.get(i));
                                    startActivity(intent);
                                }
                            }
                            dialog.dismiss();
                        }
                    }).show();
                }else {
                    Intent intent = new Intent(getContext(), TutorialDispalyActivity.class);
                    intent.putExtra("tutorial", recyclerItemsList.get(position));
                    startActivity(intent);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return rootView;
    }

    public List<TutorialModel> createTutorials(){

        final String toolBarTitle = "Tutorials";

        ArrayList<TutorialModel> listTutorials = new ArrayList<TutorialModel>() ;
        listTutorials.add(new TutorialModel("1","Pointers",false,null,R.string.tutorial_advance_pointers,false,toolBarTitle));
        listTutorials.add(new TutorialModel("2","References",false,null,R.string.tutorial_advance_references,false,toolBarTitle));
        listTutorials.add(new TutorialModel("3","Exception Handling",false,null,R.string.tutorial_advance_exception,false,toolBarTitle));
        listTutorials.add(new TutorialModel("4","File & Stream",false,null,R.string.tutorial_advance_file,false,toolBarTitle));
        listTutorials.add(new TutorialModel("5","Virtual Function",false,null,R.string.tutorial_advance_virtual,false,toolBarTitle));
        listTutorials.add(new TutorialModel("6","Abstract class",false,null,R.string.tutorial_advance_abstract_class,false,toolBarTitle));
        listTutorials.add(new TutorialModel("7","Virtual Destructor",false,null,R.string.tutorial_advance_virtual_desc,false,toolBarTitle));
        listTutorials.add(new TutorialModel("8","Upcasting",false,null,R.string.tutorial_advance_upcasting,false,toolBarTitle));
        listTutorials.add(new TutorialModel("9","Multithreading",false,null,R.string.tutorial_advance_multithreadding,false,toolBarTitle));
        listTutorials.add(new TutorialModel("10","Dynamic Memory",false,null,R.string.tutorial_advance_dynamic_memory,false,toolBarTitle));

        return listTutorials;
    }

}
