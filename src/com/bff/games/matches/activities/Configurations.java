package com.bff.games.matches.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import com.bff.games.matches.R;
import com.bff.games.matches.TheGame;

/**
 * Created with IntelliJ IDEA.
 * User: dooter
 * Date: 7/16/13
 * Time: 9:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class Configurations extends Activity {


    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.configurations);
        final ImageView submit = (ImageView) findViewById(R.id.submit);
        final Spinner level = (Spinner) findViewById(R.id.levelofplay);
        final Spinner pairs = (Spinner) findViewById(R.id.numberofpairs);
        final TheGame theGame = TheGame.getInstance();
        if (theGame.getGoToPairs() != 0) {
            String prevVal = Integer.toString(theGame.getGoToPairs()); //the value you want the position for
            ArrayAdapter myAdap = (ArrayAdapter) pairs.getAdapter(); //cast to an ArrayAdapter
            int spinnerPosition = myAdap.getPosition(prevVal);
            pairs.setSelection(spinnerPosition);
        }
        if (theGame.isBeginner()) {
            String myString = "Beginner"; //the value you want the position for
            ArrayAdapter myAdap2 = (ArrayAdapter) level.getAdapter(); //cast to an ArrayAdapter
            int spinnerLevelPosition = myAdap2.getPosition(myString);
            level.setSelection(spinnerLevelPosition);
        } else if (theGame.isAdvanced()) {
            String myString = "Advanced"; //the value you want the position for
            ArrayAdapter myAdap2 = (ArrayAdapter) level.getAdapter(); //cast to an ArrayAdapter
            int spinnerLevelPosition = myAdap2.getPosition(myString);
            level.setSelection(spinnerLevelPosition);
        }

        pairs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                final Object item = parent.getItemAtPosition(pos);
                if (item != null) {
                    Log.i("Configuration.java", "The item selected is: " + item);

                    if (item.toString().charAt(0) == '2') {
                        if (theGame.getnMaxRows() >= 2 && theGame.getnMaxCols() >= 2) {
                            theGame.setNCols(2);
                            theGame.setNRows(2);
                            theGame.setGoToPairs(2);
                        } else {
                            // do nothing
                        }

                    } else if (item.toString().charAt(0) == '4') {
                        if (theGame.getnMaxRows() >= 2 && theGame.getnMaxCols() >= 4) {
                            theGame.setNCols(4);
                            theGame.setNRows(2);
                            theGame.setGoToPairs(4);
                        } else {
                            // do nothing
                        }

                    } else if (item.toString().charAt(0) == '8') {
                        if (theGame.getnMaxRows() >= 4 && theGame.getnMaxCols() >= 4) {
                            theGame.setNCols(4);
                            theGame.setNRows(4);
                            theGame.setGoToPairs(8);
                        } else {
                            // do nothing
                        }

                    } else if (item.toString().charAt(0) == '1') {
                        if (theGame.getnMaxRows() >= 6 && theGame.getnMaxCols() >= 5) {
                            theGame.setNCols(5);
                            theGame.setNRows(6);
                            theGame.setGoToPairs(15);
                        } else {
                            // do nothing
                        }

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                final Object item = parent.getItemAtPosition(pos);
                if (item != null)
                    Log.i("Configuration.java", "The item selected is: " + item);

                Character firstLetter = item.toString().charAt(0);
                if (firstLetter == 'B') {
                    Log.i("Configuration.java", "Beginner mode!");
                    theGame.setBeginner(true);
                    theGame.setAdvanced(false);

                } else {
                    Log.i("Configuration.java", "Advanced mode!");
                    theGame.setBeginner(false);
                    theGame.setAdvanced(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
