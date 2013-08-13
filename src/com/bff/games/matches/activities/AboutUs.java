package com.bff.games.matches.activities;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import com.bff.games.matches.R;

/**
 * Created with IntelliJ IDEA.
 * User: dooter
 * Date: 6/22/13
 * Time: 6:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class AboutUs extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);
        TextView link = (TextView) findViewById(R.id.link);
        link.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
