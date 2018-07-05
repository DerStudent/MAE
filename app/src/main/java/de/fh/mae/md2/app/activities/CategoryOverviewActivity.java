package de.fh.mae.md2.app.activities;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fh.mae.md2.app.Category.CategoryIncomeTabFragment;
import de.fh.mae.md2.app.Category.CategoryOutcomeTabFragment;
import de.fh.mae.md2.app.Category.CategoryPageAdapter;
import de.fh.mae.md2.app.R;

public class CategoryOverviewActivity extends Fragment implements  View.OnClickListener {
    private FragmentActivity activity;

    private CategoryPageAdapter mCategoryPageAdapter;

    private ViewPager mViewPager;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();

        activity.setTitle(getString(R.string.title_activity_category_overview));
        setOnClickListeners();

        mCategoryPageAdapter = new CategoryPageAdapter(activity.getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) activity.findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_category_overview, container, false);
    }

    private void setupViewPager(ViewPager viewPager) {
        CategoryPageAdapter adapter = new CategoryPageAdapter(activity.getSupportFragmentManager());
        adapter.addFragment(new CategoryIncomeTabFragment(), getString(R.string.tab_text_1));
        adapter.addFragment(new CategoryOutcomeTabFragment(), getString(R.string.tab_text_2));
        viewPager.setAdapter(adapter);
    }

    private void setOnClickListeners() {
        FloatingActionButton buttonFloatingMain = (FloatingActionButton) activity.findViewById(R.id.button__floating_main);
        if(buttonFloatingMain != null) {
            buttonFloatingMain.setVisibility(View.VISIBLE);
            buttonFloatingMain.setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View view) {
        int i = view.getId();

        if (i == R.id.button__floating_main) {
            Intent myIntent = new Intent(activity, CategoryPopupActivity.class);
            startActivity(myIntent);
        }
    }

}