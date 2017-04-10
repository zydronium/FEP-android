package hu.nl.actortemplateapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import hu.nl.actortemplateapp.R;

/**
 * Created by Dyon on 13-3-2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private GoogleApiClient mGoogleApiClient;
    private ActionBarDrawerToggle mDrawerToggle;
    private static final String TAG = "BaseActivity";
    protected DatabaseReference firebase;

    protected void onCreateDrawer(){

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent signInIntent = new Intent(this, SignInActivity.class);
            startActivity(signInIntent);
            finish();
            return;
        }

        firebase = FirebaseDatabase.getInstance().getReference();

//        try {
//            new GetImageTask().execute(new URL(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString()));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    protected boolean hasFirebaseReference(){
        return !(firebase == null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.action_sign_out){
            signOut();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_sign_out) {
            signOut();
        }

        if(id == R.id.action_home){
            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(home);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut() {
        FirebaseAuth fbAuth = FirebaseAuth.getInstance();
        FirebaseUser fbUser = fbAuth.getCurrentUser();
        if(fbUser == null){
            Toast.makeText(getApplicationContext(),"Logout failed, no firebase user logged in", Toast.LENGTH_SHORT).show();
            return;
        }
        fbAuth.signOut();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        Intent startAppIntent = new Intent(this, MainActivity.class);
        startActivity(startAppIntent);
    }

    public class GetImageTask extends AsyncTask<URL, Integer, Drawable> {
        @Override
        protected Drawable doInBackground(URL... params) {
            URLConnection connection = null;
            try {
                connection = new URL(params[0].toString()).openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStream input = null;
            try {
                input = connection.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap image = BitmapFactory.decodeStream(input);
            Drawable logo = new BitmapDrawable(getResources(), image);
            return logo;
        }
        protected void onPostExecute(Drawable result) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setOverflowIcon(result);
        }
    }
}

