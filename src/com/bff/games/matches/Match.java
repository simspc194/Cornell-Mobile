

package com.bff.games.matches;

import android.graphics.drawable.Drawable;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 6/18/13
 * Time: 4:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Match {
    private int id;
    private Drawable image1;
    private Drawable image2;
    String description;

    public Drawable getImage1() {
        return image1;
    }

    public void setImage1(Drawable image1) {
        this.image1 = image1;
    }

    public Drawable getImage2() {
        return image2;
    }

    public void setImage2(Drawable image2) {
        this.image2 = image2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


