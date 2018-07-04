package de.fh.mae.md2.app.Category;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
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
    SharedPreferences sharedPreferences;
    Category oldCategory;
    Category newCategory;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        mCategoryListAdapter = new CategoryListAdapter(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        resetSharedPreference(sharedPreferences);
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
        final String categoryName = sharedPreferences.getString("categoryName", "");
        int categoryImage = sharedPreferences.getInt("categoryImage", 0);
        boolean isIncome = sharedPreferences.getBoolean("categoryIsIncome", false);
        String categoryAction = sharedPreferences.getString("categoryAction", "");
        int id = sharedPreferences.getInt("categoryId", 0);

        if(categoryImage != 0 && categoryName != "") {
            oldCategory = mCategoryViewModel.loadCategoryByName(categoryName, isIncome);
            newCategory = new Category(categoryName, categoryImage, isIncome);

            if(((oldCategory == null) && (categoryAction == "insert"))) {
                mCategoryViewModel.insert(newCategory);
                resetSharedPreference(sharedPreferences);
                Toast.makeText(getContext(), categoryName + " hinzugefügt", Toast.LENGTH_LONG).show();
            }
            else if(((oldCategory == null) || ((oldCategory.getName() != newCategory.getName()) || (oldCategory.getImage() != newCategory.getImage()))) && (categoryAction == "update")){
                newCategory.setId(id);
                mCategoryViewModel.update(newCategory);
                resetSharedPreference(sharedPreferences);
                Toast.makeText(getContext(), categoryName + " aktualisiert", Toast.LENGTH_LONG).show();
            }
            else if((oldCategory.getIsIncomeCategory() != newCategory.getIsIncomeCategory()) && (oldCategory.getName() != newCategory.getName()) && (categoryAction == "insert")){
                mCategoryViewModel.insert(newCategory);
                resetSharedPreference(sharedPreferences);
                Toast.makeText(getContext(), categoryName + " hinzugefügt", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getContext(), categoryName + " bereits vorhanden", Toast.LENGTH_LONG).show();
                resetSharedPreference(sharedPreferences);
            }
        }
    }

    private void resetSharedPreference(SharedPreferences sharedPreferences){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("categoryName", "");
        editor.putInt("categoryImage", 0);
        editor.putBoolean("categoryIsIncome", false);
        editor.putString("categoryAction", "");
        editor.apply();
    }
}