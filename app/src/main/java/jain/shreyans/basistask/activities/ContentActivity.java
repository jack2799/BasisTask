package jain.shreyans.basistask.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import jain.shreyans.basistask.R;
import jain.shreyans.basistask.adapters.ContentStackAdapter;
import jain.shreyans.basistask.models.Content;
import jain.shreyans.basistask.utils.Constants;
import jain.shreyans.basistask.viewModels.ContentViewModel;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;

import java.util.List;


public class ContentActivity extends AppCompatActivity implements CardStackListener {

    private CardStackView content_cardStackView;
    private boolean mIsRewinded;
    private TextView page_textView;
    private int mContentSize;
    private int mCurrentPage;
    private boolean mIsRestartButtonClicked;
    private ImageButton restart_imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentViewModel contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);
        contentViewModel.loadData();
        content_cardStackView = (CardStackView) findViewById(R.id.content_cardStackView);
        CardStackLayoutManager cardStackLayoutManager = new CardStackLayoutManager(this, this);
        content_cardStackView.setLayoutManager(cardStackLayoutManager);
        cardStackLayoutManager.setStackFrom(StackFrom.Bottom);
        cardStackLayoutManager.setMaxDegree(60.0f);
        cardStackLayoutManager.setDirections(Constants.CUSTOM);
        ContentStackAdapter contentStackAdapter = new ContentStackAdapter(contentViewModel.mData.getValue(), ContentActivity.this);
        content_cardStackView.setAdapter(contentStackAdapter);


        contentViewModel.mData.observe(this, new Observer<List<Content>>() {
            @Override
            public void onChanged(List<Content> contentList) {
                contentStackAdapter.update(contentList);
                contentStackAdapter.notifyDataSetChanged();
                if (contentList.size() != 0) {
                    mContentSize = contentList.size();
                    page_textView.setText("Showing page " + (mCurrentPage + 1) + " of " + (mContentSize));
                    restart_imageButton.setVisibility(View.VISIBLE);
                }
            }
        });

        page_textView = (TextView) findViewById(R.id.page_textView);
        restart_imageButton = ((ImageButton) findViewById(R.id.restart_imageButton));
        restart_imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content_cardStackView.rewind();
                mIsRestartButtonClicked = true;
            }
        });

    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardSwiped(Direction direction) {
        if (direction == Direction.Top)
            content_cardStackView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    content_cardStackView.rewind();
                    mIsRewinded = true;
                }
            }, 500);
    }

    @Override
    public void onCardRewound() {
        content_cardStackView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mIsRewinded) {
                    content_cardStackView.rewind();
                    mIsRewinded = false;
                }
            }
        }, 500);
        content_cardStackView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mIsRestartButtonClicked && mCurrentPage != 1) {
                    content_cardStackView.rewind();
                } else {
                    mIsRestartButtonClicked = false;
                }
            }
        }, 500);

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {
        mCurrentPage = position + 1;
        page_textView.setText("Showing page " + (mCurrentPage) + " of " + (mContentSize));
    }

    @Override
    public void onCardDisappeared(View view, int position) {

    }
}


