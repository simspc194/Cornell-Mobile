package com.bff.games.matches.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
 * Date: 7/31/13
 * Time: 10:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class Study extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study);

        final TheGame theGame = TheGame.getInstance();

        LinearLayout matchesTable = (LinearLayout) findViewById(R.id.fullMatches);

        Match[] gameMatches = theGame.getFullMatches();
        for(Match theMatch : gameMatches) {

            View view = getLayoutInflater().inflate(R.layout.inf_layout_study, matchesTable, false);


            ImageView imageView1 = (ImageView) view.findViewById(R.id.imageOfBug1);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.imageOfBug2);
            TextView aboutBug = (TextView) view.findViewById(R.id.textOfBug);

            final Drawable drawable2 = theMatch.getImage1();
            final Drawable drawable1 = theMatch.getImage2();

            imageView1.setImageDrawable(theMatch.getImage2());
            imageView2.setImageDrawable(theMatch.getImage1());
            aboutBug.setText(theMatch.getDescription());

            matchesTable.addView(view);

            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), BiggerPicture.class);
                    theGame.setImageToPass(drawable1);
                    startActivity(i);
                }
            });

            imageView2.setOnClickListener(new View.OnClickListener() {
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
