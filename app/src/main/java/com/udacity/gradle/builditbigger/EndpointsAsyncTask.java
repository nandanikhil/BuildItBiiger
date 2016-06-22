package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

/**
 * Created by nikhil on 20/06/16.
 */
public class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private String LOG_TAG = EndpointsAsyncTask.class.getSimpleName();
    private static MyApi myApiService = null;
    private Context mContext;
    private Listener listener;

    public EndpointsAsyncTask(Context context, Listener listener) {
        this.mContext = context;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://diesel-client-134823.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }
        //mContext = params[0];
        try {
            return myApiService.tellAJoke().execute().getData();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        listener.onTaskComplete(result);
        Log.i(LOG_TAG, "result: " + result);

    }

    /**
     * Callback for the post execute of this async task
     */
    public interface Listener {
        void onTaskComplete(String joke);
    }
}
