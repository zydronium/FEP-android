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

import java.util.ArrayList;
import java.util.HashMap;

import hu.nl.actortemplateapp.R;
import hu.nl.actortemplateapp.data_classes.Actor;

/**
 * Created by Dyon on 27-3-2017.
 */

public class InnerRecyclerViewAdapter extends RecyclerView.Adapter<InnerRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Actor> actoren;
    private final static String TAG = "InnerRecycler";

    public InnerRecyclerViewAdapter(DatabaseReference db, String projectid, String atid)
    {
        actoren = new ArrayList<Actor>();
        Log.d(TAG, "CTOR");
        db.getRoot().child("projects").child(projectid).child("actortemplates").child(atid).child("actoren").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Actor actor = dataSnapshot.getValue(Actor.class);
                actor.setKey(dataSnapshot.getKey());
                actoren.add(actor);
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
        View actorTemplateView = LayoutInflater.from(parent.getContext()).inflate(R.layout.actortemplate_users_row, parent, false);
        return new ViewHolder(actorTemplateView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.personname.setText(actoren.get(position).getNaam());
    }

    @Override
    public int getItemCount() {
        return actoren.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView personname;

        public ViewHolder(View v) {
            super(v);
            personname = (TextView)v.findViewById(R.id.personname);
        }
    }
}
