package de.fh.mae.md2.app.repository;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import de.fh.mae.md2.app.activities.AddTransactionActivity;
import de.fh.mae.md2.app.dao.CategoryDao;
import de.fh.mae.md2.app.database.AppDatabase;
import de.fh.mae.md2.app.entities.Category;

public class CategoryRepository extends AndroidViewModel {

//  private Context context;
    private CategoryDao categoryDao;
    private LiveData<List<Category>> allIncomeCategories;
    private LiveData<List<Category>> allOutcomeCategories;

    private long count;
    private Category category;

    public CategoryRepository(Application application){
        super(application);
//      context = application.getApplicationContext();
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

    public void delete(Category category) { categoryDao.deleteCategory(category); }

    public void insert(Category category) {
        new insertAsyncTask(categoryDao).execute(category);
    }

    public void update(Category category){
        new updateAsyncTask(categoryDao).execute(category);
    }

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

    public Category getCategoryById(long id) {
        try {
            new  AsyncTask<String, Void, Void>(){
                @Override
                protected Void doInBackground(final String... params){
                    category = categoryDao.getCategoryById(params[0]);
                    return null;
                }
            }.execute(String.valueOf(id)).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return category;
    }

    public Category getFirstCategory() {
        try {
            new  AsyncTask<Void, Void, Void>(){
                @Override
                protected Void doInBackground(Void... voids){
                    category = categoryDao.getFirstCategory();
                    return null;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return category;
    }

    public boolean hasCategory() {
        try {
            new  AsyncTask<Void, Void, Void>(){
                @Override
                protected Void doInBackground(Void... voids){
                    count = categoryDao.getCategoryCount();
                    return null;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(count > 0) {
            return true;
        }

        return false;
    }

//    public void startAddTransactionIfHasCategory() {
//        new startAddTransactionIfHasCategoryAsyncTask(categoryDao, context).execute();
//    }
//
//    private static class startAddTransactionIfHasCategoryAsyncTask extends AsyncTask<Void, Void, Long> {
//
//        private CategoryDao categoryDao;
//        private Context context;
//
//        startAddTransactionIfHasCategoryAsyncTask(CategoryDao categoryDao, Context context) {
//            this.categoryDao = categoryDao;
//            this.context = context;
//        }
//
//
//        @Override
//        protected Long doInBackground(Void... params) {
//            return categoryDao.getCategoryCount();
//        }
//
//        @Override
//        protected void onPostExecute(Long result) {
//            if(result > 0) {
//                Intent intent = new Intent(context, AddTransactionActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//            } else {
//                Toast.makeText(context, "Erstellen Sie eine Kategorie", Toast.LENGTH_LONG).show();
//            }
//        }
//
//    }
}