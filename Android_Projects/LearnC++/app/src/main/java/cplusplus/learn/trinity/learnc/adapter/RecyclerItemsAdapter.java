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
import cplusplus.learn.trinity.learnc.model.RecyclerItems;


public class RecyclerItemsAdapter extends RecyclerView.Adapter<RecyclerItemsAdapter.MyViewHolder> {
 
    private Context mContext;
    private List<RecyclerItems> albumList;
 
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ImageView icon;
 
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            icon = (ImageView) view.findViewById(R.id.icon);
        }
    }
 
 
    public RecyclerItemsAdapter(Context mContext, List<RecyclerItems> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }
 
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_layout, parent, false);
 
        return new MyViewHolder(itemView);
    }
 
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        RecyclerItems recyclerItems = albumList.get(position);
        holder.title.setText(recyclerItems.getTitle());
        holder.description.setText(recyclerItems.getDescription());
        holder.icon.setImageResource(recyclerItems.getIcon());
    }

 

 
    @Override
    public int getItemCount() {
        return albumList.size();
    }
}