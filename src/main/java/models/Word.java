package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by Guest on 8/10/17.
 */
public class Word {

    private String meaning;

    private boolean published;

    private LocalDateTime createdAt;


    private int id;

    private static ArrayList<Word> instances = new ArrayList<>();

    public Word(String meaning) {
        this.meaning = meaning;
        instances.add(this);
        this.published = false;
        this.createdAt = LocalDateTime.now();
        this.id = instances.size();
    }

    public String getMeaning() {
        return meaning;
    }

    public int getId() {
        return id;
    }

    //Displaying Custom Objects
    public static ArrayList<Word> getAll(){
        return instances;
    }

    public static void clearAllBlogs() {
        instances.clear();
    }

    public boolean getPublished() {
        return published;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static Word findById(int id) {
        return instances.get(id -1);
    }

    public void update(String newValue) {
        this.meaning = newValue;
    }

    public void deleteBlog(){
        instances.remove(id-1); //same reason
    }
}

