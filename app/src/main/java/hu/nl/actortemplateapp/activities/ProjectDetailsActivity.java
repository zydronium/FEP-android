package hu.nl.actortemplateapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.firebase.database.FirebaseDatabase;

import hu.nl.actortemplateapp.R;
import hu.nl.actortemplateapp.adapters.ActorTemplateAdapter;

/**
 * Created by Dyon on 5-3-2017.
 */

public class ProjectDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectdetails);
        super.onCreateDrawer();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.actorTemplates);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        String project = getIntent().getStringExtra("projectid");
        ActorTemplateAdapter actorTemplateAdapter = new ActorTemplateAdapter(super.firebase, project);
        recyclerView.setAdapter(actorTemplateAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        return super.onNavigationItemSelected(item);
    }

}
