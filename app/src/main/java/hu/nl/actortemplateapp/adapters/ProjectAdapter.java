package hu.nl.actortemplateapp.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.nl.actortemplateapp.R;
import hu.nl.actortemplateapp.activities.ActorTemplateActivity;
import hu.nl.actortemplateapp.data_classes.Project;

/**
 * Created by Dyon on 27-2-2017.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {


    private ArrayList<Project> projects = new ArrayList<Project>();
    private DatabaseReference mFirebaseDatabaseReference;

    private static final String TAG = "ProjectAdapter";
    public ProjectAdapter(DatabaseReference ref) {
        mFirebaseDatabaseReference = ref;
        mFirebaseDatabaseReference.getRoot().child("projects").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Get projects from firebase and put them inside arraylist.;
                HashMap<String, Object> map = (HashMap<String, Object>)dataSnapshot.getValue();
                map.put("key", dataSnapshot.getKey());
                projects = HashMapHelper.addHashMapToProjects(map, projects);
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
        public FloatingActionButton fab;

        public ViewHolder(View v) {
            super(v);
            fab = (FloatingActionButton) v.findViewById(R.id.fab);
            title = (TextView) v.findViewById(R.id.project_title);
            content = (TextView) v.findViewById(R.id.project_content);
            id = (TextView) v.findViewById(R.id.project_id);
            cardView = (CardView) v.findViewById(R.id.card_view);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent projectDetailsActivity = new Intent(v.getContext(), ActorTemplateActivity.class);
                    projectDetailsActivity.putExtra("projectid", id.getText());
                    v.getContext().startActivity(projectDetailsActivity);
                }
            });
        }
    }

    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View projectView = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_row, parent, false);
        return new ViewHolder(projectView);
    }


    @Override
    public void onBindViewHolder(ProjectAdapter.ViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.title.setText(project.getName());
        holder.content.setText(project.getDescription());
        holder.id.setText(project.getKey());
        holder.title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        if ((position % 2) == 0) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#42A5F5"));
        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#80CBC4"));
        }
        //Hide the FloatingActionButton when currentuser != analist.
        if (!(FirebaseAuth.getInstance().getCurrentUser().getEmail().toLowerCase().equals(project.analist.toLowerCase()))) {
            holder.fab.hide();
        }

    }

    @Override
    public int getItemCount() {
        return projects.size();
    }
}
