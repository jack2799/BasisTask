package jain.shreyans.basistask.viewModels;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import jain.shreyans.basistask.models.Content;
import jain.shreyans.basistask.repository.ContentRepository;

public class ContentViewModel extends ViewModel implements ContentRepository.RepositoryCallback {
    public MutableLiveData<List<Content>> mData = new MutableLiveData<>();

    public void loadData() {
        ContentRepository.getInstance().getContent(this);
        mData.setValue(new ArrayList<Content>());
    }

    @Override
    public void onContent(List<Content> contentList) {
        mData.postValue(contentList);
    }
}
