package cplusplus.learn.trinity.learnc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cplusplus.learn.trinity.learnc.R;


public class ProgramRecyclerItemsAdapter extends RecyclerView.Adapter<ProgramRecyclerItemsAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }


    public ProgramRecyclerItemsAdapter(Context mContext, List<String> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_single_layout, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String recyclerItem = albumList.get(position);
        holder.title.setText(recyclerItem);
    }

 

 
    @Override
    public int getItemCount() {
        return albumList.size();
    }
}