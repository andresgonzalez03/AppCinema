package com.example.appcinema;

public class Movie {
    private final String titol;
    private final int imatge;
    private final String duration;
    private final String genre;
    private final String actors;
    private final String description;

    public Movie(String titol, int imatge, String duration, String genre, String actors, String description) {
        this.titol = titol;
        this.imatge = imatge;
        this.duration = duration;
        this.genre = genre;
        this.actors = actors;
        this.description = description;
    }
    public String getTitle() {
        return titol;
    }
    public int getImageResource() {
        return imatge;
    }
    public String getDuration() { return duration; }
    public String getGenre() { return genre; }
    public String getActors() { return actors; }
    public String getDescription() { return description; }
}

