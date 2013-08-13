package com.bff.games.matches.os;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 2/3/13
 * Time: 10:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class ObjectServer {
    private static ObjectServer instance;
    private HashMap<String, Object> sources;
    private static Context applicationContext;


    public ObjectServer(Context context) throws Exception {
        super();
        this.applicationContext = context;

        initSources();
    }

    private void initSources() throws Exception {
        sources = new HashMap<String, Object>();

        sources.put("assets", new ConnectionToAssets(applicationContext));
    }

}
