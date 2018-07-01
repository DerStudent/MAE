package de.fh.mae.md2.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import de.fh.mae.md2.app.R;

public class CategoryPopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.category_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Resources r = getResources();
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 242, r.getDisplayMetrics());
        int width = dm.widthPixels;
        getWindow().setLayout((int)(width * .95), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void clickEvent(View v)
    {
        Intent myIntent = new Intent(CategoryPopupActivity.this, ChooseIconActivity.class);
        startActivity(myIntent);
    }
}