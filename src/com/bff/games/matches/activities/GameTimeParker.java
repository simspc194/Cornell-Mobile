package com.bff.games.matches.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bff.games.matches.Card;
import com.bff.games.matches.R;
import com.bff.games.matches.TheGame;
import com.bff.games.matches.Widgets.MatchFlipper;

public class GameTimeParker extends Activity {
    final static String TAG="GameTime";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboard);

        LinearLayout parent = (LinearLayout)findViewById(R.id.gameboard);
        final int nRows = 4;
        final int nColumns = 4;
        int nCard = 0;
        int id = 0;
        Card theCard;
        final Card[] theCards;
        MatchFlipper firstFlipped, secondFlipped = null;


        TheGame theGame = TheGame.getInstance();

        theCards = theGame.getRandomizedCards(nRows * nColumns);
        Log.i(TAG, "We have "+theCards.length+" cards");


        for (int nRow = 0; nRow < nRows; nRow++) {
            Log.i(TAG, "Adding row "+nRow);
            LinearLayout row = (LinearLayout) getLayoutInflater().inflate(R.layout.inf_layout, parent, false);

            for (int nCol = 0; nCol < nColumns; nCol++) {
                Log.i(TAG, "Adding column "+nCol) ;
                Log.i(TAG, "Adding card "+nCard);
                theCard = theCards[nCard];
                nCard++;



                final MatchFlipper mf = (MatchFlipper) getLayoutInflater().inflate(R.layout.inf_flipper, row, false);
                Log.i(TAG, "setting flipper id to "+theCard.getId());
                mf.setId(theCard.getId());

                ImageView image1 = (ImageView) getLayoutInflater().inflate(R.layout.inf_image, mf, false);
                ImageView image2 = (ImageView) getLayoutInflater().inflate(R.layout.inf_image, mf, false);

                image1.setImageResource(R.drawable.concentrationbutton);
                image2.setImageDrawable(theCard.getDrawable());

                mf.addView(image1);
                mf.addView(image2);

                row.addView(mf);

                final Card TheCard = theCard;
                final int NCard = nCard;
                final int finalNCard = nCard;
                mf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mf.showNext();
                        Log.i(TAG, "id of flipper is "+view.getId());
                        TheCard.setCardFlipped(true);
                        for (int i = 0; i < theCards.length; i++){
                            Card check = theCards[i];
                            if (check.getCardFlipped()) {
                                if(TheCard.getId() == check.getId()){
                                    Log.i(TAG, "These cards have the same id. Get rid of them eventually");

                                }
                                else {
                                    Log.i(TAG, "These cards have different id's. Flip them back over.");
                                    TheCard.setCardFlipped(false);
                                    check.setCardFlipped(false);
                                }

                            }
                            else {
                                Log.i(TAG, "We are continuing, no other card has been clicked yet.");
                                continue;
                            }
                        }
                    }
                });
            }
            parent.addView(row);
        }

    }
}
