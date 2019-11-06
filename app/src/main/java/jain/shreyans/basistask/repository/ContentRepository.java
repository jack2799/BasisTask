package jain.shreyans.basistask.repository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jain.shreyans.basistask.models.Content;
import jain.shreyans.basistask.okHttp.OkHttp;
import jain.shreyans.basistask.utils.Constants;

public class ContentRepository implements OkHttp.ResponseCallback {

    private static ContentRepository mInstance;
    private RepositoryCallback mRepositoryCallback;

    public static ContentRepository getInstance() {
        if (mInstance == null)
            mInstance = new ContentRepository();
        return mInstance;
    }

    public void getContent(RepositoryCallback repositoryCallback) {
        mInstance.mRepositoryCallback = repositoryCallback;
        String response = null;
        try {
            new OkHttp().get(Constants.url, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response == null) {
            //display message to user and exit or return
        }
    }

    private List<Content> parseResponse(String response) throws JSONException {
        List<Content> contentList = new ArrayList<>();
        String json = response.substring(1);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray dataArray = jsonObject.getJSONArray("data");
        for (int i = 0; i < dataArray.length(); i++) {
            Content content = new Content();
            content.mId = dataArray.getJSONObject(i).getString("id");
            content.mText = dataArray.getJSONObject(i).getString("text");
            contentList.add(content);
        }
        return contentList;
    }


    @Override
    public void onSuccess(String response) {
        List<Content> contentList = null;
        try {
            contentList = parseResponse(response);
            mRepositoryCallback.onContent(contentList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (contentList == null) {
            //display message to user and return
        }

    }

    public interface RepositoryCallback {
        public void onContent(List<Content> contentList);
    }
}
