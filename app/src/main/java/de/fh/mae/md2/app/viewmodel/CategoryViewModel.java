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

    private List<Category> allCategories;

    public CategoryViewModel(Application application){
        super(application);
        categoryRepository = new CategoryRepository(application);
        allCategories = categoryRepository.getAllCategories();
    }

    public List<Category> getAllCategories() {
        return allCategories;
    }

    public void insert(Category category){
        categoryRepository.insert(category);
    }

    public void update(Category category){
        categoryRepository.update(category);
    }
}