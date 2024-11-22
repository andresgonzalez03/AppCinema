package com.example.appcinema;

public class Movie {
    private String titol;
    private int imatge;

    public Movie(String titol, int imatge) {
        this.titol = titol;
        this.imatge = imatge;
    }public String getTitle() {
        return titol;
    }
    public int getImageResource() {
        return imatge;
    }
}

