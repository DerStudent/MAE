package de.fh.mae.md2.app.dao;

import de.fh.mae.md2.app.entities.Category;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category... category);

    @Update
    void updateCategory(Category... category);

    @Delete
    void deleteCategory(Category... category);

    @Query("DELETE FROM categories")
    void deleteAll();

/*    @Query("SELECT * FROM categories WHERE name LIKE :name AND isIncomeCategory LIKE :isIncomeCategory")
    List<Category> selectCategoryByAttributes(String name, boolean isIncomeCategory);*/

    @Query("SELECT * FROM categories WHERE status = 1 ORDER BY name ASC ")
    LiveData<List<Category>> loadAllIncomeCategories();

    @Query("SELECT * FROM categories WHERE status = 0 ORDER BY name ASC ")
    LiveData<List<Category>> loadAllOutcomeCategories();

    @Query("SELECT * FROM categories WHERE id = :id")
    LiveData<Category> getCategoryById(int id);

}