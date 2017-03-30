package hu.nl.actortemplateapp.data_classes;

import java.util.ArrayList;

/**
 * Created by Dyon on 5-3-2017.
 */

public class ActorTemplate {
    public String actor;
    public String beschrijving;
    public String key;
    public boolean isArchived;
    public ArrayList<Actor> actoren;

    public ActorTemplate(){
        actoren = new ArrayList<Actor>();
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

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public ArrayList<Actor> getActoren() {
        return actoren;
    }

    public void setActoren(ArrayList<Actor> actoren) {
        this.actoren = actoren;
    }

}
