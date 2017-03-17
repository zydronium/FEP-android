package hu.nl.actortemplateapp.data_classes;

import java.util.ArrayList;

/**
 * Created by Dyon on 20-2-2017.
 */

public class Project {
    public String name;
    public String description;
    public String key;
    public ArrayList<User> users;

    public Project(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

}
