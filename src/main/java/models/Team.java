package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaxe1 on 8/11/17.
 */
public class Team {
    private String mTeamname;

    private static List<Team> instances = new ArrayList<Team>();

    public Team (String mTeamname) {
        this.mTeamname = mTeamname;
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
}
