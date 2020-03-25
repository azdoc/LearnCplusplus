package cplusplus.learn.trinity.learnc.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.List;

import cplusplus.learn.trinity.learnc.R;
import cplusplus.learn.trinity.learnc.db.DatabaseAccess;
import cplusplus.learn.trinity.learnc.model.interviewFaqModel;


public class FrequentQuestionsRecyclerItemsAdapter extends RecyclerView.Adapter<FrequentQuestionsRecyclerItemsAdapter.MyViewHolder> {

    private Context mContext;
    private List<interviewFaqModel> frequentQuestionsRecyclerList;
    DatabaseAccess databaseAccess;
    String interViewOrFaq;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question, answer, textViewOptions;
        public ExpandableLayout expandableLayout;
        public TextView circularTextView;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.textViewQuestion);
            answer = (TextView) view.findViewById(R.id.textViewAnswer);
            expandableLayout = (ExpandableLayout) view.findViewById(R.id.expandable_layout);
            circularTextView = (TextView) view.findViewById(R.id.circularTextView);
            textViewOptions = (TextView) view.findViewById(R.id.textViewOptions);
            imageView = (ImageView) view.findViewById(R.id.imageViewArrow);
            /*textViewOptions.setOnClickListener(this);
            view.setOnClickListener(this);
*/
        }


        /*@Override
        public void onClick(View v) {
            if (v.getId() == textViewOptions.getId()){
                Toast.makeText(v.getContext(), "Added to Favourites", Toast.LENGTH_SHORT).show();
                databaseAccess = DatabaseAccess.getInstance(v.getContext());
                databaseAccess.open();
                //String fav = v.
                databaseAccess.updateFaq();
                databaseAccess.close();
            }
            else {
                expandableLayout.toggle();
                if (imageView.getTag().equals("down"))
                {
                    imageView.setImageResource(R.drawable.up_arrow);
                    imageView.setTag("up");
                }
                else if (imageView.getTag().equals("up"))
                {
                    imageView.setImageResource(R.drawable.down_arrow);
                    imageView.setTag("down");
                }

            }
        }*/
    }


    public FrequentQuestionsRecyclerItemsAdapter(Context mContext, List<interviewFaqModel> frequentQuestionsRecyclerList, String interViewOrFaq) {
        this.mContext = mContext;
        this.frequentQuestionsRecyclerList = frequentQuestionsRecyclerList;
        this.interViewOrFaq = interViewOrFaq;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_frequent_questions_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final interviewFaqModel recyclerItems = frequentQuestionsRecyclerList.get(position);
        // check if item is added is favourite
        final String fav = recyclerItems.getFavourite();
        final String message = fav.equals("Yes") ? "Removed from Favourites" : "Added to Favourites";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ColorStateList colorStateList = fav.equals("Yes") ? mContext.getResources().getColorStateList(R.color.red) : mContext.getResources().getColorStateList(R.color.grey);
            holder.textViewOptions.setCompoundDrawableTintList(colorStateList);
        }

        holder.question.setText(recyclerItems.getQuestion());
        holder.answer.setText(recyclerItems.getAnswer());
        holder.circularTextView.setText(position + 1 + "");
        holder.textViewOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),interViewOrFaq+" "+ message+" "+fav, Toast.LENGTH_SHORT).show();
                databaseAccess = DatabaseAccess.getInstance(v.getContext());
                databaseAccess.open();

                if (interViewOrFaq.equalsIgnoreCase("Interview"))
                    databaseAccess.updateInterviewQues(recyclerItems, fav.equals("Yes") ? "No" : "Yes");
                else
                    databaseAccess.updateFaq(recyclerItems, fav.equals("Yes") ? "No" : "Yes");

                databaseAccess.close();
                frequentQuestionsRecyclerList.get(position).setFavourite(fav.equals("Yes") ? "No" : "Yes");
                FrequentQuestionsRecyclerItemsAdapter.this.notifyDataSetChanged();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ColorStateList colorStateList = fav.equals("Yes") ? mContext.getResources().getColorStateList(R.color.red) : mContext.getResources().getColorStateList(R.color.grey);
                    holder.textViewOptions.setCompoundDrawableTintList(colorStateList);
                }

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.expandableLayout.toggle();
                if (holder.imageView.getTag().equals("down")) {
                    holder.imageView.setImageResource(R.drawable.up_arrow);
                    holder.imageView.setTag("up");
                } else if (holder.imageView.getTag().equals("up")) {
                    holder.imageView.setImageResource(R.drawable.down_arrow);
                    holder.imageView.setTag("down");
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return frequentQuestionsRecyclerList.size();
    }
}