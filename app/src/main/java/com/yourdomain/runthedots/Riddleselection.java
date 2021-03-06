package com.yourdomain.runthedots;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Riddleselection extends ActionBarActivity {

    Context context = this;
    private ImageButton Imagebutton3;
    private ImageButton Imagebutton4;
    private ImageButton Imagebutton5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddleselection);
        // Makes sure that the screen does not go idle and into black screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Imagebutton3 = (ImageButton) findViewById(R.id.Mysterie3Button);


        // add button listener
        Imagebutton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.costumize_dialog);

                // set title

                dialog.setTitle(getString(R.string.mystery_locked_popup_title));


                // set dialog message

                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText(R.string.mystery_locked_popup);
                ImageButton dialogButton = (ImageButton) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        Imagebutton4 = (ImageButton) findViewById(R.id.Mysterie4Button);


        // add button listener
        Imagebutton4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.costumize_dialog);

                // set title

                dialog.setTitle(getString(R.string.mystery_locked_popup_title));


                // set dialog message

                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText(R.string.mystery_locked_popup);
                ImageButton dialogButton = (ImageButton) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        Imagebutton5 = (ImageButton) findViewById(R.id.Mysterie5Button);


        // add button listener
        Imagebutton5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.costumize_dialog);

                // set title

                dialog.setTitle(getString(R.string.mystery_locked_popup_title));


                // set dialog message

                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText(R.string.mystery_locked_popup);
                ImageButton dialogButton = (ImageButton) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }

    /* Implements the onClick action that takes you from the riddleselection screen to mystery one screen
 */

    public void Mysterie2TilMission(View view){
        Intent intent = new Intent(this, Mysterie2intro.class);
        startActivity(intent);
    }
// Implements the onClick action that takes you from the riddleselection menu, BACK to the main menu
    public void Mysterie1toMission(View view) {
        Intent intent = new Intent (this,Mysterie1intro.class);
        startActivity(intent);
    }
}
