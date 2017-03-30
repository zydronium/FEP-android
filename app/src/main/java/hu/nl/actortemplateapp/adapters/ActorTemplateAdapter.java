package hu.nl.actortemplateapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import hu.nl.actortemplateapp.R;
import hu.nl.actortemplateapp.activities.ProjectDetailsActivity;
import hu.nl.actortemplateapp.data_classes.ActorTemplate;
import hu.nl.actortemplateapp.data_classes.Project;

/**
 * Created by Dyon on 9-3-2017.
 */

public class ActorTemplateAdapter extends RecyclerView.Adapter<ActorTemplateAdapter.ViewHolder> {

    private List<ActorTemplate> actorTemplates = new ArrayList<>();
    private static final String TAG = "ActorTemplateAdapter";

    public ActorTemplateAdapter(DatabaseReference db, String projectid) {
        db.getRoot()
                .child("projects")
                .child(projectid)
                .child("actortemplates")
                .addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        //Get projects from firebase and put them inside arraylist.
                        ActorTemplate at = dataSnapshot.getValue(ActorTemplate.class);
                        if(at.getActor() != null) {
                            at.setKey(dataSnapshot.getKey());
                            actorTemplates.add(at);
                            Log.d(TAG, dataSnapshot.getKey());
                            notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        //setupDatabaseListeners(db, projectid);
    }

    private void setupDatabaseListeners(DatabaseReference db, String projectid) {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View actorTemplateView = LayoutInflater.from(parent.getContext()).inflate(R.layout.roles_row, parent, false);
        return new ViewHolder(actorTemplateView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ActorTemplate at = actorTemplates.get(position);
        holder.bindViews(at);
    }

    @Override
    public int getItemCount() {
        return actorTemplates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView actor;
        public TextView beschrijving;
        public RecyclerView personen;

        public ViewHolder(View v) {
            super(v);
            beschrijving = (TextView) v.findViewById(R.id.actorbeschrijving);
            actor = (TextView) v.findViewById(R.id.actortitle);
            personen = (RecyclerView) v.findViewById(R.id.innerRecyclerView);

//                public void onClick(View v) {
//                    Intent projectDetailsActivity = new Intent(v.getContext(), ProjectDetailsActivity.class);
//                    projectDetailsActivity.putExtra("projectid", id.getText());
//                    v.getContext().startActivity(projectDetailsActivity);
//                }
        }

        public void bindViews(ActorTemplate at){
            //Set card values
            beschrijving.setText(at.getBeschrijving());
            actor.setText(at.getActor());
            //Config InnerRecyclerView
            ActorTemplate temp = at;
            LinearLayoutManager layoutManager = new LinearLayoutManager(personen.getContext(), LinearLayoutManager.HORIZONTAL, false);
            personen.setLayoutManager(layoutManager);
            InnerRecyclerViewAdapter adapter = new InnerRecyclerViewAdapter(at.getActoren());
            personen.setAdapter(adapter);
        }


    }



}