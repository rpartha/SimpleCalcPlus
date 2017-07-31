package applications.rpartha.com.simplecalcplus;

import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.content.Context;
import android.graphics.Color;

/**
 * Created by tillu on 7/29/2017.
 */

public class CustomSettings {

    private SharedPreferences sharedPreferences;
    private Context context;

    public CustomSettings(Context context) {
        this.context = context;
        PreferenceManager.setDefaultValues(context,R.xml.preferences,false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getPrimaryColor() {
        int color;
        try {
            color = Color.parseColor(sharedPreferences.getString(context.getResources().getString(R.string.pref_primary_color),context.getString(R.string.primary_color_default)));
        } catch(IllegalArgumentException illegal) {
            color = Color.parseColor(context.getString(R.string.primary_color_default));
        }
        return color;
    }

    public int getBackgroundColor() {
        int color;
        try {
            color = Color.parseColor(sharedPreferences.getString(context.getString(R.string.pref_background_color),
                                                                 context.getString(R.string.background_color_default)));
        } catch(IllegalArgumentException illegal) {
            color = Color.parseColor(context.getString(R.string.background_color_default));
        }
        return color;
    }
}