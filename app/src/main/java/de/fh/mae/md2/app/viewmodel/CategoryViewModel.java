package de.fh.mae.md2.app.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import de.fh.mae.md2.app.entities.Category;
import de.fh.mae.md2.app.entities.Transaction;
import de.fh.mae.md2.app.repository.CategoryRepository;
import de.fh.mae.md2.app.repository.TransactionRepository;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryRepository categoryRepository;

    private LiveData<List<Category>> allIncomeCategories;
    private LiveData<List<Category>> allOutcomeCategories;

    public CategoryViewModel(Application application){
        super(application);
        categoryRepository = new CategoryRepository(application);
        allIncomeCategories = categoryRepository.getAllIncomeCategories();
        allOutcomeCategories = categoryRepository.getAllOutcomeCategories();
    }

    public LiveData<List<Category>> getAllIncomeCategories() {
        return allIncomeCategories;
    }

    public LiveData<List<Category>> getAllOutcomeCategories() {
        return allOutcomeCategories;
    }

    public void insert(Category category){
        categoryRepository.insert(category);
    }

    public void update(Category category){
        categoryRepository.update(category);
    }
}