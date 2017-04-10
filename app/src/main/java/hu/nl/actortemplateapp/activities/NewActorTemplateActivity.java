package hu.nl.actortemplateapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import hu.nl.actortemplateapp.R;
import hu.nl.actortemplateapp.data_classes.ActorTemplate;

/**
 * Created by Dyon on 4-4-2017.
 */

public class NewActorTemplateActivity extends BaseActivity {

    private EditText templatetitel;
    private EditText templatebeschrijving;
    private TextView titel;
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
        titel = (TextView) findViewById(R.id.addActorTemplateText);
        titel.setText("Hier kunt U een nieuwe ActorTemplate creëren door de velden in te vullen en de opslag knop te selecteren. De velden dienen allebei een waarde te bevatten");

        key = getIntent().getStringExtra("key");

        addactortemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateNewActorTemplate(v)){
                    ActorTemplate newActorTemplate = new ActorTemplate();
                    newActorTemplate.setIsArchived(false);
                    newActorTemplate.setActor(templatetitel.getText().toString());
                    newActorTemplate.setBeschrijving(templatebeschrijving.getText().toString());
                    firebase.getRoot().child("projects").child(key).child("actortemplates").push().setValue(newActorTemplate);
                    Intent returntoprojectdetailsactivity = new Intent(getBaseContext(), ActorTemplateActivity.class);
                    returntoprojectdetailsactivity.putExtra("projectid", key);
                    startActivity(returntoprojectdetailsactivity);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Titel of beschrijving niet valide", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private boolean validateNewActorTemplate(View v){
        boolean output = false;
        if(!(templatetitel.getText().toString().equals("")) && !(templatebeschrijving.getText().toString().equals(""))){
            output = true;
        }
        return output;
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
