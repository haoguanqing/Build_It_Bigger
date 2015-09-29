package com.udacity.gradle.jokelib;

import java.util.Random;

public class JokeLib {

    public String getRandomJoke(){
        Random random = new Random();
        return "random joke "+ random.nextInt(100);
    }
}
