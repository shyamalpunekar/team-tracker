package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaxe1 on 8/11/17.
 */
public class Team {
    private String mTeamname;
    private int mId;
    private List<Member> mMembers;

    private static List<Team> instances = new ArrayList<Team>();

    public Team (String mTeamname) {
        this.mTeamname = mTeamname;
        this.instances.add(this);
        mId = instances.size();
        mMembers = new ArrayList<Member>();
    }

    public String getmTeamname() {
        return mTeamname;
    }

    public static List<Team> getInstances() {
        return instances;
    }

    public static List<Team> all() {
        return instances;
    }

    public static void clear() {
        instances.clear();
    }

    public int getmId() {
        return mId;
    }

    public static Team find(int id) {
        return instances.get(id - 1);
    }

    public List<Member> getMembers() {
        return mMembers;
    }

    public void addMember(Member member) {
        mMembers.add(member);
    }
}
