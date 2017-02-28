package hu.nl.actortemplateapp;

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

/**
 * Created by Dyon on 27-2-2017.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //Viewholder title = name in dbase
        //Viewholder content = description in dbase

        public TextView title;
        public TextView content;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.project_title);
            content = (TextView) v.findViewById(R.id.project_content);
        }

        @Override
        public void onClick(View v) {

        }
    }
    private List<Project> projects = new ArrayList<Project>();
    private DatabaseReference mFirebaseDatabaseReference;

    public ProjectAdapter(DatabaseReference ref){
        mFirebaseDatabaseReference = ref;
        mFirebaseDatabaseReference.getRoot().child("projects").addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
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
        //Get projects from firebase and put them inside projecten.

        //mFirebaseDatabaseReference.child("Projecten");
    }

    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View projectView = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_row, parent, false);
        return new ViewHolder(projectView);
    }

    @Override
    public void onBindViewHolder(ProjectAdapter.ViewHolder holder, int position) {
        Project p = projects.get(position);
        //Viewholder title = name in dbase
        //Viewholder content = description in dbase
        holder.title.setText(p.getName());
        holder.content.setText(p.getDescription());

    }

    @Override
    public int getItemCount() {
        return projects.size();
    }
}
