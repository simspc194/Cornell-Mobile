package com.bff.games.matches.Widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ViewFlipper;

/**
 * Created with IntelliJ IDEA.
 * User: dooter
 * Date: 6/23/13
 * Time: 9:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class MatchFlipper extends ViewFlipper {
    private int nFlipped = 0;
    private int nLeft = 0;

    public MatchFlipper(Context context) {
        super(context);
        Log.i(this.getClass().getName(), "I am in constructor");
    }

    public MatchFlipper(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(this.getClass().getName(), "I am in constructor with attributes");
    }

    public void flipIt() {
        this.nFlipped++;
    }

    public int getNFlipped() {
        return this.nFlipped;
    }

    public int anyLeft(){
        this.nLeft++;
        return nLeft;
    }


}
