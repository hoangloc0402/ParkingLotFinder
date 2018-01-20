package hoangloc.parkinglotfinder;

import android.Manifest;

import android.content.Context;

import android.content.Intent;

import android.content.SharedPreferences;

import android.content.pm.PackageManager;

import android.graphics.Bitmap;

import android.graphics.Point;

import android.location.Address;

import android.location.Geocoder;

import android.location.Location;



import android.os.Build;

import android.os.Handler;

import android.provider.Settings;

import android.support.annotation.NonNull;

import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;

import android.support.design.widget.NavigationView;

import android.support.design.widget.Snackbar;

import android.support.v4.app.ActivityCompat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;



import android.support.v4.content.ContextCompat;

import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;

import android.telephony.TelephonyManager;

import android.view.Display;

import android.view.LayoutInflater;
import android.view.MenuItem;

import android.view.View;

import android.view.ViewGroup;
import android.widget.*;

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

import java.io.IOException;

import java.lang.reflect.Array;

import java.util.*;



public class FragmentHome extends Fragment implements

        OnMapReadyCallback,

        GoogleApiClient.ConnectionCallbacks,

        GoogleApiClient.OnConnectionFailedListener,

        LocationListener {



    public GoogleMap mMap;

    private GoogleApiClient client;

    private LocationRequest locationRequest;

    public HashMap<String,Marker> currentMarker = new HashMap<>();

    //public FloatingActionButton fab;

    LatLng latLng;

    private static final int REQUEST_CODE_PERMISSION = 2;

    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    private long interval ;

    public FragmentHome() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting,null);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =

                (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.fragment_map);

        mapFragment.getMapAsync(this);
        try {

            if (ActivityCompat.checkSelfPermission(getContext(), mPermission)

                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{mPermission},REQUEST_CODE_PERMISSION);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    @Override

    public void onMapReady(GoogleMap googleMap) { // cai nay duoc goi moi khi map san sang de duoc su dung

        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);



        if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            buildGoogleApiClient();

            mMap.setMyLocationEnabled(true); // cai nay de cho phep lay location, dat trong vong if de coi coi may da duoc cho quen su dung GPS hay chua

        }



    }





    private void buildGoogleApiClient() {

        client = new GoogleApiClient.Builder(getContext())

                .addConnectionCallbacks(this)

                .addOnConnectionFailedListener(this)

                .addApi(LocationServices.API)

                .build();



        client.connect();

    }



    @Override

    public void onLocationChanged(Location location) {

        double latitude = location.getLatitude();

        double longitude = location.getLongitude();

        latLng = new LatLng(latitude,longitude);



    }



    @Override

    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();

        locationRequest.setInterval(interval);

        locationRequest.setFastestInterval(100);

        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);



        if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);

        }

    }



    @Override

    public void onConnectionSuspended(int i) {

    }



    @Override

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}