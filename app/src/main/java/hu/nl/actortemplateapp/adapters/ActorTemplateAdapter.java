package hu.nl.actortemplateapp.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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

    private List<Project> projects = new ArrayList<Project>();
    private DatabaseReference mFirebaseDatabaseReference;

    public ActorTemplateAdapter(DatabaseReference db){
        mFirebaseDatabaseReference = db;
        mFirebaseDatabaseReference.getRoot().child("projects").addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Get projects from firebase and put them inside arraylist.
                Project p = dataSnapshot.getValue(Project.class);
                p.setKey(dataSnapshot.getKey());
                projects.add(p);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Viewholder title = name in dbase
        //Viewholder content = description in dbase
        public TextView title;
        public TextView content;
        public CardView cardView;
        public TextView id;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.project_title);
            content = (TextView) v.findViewById(R.id.project_content);
            id = (TextView) v.findViewById(R.id.project_id);
            cardView = (CardView) v.findViewById(R.id.card_view);
            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent projectDetailsActivity = new Intent(v.getContext(), ProjectDetailsActivity.class);
                    projectDetailsActivity.putExtra("projectid", id.getText());
                    v.getContext().startActivity(projectDetailsActivity);
                }
            });
        }


    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View projectView = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_row, parent, false);
        return new ViewHolder(projectView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return projects.size();
    }

}
