package com.example.guanqing.jokelibrary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class JokeTellingActivityFragment extends Fragment {
    private String joke = "";

    public JokeTellingActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_joke_telling, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.joke_text_view);

        if (getArguments()!=null){
            joke = getArguments().getString(JokeTellingActivity.joke_key);
            textView.setText(joke);
        }

        return rootView;
    }

}
