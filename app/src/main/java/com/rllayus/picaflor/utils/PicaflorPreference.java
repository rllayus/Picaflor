package com.rllayus.picaflor.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Ricardo Laredo on 28-Jul-15.
 */
public class PicaflorPreference {
    private static PicaflorPreference ourInstance = new PicaflorPreference();
    private final static String KEY_DISTANCE="Distance";
    private SharedPreferences mPreferences;

    public static PicaflorPreference getInstance() {
        return ourInstance;
    }

    private PicaflorPreference() {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(ProveedorDeObjetos.getInstance().getBaseContext());
    }

    /**
     *
     * @param distance referido en Kilometros
     */
    public void setDistance(int distance){
        mPreferences.edit().putInt(KEY_DISTANCE,distance).commit();
    }

    /**
     *
     * @return referido en Kilometros valor por default 10 kilometros
     */
    public int getDistance(){
        return mPreferences.getInt(KEY_DISTANCE,10);
    }
    public String getRestServiceUrl(){
        return "";
    }
}
