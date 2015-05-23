package com.mobile_di_testexample.mobile_di;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class OutputActivity extends FragmentActivity {

    private GoogleMap map; // Might be null if Google Play services APK is not available.
    private TextView mapLocation; // Used for displaying the address
    TextView name; // Will display user name
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!isGooglePlayServiceExits() )
          finish();
        setContentView(R.layout.activity_output);
        if(getActionBar() != null)
            getActionBar().hide();
        String first = getIntent().getStringExtra("first");
        String last = getIntent().getStringExtra("last");
        name = (TextView) findViewById(R.id.name);
        name.setText(first+" "+last);
        mapLocation = (TextView) findViewById(R.id.mapLocation);

        // With the supportMapFragment we get the id of map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        map = supportMapFragment.getMap();
        map.setMyLocationEnabled(true);

        // Here with the LocationManager we actually set service of getting location
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria,true);

        //here we get the current location in Location
        Location location = locationManager.getLastKnownLocation(bestProvider);
        if(location != null){
            setLocation(location);
        }else{
            Toast.makeText(this,"Location not found, Please turn On GPS and Mobile data",Toast.LENGTH_LONG).show();
        }
    }
    // Here in this method we plot user current location on Map and also getting address from Latitude and Longitude points
    public void setLocation(Location location){
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,9);
        map.moveCamera(cameraUpdate);
        map.animateCamera(cameraUpdate);
        map.addMarker(new MarkerOptions().position(latLng).title("Your Current Location"));

        // Here with the Geocoder we get address from Latitude and Longitude points
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getAddressLine(1);
            String country = addresses.get(0).getAddressLine(2);
            Log.e("address", address);
            Log.e("country", country);
            mapLocation.setText(address+"/n"+city+", "+country);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // First we check whether the GooglePlayService is installed or not, then only we proceed our process
    private boolean isGooglePlayServiceExits(){
        int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(ConnectionResult.SUCCESS == result){
            return true;
        }else{
            GooglePlayServicesUtil.getErrorDialog(result,this,0).show();
            return false;
        }
    }
}
