package cplusplus.learn.trinity.learnc.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;
import java.util.jar.Attributes;

import cplusplus.learn.trinity.learnc.R;
import cplusplus.learn.trinity.learnc.model.QuizRecyclerItems;
import cplusplus.learn.trinity.learnc.model.TutorialModel;


public class TutorialRecyclerItemsAdapter extends RecyclerView.Adapter<TutorialRecyclerItemsAdapter.MyViewHolder> {

    private Context mContext;
    private List<TutorialModel> tutorialModelsList;


    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView title,index;
        public Button testButton;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textViewTopic);
            index= (TextView) view.findViewById(R.id.textViewTutorialIndex);
            testButton=(Button)view.findViewById(R.id.buttonTest);
        }

    }



    public TutorialRecyclerItemsAdapter(Context mContext, List<TutorialModel> tutorialModelsList) {
        this.mContext = mContext;
        this.tutorialModelsList = tutorialModelsList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_tutorial_layout_2, parent, false);

        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        TutorialModel recyclerItem = tutorialModelsList.get(position);
        holder.title.setText(recyclerItem.getTopic());
        holder.index.setText(position+1+"");
        if(recyclerItem.getTestAvailable()) holder.testButton.setVisibility(View.VISIBLE);
        else holder.testButton.setVisibility(View.INVISIBLE);
        if ((position==0)||(position%4==0)) holder.index.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.rounded_textview));
        else if ((position==1)||(position%5==0)) holder.index.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.rounded_textview2));
        else if ((position%2==0)) holder.index.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.rounded_textview3));
        else if ((position%3==0)) holder.index.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.rounded_textview4));
    }

    @Override
    public int getItemCount() {
        return tutorialModelsList.size();
    }
}