package hu.nl.actortemplateapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import hu.nl.actortemplateapp.R;

/**
 * Created by Dyon on 31-5-2017.
 */

public class NewProjectActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_project);
        super.onCreateDrawer();
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
