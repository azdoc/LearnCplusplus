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

/**
 * A simple {@link Fragment} subclass.
 */
public class ObjectFragment extends Fragment {

    private RecyclerView recyclerView;
    private TutorialRecyclerItemsAdapter recyclerItemsAdapter;

    public ObjectFragment() {
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

        ArrayList<TutorialModel> classesList=new ArrayList<>();
        classesList.add(new TutorialModel("1","Classes",false,null,R.string.tutorial_object_classes,false));
        classesList.add(new TutorialModel("2","Objects",false,null,R.string.tutorial_object_objects,false));
        classesList.add(new TutorialModel("3","Access Controllers",false,null,R.string.tutorial_object_access_control,false));
        classesList.add(new TutorialModel("4","Defining Class & declaring objects",false,null,R.string.tutorial_object_declaring_class,false));
        classesList.add(new TutorialModel("5","Accessing data members ",false,null,R.string.tutorial_object_data_members,false));

        ArrayList<TutorialModel> memberList=new ArrayList<>();
        memberList.add(new TutorialModel("1","What are member functions?",false,null,R.string.tutorial_object_member_functions,false));
        memberList.add(new TutorialModel("2","Types of member functions",false,null,R.string.tutorial_object_types_members_function,false));

        ArrayList<TutorialModel> inlineList=new ArrayList<>();
        inlineList.add(new TutorialModel("1","Inline functions in c++ and drawbacks of macro",false,null,R.string.tutorial_object_inline_functions,false));
        inlineList.add(new TutorialModel("2","Limitations of Inline functions",false,null,R.string.tutorial_object_limitation_inline,false));

        ArrayList<TutorialModel> inheritanceList=new ArrayList<>();
        inheritanceList.add(new TutorialModel("1","Basics of Inheritance",false,null,R.string.tutorial_object_inheritance_basic,false));
        inheritanceList.add(new TutorialModel("2","Types of Inheritance",false,null,R.string.tutorial_object_types_inheritance,false));

        ArrayList<TutorialModel> consList=new ArrayList<>();
        consList.add(new TutorialModel("1","What are Constructors?",false,null,R.string.tutorial_object_constructors,false));
        consList.add(new TutorialModel("2","Type of Constructors",false,null,R.string.tutorial_object_types_constructors,false));
        consList.add(new TutorialModel("3","Constructors Overloading",false,null,R.string.tutorial_object_constructors_overloading,false));
        consList.add(new TutorialModel("4","What are  Destructors?",false,null,R.string.tutorial_object_desctructors,false));


        ArrayList<TutorialModel> listTutorials = new ArrayList<TutorialModel>() ;
        listTutorials.add(new TutorialModel("1","OOPS Concept Basic",false,null,R.string.tutorial_object_concept,false));
        listTutorials.add(new TutorialModel("2","Classes and objects",true,classesList,0,false));
        listTutorials.add(new TutorialModel("3","Member functions",true,memberList,0,false));
        listTutorials.add(new TutorialModel("4","Inline functions",true,inlineList,0,false));
        listTutorials.add(new TutorialModel("5","Inheritance",true,inheritanceList,0,false));
        listTutorials.add(new TutorialModel("5","Function Overloading",false,null,R.string.tutorial_object_fun_overloading,false));
        listTutorials.add(new TutorialModel("5","Function Overriding",false,null,R.string.tutorial_object_fun_overriding,false));
        listTutorials.add(new TutorialModel("6","Constructors &\n Destructors",true,consList,0,false));
        listTutorials.add(new TutorialModel("7","Namespace",false,null,R.string.tutorial_object_namespace,false));
        listTutorials.add(new TutorialModel("8","Static Keyword",false,null,R.string.tutorial_object_static,false));
        listTutorials.add(new TutorialModel("9","Constant Keyword",false,null,R.string.tutorial_object_conts,false));
        listTutorials.add(new TutorialModel("10","Mutable Keyword",false,null,R.string.tutorial_object_mutuable,false));
        listTutorials.add(new TutorialModel("11","Copy Constructor",false,null,R.string.tutorial_object_copy,false));


        return listTutorials;
    }




}


