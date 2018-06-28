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

    @Query("SELECT * FROM categories")
    public List<Category> loadAllCategories();

}
