package de.fh.mae.md2.app.dao;

import de.fh.mae.md2.app.entities.Category;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    public void insertCategory(Category category);

    @Update
    public void updateCategory(Category category);

    @Delete
    public void deleteCategory(Category category);

    @Query("DELETE FROM categories WHERE name = :name AND image = :image")
    public void deleteByAttributes(String name, int image);

    @Query("DELETE FROM categories")
    public void deleteAll();

    @Query("SELECT * FROM categories WHERE isIncomeCategory = 1 ORDER BY name ASC ")
    public LiveData<List<Category>> loadAllIncomeCategories();

    @Query("SELECT * FROM categories WHERE isIncomeCategory = 0 ORDER BY name ASC ")
    public LiveData<List<Category>> loadAllOutcomeCategories();

}