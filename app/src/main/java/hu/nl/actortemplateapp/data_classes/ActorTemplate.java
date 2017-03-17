package hu.nl.actortemplateapp.data_classes;

/**
 * Created by Dyon on 5-3-2017.
 */

public class ActorTemplate {
    public String actorName;
    public String actorDescription;
    public String key;
    public boolean isArchived;


    public ActorTemplate(){

    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getActorDescription() {
        return actorDescription;
    }

    public void setActorDescription(String actorDescription) {
        this.actorDescription = actorDescription;
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

}
