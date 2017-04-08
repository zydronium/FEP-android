package hu.nl.actortemplateapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import hu.nl.actortemplateapp.R;
import hu.nl.actortemplateapp.data_classes.ActorTemplate;

/**
 * Created by Dyon on 4-4-2017.
 */

public class NewActorTemplateActivity extends BaseActivity {

    private EditText templatetitel;
    private EditText templatebeschrijving;
    private String key;
    private FloatingActionButton addactortemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_actor_template);
        super.onCreateDrawer();
        templatetitel = (EditText) findViewById(R.id.actorTemplateTitle);
        templatebeschrijving = (EditText) findViewById(R.id.actorTemplateBeschrijving);
        addactortemplate = (FloatingActionButton) findViewById(R.id.addactortemplatebutton);
        key = getIntent().getStringExtra("key");
        setFirebaseListener(key);

        addactortemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateNewActorTemplate(v)){
                    ActorTemplate newActorTemplate = new ActorTemplate();
                    newActorTemplate.setIsArchived(false);
                    newActorTemplate.setActor(templatetitel.getText().toString());
                    newActorTemplate.setBeschrijving(templatebeschrijving.getText().toString());
                    firebase.getRoot().child("projects").child(key).child("actortemplates").push().setValue(newActorTemplate);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Titel of beschrijving niet valide", Toast.LENGTH_LONG);
                }
            }
        });
    }

    private boolean validateNewActorTemplate(View v){
        boolean output = false;
        if(templatetitel.getText().toString() != "" && templatebeschrijving.getText().toString() != ""){
            output = true;
        }
        return output;
    }

    private void setFirebaseListener(String key) {
        firebase.getRoot().child("projects").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        return super.onNavigationItemSelected(item);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
