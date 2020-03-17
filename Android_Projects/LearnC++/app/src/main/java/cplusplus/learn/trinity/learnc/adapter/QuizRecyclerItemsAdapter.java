package cplusplus.learn.trinity.learnc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cplusplus.learn.trinity.learnc.R;
import cplusplus.learn.trinity.learnc.model.QuizRecyclerItems;
import cplusplus.learn.trinity.learnc.model.RecyclerItems;


public class QuizRecyclerItemsAdapter extends RecyclerView.Adapter<QuizRecyclerItemsAdapter.MyViewHolder> {

    private Context mContext;
    private List<QuizRecyclerItems> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, bestScore,attempts;
        public ImageView icon;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            bestScore = (TextView) view.findViewById(R.id.bestScore);
            attempts = (TextView) view.findViewById(R.id.attempts);
            icon = (ImageView) view.findViewById(R.id.icon);
        }
    }


    public QuizRecyclerItemsAdapter(Context mContext, List<QuizRecyclerItems> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_quiz_layout, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        QuizRecyclerItems recyclerItems = albumList.get(position);
        holder.title.setText(recyclerItems.getTitle());
        holder.bestScore.setText(recyclerItems.getBestScore());
        holder.attempts.setText(recyclerItems.getAttempts());
        holder.icon.setImageResource(recyclerItems.getIcon());
    }

 

 
    @Override
    public int getItemCount() {
        return albumList.size();
    }
}