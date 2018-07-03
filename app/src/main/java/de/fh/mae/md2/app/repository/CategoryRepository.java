package de.fh.mae.md2.app.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Query;
import android.icu.util.ULocale;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

import de.fh.mae.md2.app.dao.CategoryDao;
import de.fh.mae.md2.app.database.AppDatabase;
import de.fh.mae.md2.app.entities.Category;

public class CategoryRepository {

    private CategoryDao categoryDao;
    private LiveData<List<Category>> allIncomeCategories;
    private LiveData<List<Category>> allOutcomeCategories;
    private Category c;
    private int id;

    public CategoryRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        categoryDao = db.categoryDao();
        allIncomeCategories = categoryDao.loadAllIncomeCategories();
        allOutcomeCategories = categoryDao.loadAllOutcomeCategories();
    }

    public LiveData<List<Category>> getAllIncomeCategories() {
        return allIncomeCategories;
    }

    public LiveData<List<Category>> getAllOutcomeCategories() {
        return allOutcomeCategories;
    }

    public Category loadCategoryByName(final String name){
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    c = categoryDao.loadCategoryByName(name);
                    return null;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return c;
    }

    public Category loadCategoryById(final int id){
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    c = categoryDao.loadCategoryById(id);
                    return null;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return c;
    }

    public void delete(Category category) { categoryDao.deleteCategory(category); }

    public void insert(Category category) {
        new insertAsyncTask(categoryDao).execute(category);
    }

    public void update(Category category){
        new updateAsyncTask(categoryDao).execute(category);
    }

/*    public List<Category> selectCategoryByAttributes(String name, boolean isIncomeCategory){
        return categoryDao.selectCategoryByAttributes(name, isIncomeCategory);
    }*/

    private static class insertAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDao asyncTaskDao;

        insertAsyncTask(CategoryDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Category... params) {
            asyncTaskDao.insertCategory(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Category, Void, Void> {
        private CategoryDao asyncTaskDao;

        updateAsyncTask(CategoryDao dao){
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Category... params) {
            asyncTaskDao.updateCategory(params[0]);
            return null;
        }
    }
}