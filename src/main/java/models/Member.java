package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spunek on 8/11/17.
 */
public class Member {

    private String mMemberName;
    private static List<Member> instances = new ArrayList<Member>();
    private int mId;

    public Member (String name) {
        mMemberName = name;
        instances.add(this);
        mId = instances.size();
    }

    public String getMemberName() {
        return mMemberName;
    }

    public static List<Member> getAll() {
        return instances;
    }
    public static void clear() {
        instances.clear();
    }

    public int getId() {
        return mId;
    }
    public static Member find(int id) {
        return instances.get(id - 1);
    }
}
