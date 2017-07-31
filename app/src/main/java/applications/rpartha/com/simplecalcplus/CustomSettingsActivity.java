package applications.rpartha.com.simplecalcplus;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.graphics.Color;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by tillu on 7/29/2017
 */

public class CustomSettingsActivity extends AppCompatActivity {

    ColorPicker colorPicker;
    CustomSettings customSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                Log.e("Error"+Thread.currentThread().getStackTrace()[2],paramThrowable.getLocalizedMessage());
            }
        });*/

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new CustomSettingsFragment())
                .commit();
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        colorPicker = new ColorPicker(this);
        customSettings = new CustomSettings(this);
        refreshColors();
    }

    public void onResume() {
        super.onResume();
        refreshColors();
    }

    public void refreshColors() {
        colorPicker.setTextAndIconColor(customSettings.getBackgroundColor());
        colorPicker.setTopBottomBarsColor(customSettings.getPrimaryColor());
    }

    public static class CustomSettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        private ColorPicker colorPicker;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
            EditTextPreference primaryColor = (EditTextPreference) findPreference(getActivity().getResources().getString(R.string.pref_primary_color));
            primaryColor.setOnPreferenceChangeListener(this);
            EditTextPreference backgroundColor = (EditTextPreference) findPreference(getActivity().getResources().getString(R.string.pref_background_color));
            backgroundColor.setOnPreferenceChangeListener(this);
            colorPicker = new ColorPicker(getActivity());
        }

        public boolean onPreferenceChange (Preference preference, Object newValue) {
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            String key = editTextPreference.getKey();
            int color;
            if (key == getActivity().getResources().getString(R.string.pref_primary_color)) {
                try {
                    color = Color.parseColor(newValue.toString());
                } catch (IllegalArgumentException illegal) {
                    Toast.makeText(getActivity(), getString(R.string.invalid_color), Toast.LENGTH_SHORT).show();
                    return false;
                }
                colorPicker.setTopBottomBarsColor(color);
            }
            else if (key == getActivity().getResources().getString(R.string.pref_background_color)) {
                try {
                    color = Color.parseColor(newValue.toString());
                } catch (IllegalArgumentException illegal) {
                    Toast.makeText(getActivity(), getString(R.string.invalid_color), Toast.LENGTH_SHORT).show();
                    return false;
                }
                colorPicker.setTextAndIconColor(color);
            }

            return true;
        }
    }

}
