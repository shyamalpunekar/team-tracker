package models;

import java.util.ArrayList;

/**
 * Created by ssaxe1 on 8/11/17.
 */
public class Definition {
    private String description;
    private static ArrayList<Definition> instances = new ArrayList<Definition>();
    private int id;

    public Definition(String description) {
        this.description = description;
        instances.add(this);
        id = instances.size();
    }

    public String getDescription() {
        return description;
    }

    public static ArrayList<Definition> getAll() {
        return instances;
    }

    public static void clearDefinition() {
        instances.clear();
    }

    public int getId() {
        return id;
    }

    public static Definition find(int id) {
            return instances.get(id - 1);
    }
}
