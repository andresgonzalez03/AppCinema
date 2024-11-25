package com.example.appcinema;

public class Movie {
    private String titol;
    private int imatge;
    private String duration;
    private String genre;
    private String actors;
    private String description;

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

