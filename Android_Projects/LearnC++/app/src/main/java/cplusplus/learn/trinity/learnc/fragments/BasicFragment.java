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
import cplusplus.learn.trinity.learnc.utilities.RecyclerTouchListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class BasicFragment extends Fragment {

    private RecyclerView recyclerView;
    private TutorialRecyclerItemsAdapter recyclerItemsAdapter;

    public BasicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tutorials_home, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        List<TutorialModel> recyclerItemsList = createTutorials();
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
                List<TutorialModel> recyclerItemsList = createTutorials();
                if (recyclerItemsList.get(position).getList()) {
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

                } else {
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

    public List<TutorialModel> createTutorials() {

        final String toolBarTitle = "Tutorials";

        ArrayList<TutorialModel> introductionList = new ArrayList<>();
        introductionList.add(new TutorialModel("1", "What is C++?", false, null, R.string.tutorial_basic_what_is_cpp,false,toolBarTitle));
        introductionList.add(new TutorialModel("2", "Features", false, null, R.string.tutorial_basic_features,false,toolBarTitle));
        introductionList.add(new TutorialModel("3", "Why you should learn C++", false, null, R.string.tutorial_basic_why_learn_cpp,false,toolBarTitle));

        ArrayList<TutorialModel> variablesList = new ArrayList<>();
        variablesList.add(new TutorialModel("1", "What are variables?", false, null, R.string.tutorial_basic_variables,false,toolBarTitle));
        variablesList.add(new TutorialModel("2", "Rules for defining a variable", false, null, R.string.tutorial_basic_rules_variables,false,toolBarTitle));
        variablesList.add(new TutorialModel("3", "Declaration & initialization", false, null, R.string.tutorial_basic_declaration,false,toolBarTitle));
        variablesList.add(new TutorialModel("4", "Scope of variables", false, null, R.string.tutorial_basic_scope_of_var,false,toolBarTitle));

        ArrayList<TutorialModel> datatypesList = new ArrayList<>();
        datatypesList.add(new TutorialModel("1", "Types of datatypes", false, null, R.string.tutorial_basic_types_datatypes,false,toolBarTitle));
        datatypesList.add(new TutorialModel("2", "Modifiers", false, null, R.string.tutorial_basic_modifiers,false,toolBarTitle));
        datatypesList.add(new TutorialModel("3", "Keyword", false, null, R.string.tutorial_basic_keywords,false,toolBarTitle));

        ArrayList<TutorialModel> operatorsList = new ArrayList<>();
        operatorsList.add(new TutorialModel("1", "What are operators & type of operators", false, null, R.string.tutorial_basic_operators,false,toolBarTitle));
        operatorsList.add(new TutorialModel("2", "Understanding operators & basic syntax", false, null, R.string.tutorial_basic_operators_syntax,false,toolBarTitle));
        operatorsList.add(new TutorialModel("3", "Sizeof and typedef operators", false, null, R.string.tutorial_basic_sizeof,false,toolBarTitle));

        ArrayList<TutorialModel> decisionList = new ArrayList<>();
        decisionList.add(new TutorialModel("1", "If statement", false, null, R.string.tutorial_basic_if,false,toolBarTitle));
        decisionList.add(new TutorialModel("2", "If…else statement", false, null, R.string.tutorial_basic_if_else,false,toolBarTitle));
        decisionList.add(new TutorialModel("3", "Nested if statement", false, null, R.string.tutorial_basic_nested_if,false,toolBarTitle));
        decisionList.add(new TutorialModel("4", "Else if statement", false, null, R.string.tutorial_basic_else_if,false,toolBarTitle));

        ArrayList<TutorialModel> loopsList = new ArrayList<>();
        loopsList.add(new TutorialModel("1", "While loop", false, null, R.string.tutorial_basic_while_loop,false,toolBarTitle));
        loopsList.add(new TutorialModel("2", "For loop", false, null, R.string.tutorial_basic_for_loop,false,toolBarTitle));
        loopsList.add(new TutorialModel("3", "Do while loop", false, null, R.string.tutorial_basic_do_while,false,toolBarTitle));

        ArrayList<TutorialModel> functionList = new ArrayList<>();
        functionList.add(new TutorialModel("1", "Understanding function & its Syntax", false, null, R.string.tutorial_basic_function_syntax,false,toolBarTitle));
        functionList.add(new TutorialModel("2", "Declaring, Defining & Calling a function", false, null, R.string.tutorial_basic_calling_function,false,toolBarTitle));

        ArrayList<TutorialModel> listTutorials = new ArrayList<TutorialModel>();
        listTutorials.add(new TutorialModel("1", "Introduction", true, introductionList, 0,false,toolBarTitle));
        listTutorials.add(new TutorialModel("2", "Installation", false, null, R.string.tutorial_basic_installation,false,toolBarTitle));
        listTutorials.add(new TutorialModel("3", "First C++ Program", false, null, R.string.tutorial_basic_first_program,false,toolBarTitle));
        listTutorials.add(new TutorialModel("4", "Variables", true, variablesList, 0,false,toolBarTitle));
        listTutorials.add(new TutorialModel("5", "Datatypes &\n Modifiers", true, datatypesList, 0,false,toolBarTitle));
        listTutorials.add(new TutorialModel("6", "Operators", true, operatorsList, 0,false,toolBarTitle));
        listTutorials.add(new TutorialModel("7", "Decision making", true, decisionList, 0,false,toolBarTitle));
        listTutorials.add(new TutorialModel("8", "Loops", true, loopsList, 0,false,toolBarTitle));
        listTutorials.add(new TutorialModel("9", "Storage variables", false, null, R.string.tutorial_basic_storage,false,toolBarTitle));
        listTutorials.add(new TutorialModel("10", "Functions ", true, functionList, 0,false,toolBarTitle));


        return listTutorials;
    }


}




