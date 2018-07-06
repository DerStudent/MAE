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

import java.util.List;

import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.enums.ICategroryType;


public class CategoryIncomeTabFragment extends Fragment {
    private CategoryListAdapter mCategoryListAdapter;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_category_income, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_category_income);


        List<Category> categories = CategoryHelper.getCategoriesByType(ICategroryType.INCOME);
        mCategoryListAdapter = new CategoryListAdapter(context, categories);

        recyclerView.setAdapter(mCategoryListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }
}