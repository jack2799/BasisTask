package jain.shreyans.basistask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
import jain.shreyans.basistask.R;
import jain.shreyans.basistask.models.Content;

public class ContentStackAdapter extends BaseAdapter {

    private Context mContext;
    private List<Content> mContentList;

    public ContentStackAdapter(List<Content> contentList, Context context) {
        this.mContentList = contentList;
        this.mContext = context;
    }

    public void update(List<Content> data) {
        this.mContentList = data;
    }

    @Override
    public int getCount() {
        return mContentList.size();
    }

    @Override
    public String getItem(int position) {
        return mContentList.get(position).mText;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.card, parent, false);
        }

        TextView textViewCard = (TextView) convertView.findViewById(R.id.textViewCard);
        textViewCard.setText(mContentList.get(position).mText);

        return convertView;
    }
}