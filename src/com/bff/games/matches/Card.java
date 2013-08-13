package com.bff.games.matches;

import android.graphics.drawable.Drawable;

/**
 * Created with IntelliJ IDEA.
 * User: dooter
 * Date: 7/3/13
 * Time: 9:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class Card {
    private int id;
    private Drawable drawable;
    private Drawable flippedDrawable;

    private boolean flipped;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void setCardFlipped(boolean flip) {
        this.flipped = flip;
    }

    public boolean getCardFlipped(){
        return flipped;
    }

    public Drawable getFlippedDrawable() {
        return flippedDrawable;
    }

    public void setFlippedDrawable(Drawable flippedDrawable) {
        this.flippedDrawable = flippedDrawable;
    }
}
