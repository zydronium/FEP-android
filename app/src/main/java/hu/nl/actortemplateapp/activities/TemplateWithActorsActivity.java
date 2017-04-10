package hu.nl.actortemplateapp.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import hu.nl.actortemplateapp.R;
import hu.nl.actortemplateapp.adapters.ActorAdapter;
import hu.nl.actortemplateapp.adapters.ActorTemplateAdapter;

/**
 * Created by Dyon on 9-4-2017.
 */

public class TemplateWithActorsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_with_actors);
        super.onCreateDrawer();

        RecyclerView recycler = (RecyclerView) findViewById(R.id.actorsRecycler);

        String project = getIntent().getStringExtra("projectid");
        String actortemplate = getIntent().getStringExtra("actortemplateid");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new ActorAdapter(firebase, project, actortemplate);
        recycler.setAdapter(adapter);

        firebase.getRoot().child("projects").child(project).child("actortemplates").child(actortemplate).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PopulateViews(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void PopulateViews(DataSnapshot dataSnapshot) {
        String actor = dataSnapshot.child("actor").getValue().toString();
        String beschrijving = dataSnapshot.child("beschrijving").getValue().toString();
        TextView actorview = (TextView)findViewById(R.id.actortemplateTitle);
        TextView beschrijvingview = (TextView)findViewById(R.id.actorTemplateBeschrijving);
        actorview.setText(actor);
        beschrijvingview.setText(beschrijving);
    }
}
