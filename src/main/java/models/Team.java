package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by spunek on 8/11/17.
 */
public class Team {
    private String meaning;

    private boolean published;

    private LocalDateTime createdAt;

    private int id;

    private static ArrayList<Team> instances = new ArrayList<>();




    public Team(String meaning) {
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
    public static ArrayList<Team> getAll(){
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

    public static Team findById(int id) {
        return instances.get(id -1);
    }

    public void update(String newValue) {
        this.meaning = newValue;
    }

    public void deleteBlog(){
        instances.remove(id-1); //same reason
    }

    public void addMember(Member member) {
       instances.add(this);
    }



    }







//
//