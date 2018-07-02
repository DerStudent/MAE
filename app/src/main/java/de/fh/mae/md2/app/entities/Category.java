package de.fh.mae.md2.app.entities;

import android.arch.persistence.room.*;
import android.support.annotation.NonNull;

@Entity(tableName = "categories")
public class Category {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    private String name;

    @NonNull
    private int image;

    public Category(@NonNull String name, @NonNull int image){
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    // TODO: getCategoryById(Long id)
}