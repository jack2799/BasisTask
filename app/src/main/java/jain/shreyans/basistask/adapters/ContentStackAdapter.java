package jain.shreyans.basistask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import jain.shreyans.basistask.R;
import jain.shreyans.basistask.models.Content;

public class ContentStackAdapter extends RecyclerView.Adapter<ContentStackAdapter.ViewHolder> {

    private Context mContext;
    private List<Content> mContentList;

    public ContentStackAdapter(List<Content> contentList, Context context) {
        this.mContentList = contentList;
        this.mContext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(CardView cardView) {
            super(cardView);
            TextView textViewCard = (TextView) cardView.findViewById(R.id.textViewCard);
            textView = textViewCard;
        }
    }


    public void update(List<Content> data) {
        this.mContentList = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        ViewHolder vh = new ViewHolder(cardView);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(mContentList.get(position).mText);
    }



    @Override
    public int getItemCount() {
        return mContentList.size();
    }


}