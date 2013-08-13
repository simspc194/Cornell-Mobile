package com.bff.games.matches.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bff.games.matches.Match;
import com.bff.games.matches.R;
import com.bff.games.matches.TheGame;

/**
 * Created with IntelliJ IDEA.
 * User: dooter
 * Date: 7/10/13
 * Time: 12:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class Results extends Activity {
    private static final String TAG="Results";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        final TheGame theGame = TheGame.getInstance();

        final TextView seconds = (TextView) findViewById(R.id.seconds);
        seconds.setText(Long.toString(theGame.getTotalTime()));
        final ImageView playAgain = (ImageView) findViewById(R.id.play_again);
        // Listening to login button
        playAgain.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen

                Intent returnIntent = new Intent();
                returnIntent.putExtra("plAgain", true);
                returnIntent.putExtra("goHome", false);
                setResult(RESULT_OK,returnIntent);
                finish();

            }
        });

        final ImageView home = (ImageView) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("plAgain", false);
                returnIntent.putExtra("goHome", true);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        LinearLayout matchesLayout = (LinearLayout) findViewById(R.id.matchesLayout);

        Match[] gameMatches = theGame.getCurrentMatches();
        for(Match theMatch : gameMatches) {
            Log.i(TAG, "adding match ... ");

            View view = getLayoutInflater().inflate(R.layout.inf_layout_results, matchesLayout, false);


            ImageView image1 = (ImageView) view.findViewById(R.id.imageOfBug1);  //(ImageView) findViewById(R.id.imageOfBug1);
            ImageView image2 = (ImageView) view.findViewById(R.id.imageOfBug2);
            TextView aboutBug = (TextView) view.findViewById(R.id.textOfBug);

            final Drawable drawable1 = theMatch.getImage2();
            final Drawable drawable2 = theMatch.getImage1();

            image1.setImageDrawable(theMatch.getImage2());
            image2.setImageDrawable(theMatch.getImage1());
            aboutBug.setText(theMatch.getDescription());

            matchesLayout.addView(view);

            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), BiggerPicture.class);
                    theGame.setImageToPass(drawable1);
                    startActivity(i);
                }
            });

            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), BiggerPicture.class);
                    theGame.setImageToPass(drawable2);
                    startActivity(i);
                }
            });
        }
    }


}
