package com.yourdomain.runthedots;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class Mysterie1intro extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set window fullscreen and remove title bar
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        // Set layout of the activity to specified layout resource file
        setContentView(R.layout.activity_mysterie1intro);

        // Ensures that the application is kept awake at all times - app can't go into idle mode
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // Set window fullscreen and remove title bar
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mysterie1intro, menu);
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

    //The code below makes buttons change from one interface to another
    public void Startgame1(View view) {
        Intent intent = new Intent (this,Mysterie1.class);
        startActivity(intent);
    }

    /*public void TilabgetilMysteriemenu(View view) {
        Intent intent = new Intent(this,Riddleselection.class);
        startActivity(intent);
    }
*/

}

