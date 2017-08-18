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
    private boolean published;
    private LocalDateTime createdAt;
    private int id;
    private int memberId;
   // private static ArrayList<Member> instances = new ArrayList<Member>();
    //private static Map<String, List<String>> teamMembers = new HashMap<>();


    public Member(String memberName, int memberId) {
        this.memberName = memberName;
        this.memberId = memberId;
        //instances.add(this);
        this.published = false;
        this.createdAt = LocalDateTime.now();
        //memberId = instances.size();
    }


    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {this.memberName = memberName;}

//    public static ArrayList<Member> getAll() {
//        return instances;
//    }

//    public static void clearMember() {
//        instances.clear();
//    }

    public Member(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public boolean isPublished() {  return published; }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) { this.memberId = memberId; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    //    public static Member findById(int id) {
//            return instances.get(id - 1);
//    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    //Displaying Custom Objects
//    public static Map<String, List<String>> getTeamMembers()
//    {
//        return teamMembers;
//    }

//    public void update(String newValue) {
//        this.memberName = newValue;
//    }

}
