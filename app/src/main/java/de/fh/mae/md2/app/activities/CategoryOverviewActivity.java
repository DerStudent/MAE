package de.fh.mae.md2.app.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import de.fh.mae.md2.app.Category.CategoryIncomeTabFragment;
import de.fh.mae.md2.app.Category.CategoryOutcomeTabFragment;
import de.fh.mae.md2.app.Category.CategoryPageAdapter;
import de.fh.mae.md2.app.R;

public class CategoryOverviewActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private CategoryPageAdapter mCategoryPageAdapter;

    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_overview);
        Log.d(TAG, "onCreate: Starting.");

        FloatingActionButton b = findViewById(R.id.addCategoryButton);

        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(CategoryOverviewActivity.this, CategoryPopupActivity.class));
            }
        });

        mCategoryPageAdapter = new CategoryPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        CategoryPageAdapter adapter = new CategoryPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CategoryIncomeTabFragment(), "Einnahmen");
        adapter.addFragment(new CategoryOutcomeTabFragment(), "Ausgaben");
        viewPager.setAdapter(adapter);
    }
}