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
    private String teamName;
    private boolean published;
    private LocalDateTime createdAt;
    private int id;

    //private static ArrayList<Team> instances = new ArrayList<>();


    public Team(String teamName) {
        this.teamName = teamName;
       // instances.add(this);
        this.published = false;
        this.createdAt = LocalDateTime.now();
       // this.id = instances.size();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) { this.teamName = teamName; }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    //Displaying Custom Objects
//    public static ArrayList<Team> getAll(){
//        return instances;
//    }

//    public static void clearAllBlogs() {
//        instances.clear();
//    }

    public boolean getPublished() {
        return published;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (published != team.published) return false;
        if (id != team.id) return false;
        return teamName.equals(team.teamName);

    }

    @Override
    public int hashCode() {
        int result = teamName.hashCode();
        result = 31 * result + (published ? 1 : 0);
        result = 31 * result + id;
        return result;
    }

    //    public static Team findById(int id) {
//        return instances.get(id -1);
//    }

//    public void update(String newValue) {
//        this.teamName = newValue;
//    }
//
//    public void deleteBlog(){
//        instances.remove(id-1); //same reason
//    }
//
//    public void addMember(Member member) {
//       instances.add(this);
//    }

}







//
//