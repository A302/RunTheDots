package com.yourdomain.runthedots;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;


public class Mysterie1 extends FragmentActivity implements OnMapReadyCallback {

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
    }

    @Override
    public void onMapReady(GoogleMap map) {
        // Enables the current location as a blue dot on the map
        map.setMyLocationEnabled(true);

        LatLng sydney = new LatLng(-33.867, 151.206);

        /* Moves the camera to a specific location when the map is loaded as is defined by the parameters given.
        Currently, it takes the coordinate "sydney", as defined above, and gives it a zoom of 13
        13 is an arbitrary number - try changing it to see what happens at different values.
        ~20'ish is the zoom that we implemented in the physical snake game.
         */
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(57.161400,9.735294), 13));
        map.animateCamera(CameraUpdateFactory.zoomIn());
        map.animateCamera(CameraUpdateFactory.zoomTo(20), 2000, null);

        map.addPolygon(new PolygonOptions()
                .add(new LatLng(57.161790, 9.734060))
                .add(new LatLng(57.161090, 9.733900))
                .add(new LatLng(57.160840, 9.736400))
                .add(new LatLng(57.161560, 9.736610)));

        /*
        Adds a marker at the location of the "sydney" coordinate, gives it a title and a secondary
        information.
        The title and snippet are not useful to our program, as it only appears when a marker is
        pressed on the map, which we disable completely, therefore rendering the information given useless
        */
        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
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
