package de.fh.mae.md2.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import de.fh.mae.md2.app.activities.AddTransactionActivity;
import de.fh.mae.md2.app.activities.CalendarActivity;
import de.fh.mae.md2.app.activities.CategoryOverviewActivity;
import de.fh.mae.md2.app.activities.ChartActivity;
import de.fh.mae.md2.app.activities.IncomeActivity;
import de.fh.mae.md2.app.activities.OutcomeActivity;
import de.fh.mae.md2.app.activities.OverviewActivity;
import de.fh.mae.md2.app.activities.SignInActivity;
import de.fh.mae.md2.app.activities.UnlockActivity;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth = FirebaseAuth.getInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setNavigationDrawerHeader(navigationView);

        displaySelectedScreen(R.id.nav_overview);
    }

    private void setNavigationDrawerHeader(NavigationView navigationView) {
        View navigationHeader = navigationView.getHeaderView(0);
        /*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        TextView headerName = (TextView) navigationHeader.findViewById(R.id.nav_header_name);
        TextView headerEmail = (TextView) navigationHeader.findViewById(R.id.nav_header_email);
        ImageView headerImage = (ImageView) navigationHeader.findViewById(R.id.nav_header_image);

        SharedPreferences sharedPreferences = getSharedPreferences("accountInfo", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", null);
        String email = sharedPreferences.getString("email", null);
        String imageuUrl = sharedPreferences.getString("imageUrl", null);

        Picasso.get()
                .load(imageuUrl)
                .placeholder(R.drawable.ic_default_user)
                .error(R.drawable.ic_default_user)
                .into(headerImage);

        headerName.setText(name);
        headerEmail.setText(email);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            signOut();

            finish();
            Intent myIntent = new Intent(Main.this, SignInActivity.class);
            startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // sign-out
    public void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //updateUI(null);
                    }
                });

        MyPayments.signOut();
    }

    // disconnect
    public void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //updateUI(null);
                    }
                });
    }

    private void displaySelectedScreen(int id) {
        Fragment fragment = null;

        switch (id) {
            case R.id.nav_overview:
                fragment = new OverviewActivity();
                break;
            case R.id.nav_income:
                fragment = new IncomeActivity();
                break;
            case R.id.nav_outcome:
                fragment = new OutcomeActivity();
                break;
            case R.id.nav_calender:
                fragment = new CalendarActivity();
                break;
            case R.id.nav_charts:
                fragment = new ChartActivity();
                break;
            case R.id.nav_categories:
                Intent myIntent = new Intent(this, CategoryOverviewActivity.class);
                startActivity(myIntent);
                break;
            case R.id.nav_account:
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_help:
                break;
            case R.id.nav_imprint:
                break;

        }

        if(fragment != null) {
            hideFloatingButton();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.main_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void hideFloatingButton() {
        FloatingActionButton buttonFloatingMain = (FloatingActionButton) findViewById(R.id.button__floating_main);
        if(buttonFloatingMain != null) {
            buttonFloatingMain.setVisibility(View.GONE);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        displaySelectedScreen(item.getItemId());
        return true;
    }

}
