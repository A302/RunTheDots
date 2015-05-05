package com.yourdomain.runthedots;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class Mysterie1 extends FragmentActivity implements OnMapReadyCallback {

    double flati;
    double flongi;
    private CameraPosition cameraPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysterie1);
        // Makes sure that the screen does not go idle and into black screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
    }

    /* Constructs a CameraPosition which sets the camera to the position of the schoolyard.
    Secondly, it changes the rotation of the map in order to center the playing field.
     */
    {
        cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(57.162238, 9.734942))
                .zoom(19.5f)
                .tilt(0)
                .bearing(9)
                .build();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        // Enables the current location as a blue dot on the map
        map.setMyLocationEnabled(true);

        //Sets the map type to satelite maps
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        /* Moves the camera to a specific location when the map is loaded as is defined by the parameters given.
        Currently, it takes the coordinate "sydney", as defined above, and gives it a zoom of 13
        13 is an arbitrary number - try changing it to see what happens at different values.
        ~20'ish is the zoom that we implemented in the physical snake game.
         */
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(57.162238, 9.734942), 13));
        map.animateCamera(CameraUpdateFactory.zoomIn());
        map.animateCamera(CameraUpdateFactory.zoomTo(20), 2000, null);
        //Rotates the map 9 degrees by calling the cameraPosition method
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        // Adds the playing field by setting the four vertix points of the a rectangle
        map.addPolygon(new PolygonOptions()
                .add(new LatLng(57.162437, 9.734732))
                .add(new LatLng(57.162055, 9.734620))
                .add(new LatLng(57.162010, 9.735159))
                .add(new LatLng(57.162392, 9.735265)));

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            flati = location.getLatitude();
            flongi = location.getLongitude();
            Toast.makeText(getApplicationContext(), "hello" + flati + " " + flongi + " " + " +lati+longi",
                    Toast.LENGTH_LONG).show();
        }
        Polyline line = map.addPolyline(new PolylineOptions()
                .add(new LatLng(flati, flongi), new LatLng(40.7, -74.0))
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

}
