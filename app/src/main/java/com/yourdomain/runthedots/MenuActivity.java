package com.yourdomain.runthedots;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class MenuActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    // Makes sure that the screen does not go idle and into black screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);

        //Sets the layout to specified layout resourcefile
        setContentView(R.layout.activity_menu);
    }

    /*
    Implements the onClick action that happens when the button "Hvordan spiller du" is pressed.
    Takes you from the main menu, to the "hvordan spiller du" (How to play) menu.
    It takes the action of executing the code in the HowtoPlay class when it is pressed.
    *test*
     */

    public void HowtoPlayonClick(View view) {
        Intent intent = new Intent(this, HowtoPlay.class);
        startActivity(intent);
    }

    /*
    Implements the onClick action that happens when the button "Nyt Spil" is pressed.
    Takes you from main menu, to the riddleselections menu.
    It takes the action of executing the riddleselections class code
     */
    public void NytSpilonClick(View view) {
        Intent intent = new Intent(this, Riddleselection.class);
        startActivity(intent);
    }
}
