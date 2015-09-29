package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.guanqing.jokelibrary.JokeTellingActivity;
import com.example.guanqing.myapplication.backend.myJokesApi.MyJokesApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import de.greenrobot.event.EventBus;


public class MainActivity extends ActionBarActivity {
    private String joke;
    private static final String joke_key = "JOKE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new EndpointsAsyncTask().execute(getApplicationContext());

        EventBus.getDefault().register(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onEvent(JokeEvent e){
        joke = e.joke;
    }

    public String getJoke(){
        return joke;
    }

    public void tellJoke(View view){
        new EndpointsAsyncTask().execute(getApplicationContext());
        Log.e("HGQ DEBUG", "Got a Joke From the ApiServer: " + joke);
        if (getJoke() != null) {
            Intent intent = new Intent(this, JokeTellingActivity.class)
                    .putExtra(joke_key, getJoke());
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    /**
     * EndpointsAsyncTask class
     * get the joke string from MyJokesApi
     */
    class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
        private MyJokesApi myApi;
        private Context context;

        @Override
        protected String doInBackground(Context... params) {
            if (myApi == null) {  // Only do this once
                MyJokesApi.Builder builder = new MyJokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("https://makeitbigger-1083.appspot.com/_ah/api/");

                myApi = builder.build();
            }
            context = params[0];

            try {
                String joke = myApi.getMyJokes().execute().getJoke();
                JokeEvent event = new JokeEvent(joke);
                //post the event
                EventBus.getDefault().post(event);
                return joke;
            } catch (IOException e) {
                return e.getMessage();
            }
        }

    }
}
