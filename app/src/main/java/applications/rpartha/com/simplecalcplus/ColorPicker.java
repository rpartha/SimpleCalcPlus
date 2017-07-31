package applications.rpartha.com.simplecalcplus;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by tillu on 7/29/2017.
 */


public class ColorPicker {

    Activity activity;
    CustomSettings customSettings;

    public ColorPicker(Activity activity) {
        this.activity = activity;
        customSettings = new CustomSettings((Context)activity);
    }

    public void setTextAndIconColor(int color) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(color), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);
        Drawable arrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        arrow.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        ((AppCompatActivity)activity).getSupportActionBar().setHomeAsUpIndicator(arrow);
    }

    public void setTopBottomBarsColor(int color) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
            window.setNavigationBarColor(color);
        }
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(color));
    }
}