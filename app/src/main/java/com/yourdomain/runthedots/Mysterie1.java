package com.yourdomain.runthedots;

import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;


public class Mysterie1 extends FragmentActivity implements OnMapReadyCallback, com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    GoogleMap map;

    protected GoogleApiClient mGoogleApiClient;
    protected TextView mLatitudeText;
    protected TextView mLongitudeText;
    protected Location mCurrentLocation;
    private LocationRequest mLocationRequest;
    protected TextView mLatitudeTextView;
    protected TextView mLongitudeTextView;
    protected TextView mLastUpdateTimeTextView;
    public static final String TAG = Mysterie1.class.getSimpleName();
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    double currentLatitude;
    double currentLongitude;
    LatLng latLng = new LatLng(currentLatitude, currentLongitude);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysterie1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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


       /* mLatitudeText = (TextView) findViewById(R.id.textView1);
        mLongitudeText = (TextView) findViewById(R.id.textView2);
        mLatitudeTextView = (TextView) findViewById(R.id.textView3);
        mLongitudeTextView = (TextView) findViewById(R.id.textView4);
        mLastUpdateTimeTextView = (TextView) findViewById(R.id.textView5);*/

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
    }


    @Override
    public void onResume() {
        super.onResume();
        mGoogleApiClient.connect();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        // Enables the current location as a blue dot on the map
        map.setMyLocationEnabled(true);

        /* Moves the camera to a specific location when the map is loaded as is defined by the parameters given.
        Currently, it takes the coordinate "sydney", as defined above, and gives it a zoom of 13
        13 is an arbitrary number - try changing it to see what happens at different values.
        ~20'ish is the zoom that we implemented in the physical snake game.
         */
        mGoogleApiClient.connect();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(57.161400, 9.735294), 13));
        map.animateCamera(CameraUpdateFactory.zoomIn());
        map.animateCamera(CameraUpdateFactory.zoomTo(20), 2000, null);

        map.addPolygon(new PolygonOptions()
                .add(new LatLng(57.16160, 9.735000))
                .add(new LatLng(57.16160, 9.735500))
                .add(new LatLng(57.16120, 9.735500))
                .add(new LatLng(57.16120, 9.735000)));



        /*LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, true);
        Location location = service.getLastKnownLocation(provider);
        userLocation = new LatLng (location.getLatitude(),location.getLongitude());
        Marker mMarker = map.addMarker((new MarkerOptions().position(userLocation)));*/

    }
    /*private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            Marker mMarker = map.addMarker(new MarkerOptions().position(loc));
            if(map != null){
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
            }
        }
    };*/

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
    public void onConnected(Bundle connectionHint) {
        Log.i(TAG, "Location services connected.");
        mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mCurrentLocation == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } else {
            handleNewLocation(mCurrentLocation);
        }
    }

    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();

        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("I am here!");
        map.addMarker(options);

    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(mCurrentLocation);

    }


}