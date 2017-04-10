package hu.nl.actortemplateapp.adapters;

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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import hu.nl.actortemplateapp.HashMapHelper;
import hu.nl.actortemplateapp.R;
import hu.nl.actortemplateapp.data_classes.Actor;

/**
 * Created by Dyon on 9-3-2017.
 */

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder>{

    private String projectid,
            actortemplateid;

    private static final String TAG = "ActorAdapter";
    private ArrayList<Actor> actoren = new ArrayList<Actor>();

    public ActorAdapter(DatabaseReference firebase, String projectid, String actortemplateid){
        firebase.getRoot().child("projects").child(projectid).child("actortemplates").child(actortemplateid).child("actoren").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, dataSnapshot.getValue().toString());
                HashMap<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();
                map.put("key", dataSnapshot.getKey());
                actoren = HashMapHelper.addHashMapToActors(map, actoren);
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
        View actorTemplateView = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_row, parent, false);
        return new ViewHolder(actorTemplateView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Actor actor = actoren.get(position);

        holder.naam.setText(actor.getNaam());
        holder.email.setText(actor.getEmail());
        holder.aantekeningen.setText(actor.getAantekeningen());
        holder.tel.setText(actor.getTelefoonnummer());
        holder.functie.setText(actor.getFunctie());
    }

    @Override
    public int getItemCount() {
        return actoren.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView naam, functie, email, tel, aantekeningen;

        public ViewHolder(View itemView) {
            super(itemView);
            naam = (TextView) itemView.findViewById(R.id.actornaam);
            functie = (TextView) itemView.findViewById(R.id.actorfunctie);
            email = (TextView) itemView.findViewById(R.id.actoremail);
            tel = (TextView) itemView.findViewById(R.id.actortel);
            aantekeningen = (TextView) itemView.findViewById(R.id.actoraantekeningen);
        }
    }
}
