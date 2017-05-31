package hu.nl.actortemplateapp.data_classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Dyon on 5-3-2017.
 */

public class ActorTemplate {
    private String actor;
    private String beschrijving;
    private String key;
    private boolean isArchived = false;
    private HashMap<String, Object> actoren;

    public ActorTemplate(){
        actoren = new HashMap<>();
    }

    public HashMap<String, Object> getActoren() {
        return actoren;
    }

    public void setActoren(HashMap<String, Object> actoren) {
        this.actoren = actoren;
    }

    public boolean getIsArchived() {
        return isArchived;
    }

    public void setIsArchived(boolean archived) {
        isArchived = archived;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


}
