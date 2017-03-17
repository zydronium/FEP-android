package hu.nl.actortemplateapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import hu.nl.actortemplateapp.R;

/**
 * Created by Dyon on 5-3-2017.
 */

public class ProjectDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectdetails);
        super.onCreateDrawer();

    }
}
