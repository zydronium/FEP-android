package hu.nl.actortemplateapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import hu.nl.actortemplateapp.R;
import hu.nl.actortemplateapp.data_classes.Actor;

/**
 * Created by Dyon on 10-4-2017.
 */

public class EditActorActivity extends BaseActivity {

    private static final String TAG = "EditActor";

    private String project, actortemplate, actor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_actor);
        super.onCreateDrawer();

        project = getIntent().getStringExtra("projectid");
        actortemplate = getIntent().getStringExtra("actortemplateid");
        actor = getIntent().getStringExtra("actorid");

        firebase.getRoot().child("projects").child(project).child("actortemplates").child(actortemplate).child("actoren").child(actor).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                PopulateViews(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.saveEditedActor);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actor editedActor = new Actor();
                EditText naam = (EditText)findViewById(R.id.actorNaam);
                EditText email = (EditText)findViewById(R.id.actorEmail);
                EditText functie = (EditText)findViewById(R.id.actorFunctie);
                EditText tel = (EditText)findViewById(R.id.actorTel);
                EditText aantekeningen = (EditText)findViewById(R.id.actorAantekeningen);
                editedActor.setNaam(naam.getText().toString());
                editedActor.setEmail(email.getText().toString());
                editedActor.setFunctie(functie.getText().toString());
                editedActor.setTelefoonnummer(tel.getText().toString());
                editedActor.setAantekeningen(aantekeningen.getText().toString());
                firebase.getRoot().child("projects").child(project).child("actortemplates").child(actortemplate).child("actoren").child(actor).setValue(editedActor);
                Intent home = new Intent(v.getContext(), MainActivity.class);
                startActivity(home);
            }
        });
    }

    private void PopulateViews(DataSnapshot dataSnapshot) {
        Actor selected = dataSnapshot.getValue(Actor.class);
        EditText naam = (EditText)findViewById(R.id.actorNaam);
        EditText email = (EditText)findViewById(R.id.actorEmail);
        EditText functie = (EditText)findViewById(R.id.actorFunctie);
        EditText tel = (EditText)findViewById(R.id.actorTel);
        EditText aantekeningen = (EditText)findViewById(R.id.actorAantekeningen);
        naam.setText(selected.getNaam());
        email.setText(selected.getEmail());
        functie.setText(selected.getFunctie());
        tel.setText(selected.getTelefoonnummer());
        aantekeningen.setText(selected.getAantekeningen());

    }

}
