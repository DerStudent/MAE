package de.fh.mae.md2.app.Category;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.entities.Category;
import de.fh.mae.md2.app.viewmodel.CategoryViewModel;


public class CategoryIncomeTabFragment extends Fragment {
    private CategoryListAdapter mCategoryListAdapter;
    private Context context;
    private CategoryViewModel mCategoryViewModel;
    Category old;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        mCategoryListAdapter = new CategoryListAdapter(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabfragment_category_income, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_category_income);
        mCategoryListAdapter = new CategoryListAdapter(context);
        recyclerView.setAdapter(mCategoryListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        mCategoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

        mCategoryViewModel.getAllIncomeCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable final List<Category> categories) {
                // Update the cached copy of the categories in the adapter.
                mCategoryListAdapter.setCategories(categories);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        final String categoryName = sharedPreferences.getString("categoryName", "");
        int categoryImage = sharedPreferences.getInt("categoryImage", 0);
        boolean isIncome = sharedPreferences.getBoolean("categoryIsIncome", false);

        if(categoryImage != 0 && categoryName != "") {
            old = mCategoryViewModel.loadCategoryByName(categoryName);

            Category c = new Category(categoryName, categoryImage, isIncome);
            if(old == null) {
                mCategoryViewModel.insert(c);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("categoryName", "");
                editor.putInt("categoryImage", 0);
                editor.putBoolean("categoryIsIncome", false);
                editor.apply();
                Toast.makeText(getContext(), categoryName + " hinzugefügt", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getContext(), categoryName + " bereits vorhanden", Toast.LENGTH_LONG).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("categoryName", "");
                editor.putInt("categoryImage", 0);
                editor.putBoolean("categoryIsIncome", false);
                editor.apply();
            }
        }
    }
}