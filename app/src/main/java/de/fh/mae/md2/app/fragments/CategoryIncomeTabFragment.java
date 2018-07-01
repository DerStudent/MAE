package de.fh.mae.md2.app.fragments;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.entities.Category;
import de.fh.mae.md2.app.Category.CategoryAdapter;
import de.fh.mae.md2.app.repository.CategoryRepository;
import de.fh.mae.md2.app.viewmodel.CategoryViewModel;


public class CategoryIncomeTabFragment extends Fragment {
    private static final String TAG = "CategoryIncome";

    private FragmentActivity activity;

    CategoryViewModel cvw;
    List<Category> categoryList;

    //the recyclerview
    RecyclerView recyclerView;

    private void initCategory() {
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_category_income);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        categoryList = Arrays.asList(Category.populateData());
        /*
        while(categoryList.iterator().hasNext()){
            Category c = categoryList.iterator().next();
            if(c.getIsIncomeCategory() == false)
                categoryList.remove(c);
        }*/

        //creating recyclerview adapter
        CategoryAdapter adapter = new CategoryAdapter(activity, categoryList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabfragment_category_income,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        getActivity().setTitle(getString(R.string.title_activity_category_overview));
        initCategory();
    }
}
