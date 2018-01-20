package hoangloc.parkinglotfinder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.support.v4.app.FragmentTransaction;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.content.res.Configuration;
import java.util.Locale;

public class ActivityMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        OnMapReadyCallback,

        GoogleApiClient.ConnectionCallbacks,

        GoogleApiClient.OnConnectionFailedListener,

        LocationListener {



    public GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
    public FloatingActionButton fab;
    LatLng latLng;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    static SharedPreferences sharedPref;
    static SharedPreferences.Editor prefEditor;
    static int currentLang;
    static int currentTheme;
    int themeColor;
    int headerImage;
    private long interval ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SupportMapFragment mapFragment = new com.google.android.gms.maps.SupportMapFragment();
        ft.replace(R.id.place_holder_MainActivity, mapFragment);
        ft.commit();
        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()

                .findFragmentById(R.id.map);*/

        mapFragment.getMapAsync(this);

        try {

            if (ActivityCompat.checkSelfPermission(this, mPermission)

                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},

                        REQUEST_CODE_PERMISSION);



                // If any permission above not allowed by user, this condition will

                //execute every time, else your else part will work

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        FloatingActionButton fabLoc = (FloatingActionButton) findViewById(R.id.buttonGetLocation);

        fabLoc.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                try {

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                }

                catch (Exception e){

                    Snackbar.make(fab,"LOCATION NOT FOUND\nPlease turn on Location Services",Snackbar.LENGTH_SHORT).show();

                }

            }

        });


        sharedPref = getSharedPreferences(getString(R.string.preperences_file),this.MODE_PRIVATE);
        prefEditor = sharedPref.edit();
        setLanguage();
        setTheme();

        //themeColor = sharedPref.getInt("currentColor",R.color.colorPrimary);
        //headerImage = sharedPref.getInt("currentHeader",R.drawable.header);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //ft.replace(R.id.place_holder_MainActivity, new FragmentHome());
        //ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setLanguage(){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        currentLang = sharedPref.getInt("currentLang", 0);
        if (currentLang == 0)
            conf.setLocale(new Locale("en"));
        else if (currentLang == 1)
            conf.setLocale(new Locale("vi"));
        res.updateConfiguration(conf, dm);
    }

    public void setTheme(){

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
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent myIntent = new Intent(getApplicationContext(), ActivityMain.class);
            //myIntent.putExtra("key", "haha"); //Optional parameters
            startActivity(myIntent);

        } else if (id == R.id.nav_setting) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.place_holder_MainActivity, new FragmentSetting());
            ft.commit();
        } else if (id == R.id.nav_about) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.place_holder_MainActivity, new FragmentAbout());
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();

        locationRequest.setInterval(interval);

        locationRequest.setFastestInterval(100);

        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);



        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();

        double longitude = location.getLongitude();

        latLng = new LatLng(latitude,longitude);
    }
    private void buildGoogleApiClient() {

        client = new GoogleApiClient.Builder(this)

                .addConnectionCallbacks(this)

                .addOnConnectionFailedListener(this)

                .addApi(LocationServices.API)

                .build();



        client.connect();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            buildGoogleApiClient();

            mMap.setMyLocationEnabled(true); // cai nay de cho phep lay location, dat trong vong if de coi coi may da duoc cho quen su dung GPS hay chua

        }


    }
}
