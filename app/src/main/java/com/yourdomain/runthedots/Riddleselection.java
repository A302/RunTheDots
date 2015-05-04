package com.yourdomain.runthedots;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class Riddleselection extends ActionBarActivity {
    final Context context = this;
    private ImageButton Imagebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riddleselection);
        // Makes sure that the screen does not go idle and into black screen
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Imagebutton = (ImageButton) findViewById(R.id.Mysterie2Button);

        // add button listener
        Imagebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.costumize_dialog);

                // set title

                dialog.setTitle("Dette mysterie er låst!");




                // set dialog message

                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Gennemfør Mysterie 1 for at åbne op for dette mysterie");
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_riddleselection, menu);
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

    /* Implements the onClick action that takes you from the riddleselection screen to mystery one screen

     */

    public void Mysterie1toMission(View view) {
        Intent intent = new Intent (this,Mysterie1intro.class);
        startActivity(intent);
    }

}
