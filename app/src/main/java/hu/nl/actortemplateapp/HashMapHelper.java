package hu.nl.actortemplateapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import hu.nl.actortemplateapp.data_classes.Actor;
import hu.nl.actortemplateapp.data_classes.ActorTemplate;
import hu.nl.actortemplateapp.data_classes.Project;

/**
 * Created by Dyon on 5-4-2017.
 */

public class HashMapHelper {

    public static ArrayList<Project> addHashMapToProjects(HashMap<String, Object> map, ArrayList<Project> curlist){
        Project temp = new Project();
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            //// TODO: 5-4-2017 Switch instead of ifs
            if(entry.getKey().equals("name")){
                temp.setName(entry.getValue().toString());
            }
            if(entry.getKey().equals("analist")){
                temp.setAnalist(entry.getValue().toString());
            }
            if(entry.getKey().equals("description")){
                temp.setDescription(entry.getValue().toString());
            }
            if(entry.getKey().equals("key")){
                temp.setKey(entry.getValue().toString());
            }
            if(entry.getKey().equals("isArchived")){
                temp.setIsArchived(Boolean.parseBoolean(entry.getValue().toString()));
            }

            //Hashmap removed because it will not be passed around, but instead retrieved in a seperate db call and is therefore useless
//            if(entry.getKey().equals("actortemplates")){
//                temp.setActortemplates((HashMap<String, Object>)entry.getValue());
//            }
        }
        if(!temp.getIsArchived()){
            curlist.add(temp);
        }
        return curlist;
    }

    public static ArrayList<ActorTemplate> addHashMapToActorTemplates(HashMap<String, Object> map, ArrayList<ActorTemplate> actorTemplates) {
        ActorTemplate temp = new ActorTemplate();
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            Log.d("Key", entry.getKey());
            if(entry.getKey().equals("key")){
                temp.setKey(entry.getValue().toString());
            }
            if(entry.getKey().equals("actor")){
                temp.setActor(entry.getValue().toString());
            }
            if(entry.getKey().equals("beschrijving")){
                temp.setBeschrijving(entry.getValue().toString());
            }
            if(entry.getKey().equals("isArchived")){
                temp.setIsArchived(Boolean.parseBoolean(entry.getValue().toString()));
            }
            if(entry.getKey().equals("actoren")) {
                //temp.setActoren((HashMap<String, Object>) entry.getValue());
            }
        }
        if(!temp.getIsArchived()) {
            actorTemplates.add(temp);
        }
        return actorTemplates;
    }

    public static ArrayList<Actor> addHashMapToActors(HashMap<String, Object> map, ArrayList<Actor> actoren) {
        Actor temp = new Actor();
        for(Map.Entry<String, Object> entry : map.entrySet()){
            if(entry.getKey().equals("key")){
                temp.setKey(entry.getValue().toString());
            }
            if(entry.getKey().equals("naam")){
                temp.setNaam(entry.getValue().toString());
            }
            if(entry.getKey().equals("email")){
                temp.setEmail(entry.getValue().toString());
            }
            if(entry.getKey().equals("telefoonnummer")){
                temp.setTelefoonnummer(entry.getValue().toString());
            }
            if(entry.getKey().equals("functie")){
                temp.setFunctie(entry.getValue().toString());
            }
            if(entry.getKey().equals("aantekeningen")){
                temp.setAantekeningen(entry.getValue().toString());
            }
        }
        actoren.add(temp);
        return actoren;
    }
}
