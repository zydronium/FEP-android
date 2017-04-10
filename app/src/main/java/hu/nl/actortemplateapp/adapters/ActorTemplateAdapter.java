package hu.nl.actortemplateapp.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import hu.nl.actortemplateapp.HashMapHelper;
import hu.nl.actortemplateapp.R;
import hu.nl.actortemplateapp.activities.TemplateWithActorsActivity;
import hu.nl.actortemplateapp.data_classes.ActorTemplate;

/**
 * Created by Dyon on 9-3-2017.
 */

public class ActorTemplateAdapter extends RecyclerView.Adapter<ActorTemplateAdapter.ViewHolder> {

    private ArrayList<ActorTemplate> actorTemplates = new ArrayList<>();
    private HashMap<String, Object> map;
    private static final String TAG = "ActorTemplateAdapter";
    private String projectid;

    public ActorTemplateAdapter(DatabaseReference db, String projectid) {
        this.projectid = projectid;
        db.getRoot()
                .child("projects")
                .child(projectid)
                .child("actortemplates")
                .addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        //Get projects from firebase and put them inside arraylist.
                        map = (HashMap<String, Object>)dataSnapshot.getValue();
                        map.put("key", dataSnapshot.getKey());
                        actorTemplates = HashMapHelper.addHashMapToActorTemplates(map, actorTemplates);
                        notifyDataSetChanged();
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


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View actorTemplateView = LayoutInflater.from(parent.getContext()).inflate(R.layout.roles_row, parent, false);
        return new ViewHolder(actorTemplateView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ActorTemplate at = actorTemplates.get(position);
        if(position % 2 == 0){
            holder.cardView.setCardBackgroundColor(Color.parseColor("#42A5F5"));
        }
        else{
            holder.cardView.setCardBackgroundColor(Color.parseColor("#80CBC4"));
        }
        holder.bindViews(at);
    }

    @Override
    public int getItemCount() {
        return actorTemplates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView actor;
        public TextView beschrijving;
        public TextView actortemplateid;
        public RecyclerView personen;
        public CardView cardView;

        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            beschrijving = (TextView) v.findViewById(R.id.actorbeschrijving);
            actor = (TextView) v.findViewById(R.id.actortitle);
            personen = (RecyclerView) v.findViewById(R.id.innerRecyclerView);
            actortemplateid = (TextView) v.findViewById(R.id.actortemplate_id);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent actortemplatedetails = new Intent(v.getContext(), TemplateWithActorsActivity.class);
                    actortemplatedetails.putExtra("projectid", projectid);
                    actortemplatedetails.putExtra("actortemplateid", actortemplateid.getText().toString());
                    v.getContext().startActivity(actortemplatedetails);
                }
            });
        }

        public void bindViews(ActorTemplate at){
            //Set card values
            beschrijving.setText(at.getBeschrijving());
            actor.setText(at.getActor());
            actortemplateid.setText(at.getKey());
            //Config InnerRecyclerView
            LinearLayoutManager layoutManager = new LinearLayoutManager(personen.getContext(), LinearLayoutManager.HORIZONTAL, false);
            InnerRecyclerViewAdapter adapter = new InnerRecyclerViewAdapter(FirebaseDatabase.getInstance().getReference(), projectid, actortemplateid.getText().toString());
            personen.setLayoutManager(layoutManager);
            personen.setAdapter(adapter);
        }
    }



}