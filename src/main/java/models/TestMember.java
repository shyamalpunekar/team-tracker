package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spunek on 8/11/17.
 */
public class TestMember {

    private String mMemberName;
    private static List<TestMember> instances = new ArrayList<TestMember>();
    private int mId;

    public TestMember(String name) {
        mMemberName = name;
        instances.add(this);
        mId = instances.size();
    }

    public String getMemberName() {
        return mMemberName;
    }

    public static List<TestMember> getAll() {
        return instances;
    }
    public static void clear() {
        instances.clear();
    }

    public int getId() {
        return mId;
    }
    public static TestMember find(int id) {
        return instances.get(id - 1);
    }
}
