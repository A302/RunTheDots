package com.yourdomain.runthedots;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;


public class Mysterie1 extends FragmentActivity implements OnMapReadyCallback, LocationListener{

    final Context context = this;
    LocationManager locationManager;
    String provider;
    double lat;
    double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysterie1);
        // Makes sure that the screen does not go idle and into black screen

        /*
        Implements the mapFragment which is required to have a map appear on the screen.
        MapFragment is the simplest way to implement a map in an application. The fragment is added
        in the .xml files, where the layout is defined.
        The GoogleMaps layout is called in the getMapAsync(this), which will initialize the layout
        of google maps.
         */
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // Disables all interaction with the map that is implemented
        mapFragment.getMap().getUiSettings().setAllGesturesEnabled(true);
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        // check if enabled and if not send user to the GSP settings
// Better solution would be to display a dialog and suggesting to
// go to the settings
        if (!enabled) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("Googles placeringstjeneste er ikke slået til. Vil du gå til indstillinger og slå det til?");
            builder1.setCancelable(true);
            builder1.setPositiveButton("Ja",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                            dialog.cancel();
                        }
                    });
            builder1.setNegativeButton("Nej",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(String.valueOf(MenuActivity.class));
                            startActivity(intent);

                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);

        }
    }
    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
   @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }



    @Override
    public void onMapReady(GoogleMap map) {
       /* GPSTracker gpst = new GPSTracker(this);
        double thelatitude = gpst.getLatitude();
        double thelongitude = gpst.getLongitude();
        double thelatitude1 = gpst.getLatitude1();
        double thelongitude1 = gpst.getLongitude1();*/


        // Enables the current location as a blue dot on the map
        map.setMyLocationEnabled(true);


        /* Moves the camera to a specific location when the map is loaded as is defined by the parameters given.
        Currently, it takes the coordinate "sydney", as defined above, and gives it a zoom of 13
        13 is an arbitrary number - try changing it to see what happens at different values.
        ~20'ish is the zoom that we implemented in the physical snake game.
         */
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(57.161400, 9.735294), 13));
        map.animateCamera(CameraUpdateFactory.zoomIn());
        map.animateCamera(CameraUpdateFactory.zoomTo(20), 2000, null);

        map.addPolygon(new PolygonOptions()
                .add(new LatLng(57.16160, 9.735000))
                .add(new LatLng(57.16160, 9.735500))
                .add(new LatLng(57.16120, 9.735500))
                .add(new LatLng(57.16120, 9.735000)));

               map.addPolyline(new PolylineOptions()
                        .add(new LatLng(lat, lng), new LatLng(0, 0))
                        .width(5)
                        .color(Color.RED));

}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mysterie1, menu);
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
    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lng = location.getLongitude();

        Toast.makeText(this, "Latitude = " + lat + " and " + "Longitude =  " + lng,
                Toast.LENGTH_LONG).show();



    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
