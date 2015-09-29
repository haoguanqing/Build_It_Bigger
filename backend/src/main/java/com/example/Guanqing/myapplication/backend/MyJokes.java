package com.example.Guanqing.myapplication.backend;

import com.udacity.gradle.jokelib.JokeLib;

/**
 * Created by Guanqing on 2015/9/29.
 */
public class MyJokes {
    private int jokeNumber;

    public void setJokeNumber(int i){ jokeNumber = i;}

    public String getJoke() {
        return new JokeLib().getRandomJoke();
    }
}
