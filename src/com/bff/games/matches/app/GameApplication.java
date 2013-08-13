package com.bff.games.matches.app;

import android.app.Application;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bff.games.matches.Match;
import com.bff.games.matches.R;
import com.bff.games.matches.TheGame;
import com.bff.games.matches.os.ObjectServer;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 1/31/13
 * Time: 1:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameApplication extends Application {

    private static GameApplication sInstance;
    private ObjectServer objectServerInstance;

    public static GameApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sInstance.initializeInstance();

        try {
            objectServerInstance = new ObjectServer(this.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i(GameApplication.class.getName(), "Initialized ...");
    }

    public ObjectServer getObjectServerInstance() {
        return objectServerInstance;
    }


    protected void initializeInstance() {
        TheGame theGame = TheGame.getInstance();

        Resources res = getResources();
        TypedArray matchArray = res.obtainTypedArray(R.array.matches);

        int nMatches = matchArray.length();

        /*
        if(nImages%2 != 0)
            throw an error
         */
// increment by two; this will blow up horribly if you don't make
//        sure there are an even number of images
        for(int i=0;i<nMatches;i+=3) {
            Match newMatch=new Match();

            Drawable image1 = matchArray.getDrawable(i);
            Drawable image2 = matchArray.getDrawable(i+1);
            String description = matchArray.getString(i+2);


            newMatch.setImage1(image1);
            newMatch.setImage2(image2);
            newMatch.setDescription(description);

// addMatch sets the match id
            theGame.addMatch(newMatch);
        }

    }

    @Override
    public void onTerminate() {
        // TODO: need to save state in database
        Log.i(GameApplication.class.getName(), "Terminating ...");

        super.onTerminate();

    }


}