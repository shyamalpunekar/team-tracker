package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ssaxe1 on 8/11/17.
 */
public class Member {
    private String memberName;
    private static ArrayList<Member> instances = new ArrayList<Member>();
    private static Map<String, List<String>> teamMembers = new HashMap<>();

    public String getMemberName() {
        return memberName;
    }

    private boolean published;


    private LocalDateTime createdAt;

    private int id;

    public Member(String memberName) {
        this.memberName = memberName;
        instances.add(this);
        this.published = false;
        this.createdAt = LocalDateTime.now();
        id = instances.size();
    }



    public static ArrayList<Member> getAll() {
        return instances;
    }

    public static void clearMember() {
        instances.clear();
    }

    public Member(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public boolean isPublished() {  return published; }

    public int getId() {
        return id;
    }

    public static Member findById(int id) {
            return instances.get(id - 1);
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    //Displaying Custom Objects
    public static Map<String, List<String>> getTeamMembers(){
        return teamMembers;
    }

    public void update(String newValue) {
        this.memberName = newValue;
    }

}
