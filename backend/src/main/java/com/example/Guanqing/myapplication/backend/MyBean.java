package com.example.Guanqing.myapplication.backend;

import com.udacity.gradle.jokelib.JokeLib;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;

    public String getData() {
        return new JokeLib().getRandomJoke();
    }

    public void setData(String data) {
        myData = data;
    }
}