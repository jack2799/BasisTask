package jain.shreyans.basistask.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import jain.shreyans.basistask.R;
import jain.shreyans.basistask.adapters.ContentStackAdapter;
import jain.shreyans.basistask.models.Content;
import jain.shreyans.basistask.viewModels.ContentViewModel;
import link.fls.swipestack.SwipeStack;
import android.os.Bundle;
import java.util.List;


public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentViewModel contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);
        contentViewModel.loadData();
        SwipeStack contentStack = (SwipeStack) findViewById(R.id.contentStack);
        ContentStackAdapter contentStackAdapter = new ContentStackAdapter(contentViewModel.mData.getValue(), ContentActivity.this);
        contentStack.setAdapter(contentStackAdapter);

        contentViewModel.mData.observe(this, new Observer<List<Content>>() {
            @Override
            public void onChanged(List<Content> contentList) {
                contentStackAdapter.update(contentList);
                contentStackAdapter.notifyDataSetChanged();
            }
        });

    }

}


