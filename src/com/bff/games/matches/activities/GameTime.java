package com.bff.games.matches.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bff.games.matches.Card;
import com.bff.games.matches.R;
import com.bff.games.matches.TheGame;
import com.bff.games.matches.Widgets.MatchFlipper;

public class GameTime extends Activity {
    final static String TAG = "GameTime";

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        Log.i(TAG, "GameTime: in onResume()");
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "GameTime: in onCreate");

        setContentView(R.layout.gameboard);


        LinearLayout parent = (LinearLayout) findViewById(R.id.gameboard);
        int nCard = 0;
        int id = 0;
        Card theCard;
        final Card[] theCards;


        final TheGame theGame = TheGame.getInstance();
        theGame.setBackOfCardDrawable(getResources().getDrawable(R.drawable.concentrationbutton));
        theGame.clear();

        theGame.setnMaxRows(getMaxScreenRows());
        theGame.setnMaxCols(getMaxScreenColumns());

        int nRows = theGame.getNRows();
        int nColumns = theGame.getNCols();

        theCards = theGame.getRandomizedCards(nRows * nColumns);
        theGame.setnCardsLeftInGame(theCards.length);
        Log.i(TAG, "We have " + theCards.length + " cards");


        for (int nRow = 0; nRow < nRows; nRow++) {
            // adding row
            LinearLayout row = (LinearLayout) getLayoutInflater().inflate(R.layout.inf_layout, parent, false);

            for (int nCol = 0; nCol < nColumns; nCol++) {
                // adding column and card number
                theCard = theCards[nCard];
                nCard++;


                final MatchFlipper mf = (MatchFlipper) getLayoutInflater().inflate(R.layout.inf_flipper, row, false);
                // setting flipper id
                mf.setId(theCard.getId());

                ImageView image1 = (ImageView) getLayoutInflater().inflate(R.layout.inf_image, mf, false);
                ImageView image2 = (ImageView) getLayoutInflater().inflate(R.layout.inf_image2, mf, false);


                image1.setImageDrawable(theCard.getDrawable());
                image2.setImageDrawable(theCard.getFlippedDrawable());


                mf.addView(image1);
                mf.addView(image2);

                row.addView(mf);

                Animation anOriginalIn = mf.getInAnimation();

                if (anOriginalIn == null) {
                    // No existing animation, so create one
                    anOriginalIn = AnimationUtils.loadAnimation(this,
                            android.R.anim.fade_in);

                }

                final long startTime = System.currentTimeMillis();
                anOriginalIn.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        synchronized (GameTime.class) {
                            theGame.setFlipPending(true);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // animation has ended
                        synchronized (GameTime.class) {
                            if (theGame.getFirstFlipped() != null && theGame.getSecondFlipped() != null) {
                                if (theGame.getFirstFlipped().getId() == theGame.getSecondFlipped().getId()) {

                                    final MatchFlipper firstToHide = theGame.getFirstFlipped();
                                    final MatchFlipper secondToHide = theGame.getSecondFlipped();

                                    firstToHide.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
//                                            theGame.getFirstFlipped().setVisibility(View.INVISIBLE);
//                                            theGame.getSecondFlipped().setVisibility(View.INVISIBLE);
                                            firstToHide.setVisibility(View.INVISIBLE);
                                            secondToHide.setVisibility(View.INVISIBLE);
                                            theGame.flipTwo(firstToHide.getId());
                                            theGame.setFirstFlipped(null);
                                            theGame.setSecondFlipped(null);


                                            // the same id's make them disappear
                                            if (theGame.areWeDone()) {
                                                // done
                                                long endTime = System.currentTimeMillis();
                                                long totalTime = (endTime - startTime) / 1000;
                                                theGame.setTotalTime(totalTime);
                                                Log.i("GameTime", "That took " + totalTime + " seconds");
                                                Intent returnIntent = new Intent();
                                                setResult(RESULT_OK, returnIntent);
                                                finish();

                                            }
                                        }
                                    }, 1000); // postDelayed


                                } else {
                                    theGame.getFirstFlipped().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            theGame.getFirstFlipped().setDisplayedChild(0);
                                            theGame.getSecondFlipped().setDisplayedChild(0);
                                            theGame.setFirstFlipped(null);
                                            theGame.setSecondFlipped(null);
                                            // different id's
                                        }
                                    }, 1000);

                                } // flippers are not equal
                            } // flippers are not null

                            theGame.setFlipPending(false);
                        } // syncronized ...


                    } // public void onAnimationEnd(Animation animation) {

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }
                });


                mf.setInAnimation(anOriginalIn);


                mf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //To change body of implemented methods use File | Settings | File Templates.

                        synchronized (GameTime.class) {
                            if(theGame.isFlipPending() == false) {
                                MatchFlipper currentFlipper = (MatchFlipper) view;
                                if (theGame.getFirstFlipped() == null) {
                                    theGame.setFirstFlipped(currentFlipper);

//                            if(currentFlipper.anyLeft()>0)
//                            {
                                    currentFlipper.flipIt();
                                    currentFlipper.showNext();
//                            }
                                    Log.i(TAG, "setting first flipped");
                                } else if (theGame.getSecondFlipped() == null) {
                                    theGame.setSecondFlipped(currentFlipper);
                                    currentFlipper.flipIt();
                                    currentFlipper.showNext();

                                    Log.i(TAG, "setting second flipped");

                                } else {
                                    // do nada - only allow two image clicks ...

                                }
                            }

                        } // first and second have been flipped
                    }

                });


            } // for (int nCol = 0; nCol < nColumns; nCol++) {

            parent.addView(row);

        } // for (int nRow = 0; nRow < nRows; nRow++) {


    }

    public int getMaxScreenColumns() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        TheGame theGame = TheGame.getInstance();
        Log.i("GameTime.java", "width is: " + width);
        if (width < 100) {
            throw new RuntimeException();
        } else if (width >= 100 && width < 200) {
            theGame.setnMaxCols(1);
            return 1;

        } else if (width >= 200 && width < 300) {
            theGame.setnMaxCols(2);
            return 2;
        } else if (width >= 300 && width < 400) {
            theGame.setnMaxCols(3);
            return 3;
        } else if (width >= 400 && width < 500) {
            theGame.setnMaxCols(4);
            return 4;
        } else {
            theGame.setnMaxCols(5);
            return 5;
        }

    }

    public int getMaxScreenRows() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        TheGame theGame = TheGame.getInstance();

        Log.i("GameTime.java", "height is: " + height);

        if (height < 116)
            throw new RuntimeException();

        else if (height >= 116 && height < 232) {
            theGame.setnMaxRows(1);
            return 1;
        } else if (height >= 232 && height < 348) {
            theGame.setnMaxRows(2);
            return 2;
        } else if (height >= 348 && height < 464) {
            theGame.setnMaxRows(3);
            return 3;
        } else if (height >= 464 && height < 580) {
            theGame.setnMaxRows(4);
            return 4;
        } else if (height >= 580 && height < 696) {
            theGame.setnMaxRows(5);
            return 5;
        } else {
            theGame.setnMaxRows(6);
            return 6;
        }

    }

}
