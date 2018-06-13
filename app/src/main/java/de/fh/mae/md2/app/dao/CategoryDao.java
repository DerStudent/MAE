package de.fh.mae.md2.app.dao;

import de.fh.mae.md2.app.entities.Category;
import android.arch.persistence.room.*;

@Dao
public interface CategoryDao {

    @Insert
    public void insertCategory(Category category);

    @Update
    public void updateCategory(Category category);

    @Delete
    public void deleteCategory(Category category);

    @Query("SELECT * FROM categories")
    public Category[] loadAllCategories();

}
