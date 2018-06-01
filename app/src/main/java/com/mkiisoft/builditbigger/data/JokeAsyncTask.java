package com.mkiisoft.builditbigger.data;

import android.os.AsyncTask;

import com.example.mkiisoft.endpoints.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.mkiisoft.builditbigger.listeners.ApiListener;

import java.io.IOException;

import static com.mkiisoft.builditbigger.utils.Constants.LOCAL_DEVICE_ADDRESS;

public class JokeAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi apiServer;
    private ApiListener<String> listener;

    private boolean isTest;

    public JokeAsyncTask(ApiListener<String> listener, boolean isTest) {
        this.listener = listener;
        this.isTest = isTest;
    }

    @Override
    protected void onPreExecute() {
        if (!isTest) {
            listener.loading();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        if (apiServer == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(LOCAL_DEVICE_ADDRESS)
                    .setGoogleClientRequestInitializer(abstractGoogleClientRequest ->
                            abstractGoogleClientRequest.setDisableGZipContent(true));

            apiServer = builder.build();
        }

        try {
            return apiServer.getRandomJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        listener.finish(result);
    }
}