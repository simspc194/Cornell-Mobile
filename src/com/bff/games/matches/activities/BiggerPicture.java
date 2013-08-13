package com.bff.games.matches.activities;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.bff.games.matches.R;
import com.bff.games.matches.TheGame;

/**
 * Created with IntelliJ IDEA.
 * User: dooter
 * Date: 8/3/13
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class BiggerPicture extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bigger_picture);

        TheGame theGame = TheGame.getInstance();

        Drawable theDrawable = theGame.getImageToPass();

        ImageView bigPic = (ImageView) findViewById(R.id.big_picture);
        bigPic.setImageDrawable(theDrawable);
        bigPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



}
