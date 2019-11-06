package jain.shreyans.basistask.okHttp;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {


    public void get(String url, ResponseCallback responseCallback) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseCallback.onSuccess(response.body().string());
            }
        });

    }

    public interface ResponseCallback {
        public void onSuccess(String response);
    }
}
