package com.example.petarstanev.mathgame;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Score implements Comparable, Serializable {
    private int points;
    private String name;

    public Score() {
    }

    public Score(int points, String name) {
        this.points = points;
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object comapereTo) {
        int comparePoints=((Score)comapereTo).getPoints();
        return comparePoints-points;
    }
}
