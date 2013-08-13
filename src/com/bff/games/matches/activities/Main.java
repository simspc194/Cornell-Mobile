package com.bff.games.matches.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.bff.games.matches.R;
import com.bff.games.matches.TheGame;

public class Main extends Activity {
    private final static int requestCodeForMain=100;
    private final static int requestCodeForResults=200;

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getMaxScreenColumns();
        getMaxScreenRows();

        final ImageView clickMe = (ImageView) findViewById(R.id.play);
        // Listening to login button
        clickMe.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), GameTime.class);
                startActivityForResult(i, requestCodeForMain);
            }
        });
        final ImageView instructions = (ImageView) findViewById(R.id.instructions);
        instructions.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), instructions.class);
                startActivity(i);
            }
        });
        final ImageView aboutUs = (ImageView) findViewById(R.id.aboutus);
        aboutUs.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(i);
            }
        });

        final ImageView configs = (ImageView) findViewById(R.id.configurations);
        configs.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), Configurations.class);
                startActivity(i);
            }
        });

        final ImageView study = (ImageView) findViewById(R.id.study);
        study.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), Study.class);
                startActivity(i);
            }
        });
    }
    public void getMaxScreenColumns() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        TheGame theGame = TheGame.getInstance();
        Log.i("GameTime.java", "width is: " + width);
        theGame.setGoToPairs(4);
        if (width < 100) {
            throw new RuntimeException();
        } else if (width >= 100 && width < 200) {
            theGame.setnMaxCols(1);


        } else if (width >= 200 && width < 300) {
            theGame.setnMaxCols(2);

        } else if (width >= 300 && width < 400) {
            theGame.setnMaxCols(3);

        } else if (width >= 400 && width < 500) {
            theGame.setnMaxCols(4);

        } else {
            theGame.setnMaxCols(5);

        }

    }

    public void getMaxScreenRows() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        TheGame theGame = TheGame.getInstance();

        Log.i("GameTime.java", "height is: " + height);

        if (height < 116)
            throw new RuntimeException();

        else if (height >= 116 && height < 232) {
            theGame.setnMaxRows(1);

        } else if (height >= 232 && height < 348) {
            theGame.setnMaxRows(2);

        } else if (height >= 348 && height < 464) {
            theGame.setnMaxRows(3);

        } else if (height >= 464 && height < 580) {
            theGame.setnMaxRows(4);

        } else if (height >= 580 && height < 696) {
            theGame.setnMaxRows(5);

        } else {
            theGame.setnMaxRows(6);

        }


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == requestCodeForMain) {
            if (resultCode == RESULT_OK) {
                Log.i("Main.java", "This worked and we are in the result_cancelled");
                Intent i = new Intent(getApplicationContext(), Results.class);
                startActivityForResult(i, requestCodeForResults);
            }
        } else if (requestCode == requestCodeForResults) {
            if (resultCode == RESULT_OK) {

                Boolean plAgain = data.getBooleanExtra("plAgain", true);
                Boolean goHome = data.getBooleanExtra("goHome", true);
                if (plAgain) {
                    Intent i = new Intent(getApplicationContext(), GameTime.class);
                    startActivityForResult(i, 100);
                    Log.i("Main.java", "RESULT_OK, User has requested to play again");
                } else if (goHome) {
                    Log.i("Main.java", "RESULT_OK, User has requested to stay home!");
                }
            }
        }//onActivityResult
    }

}
