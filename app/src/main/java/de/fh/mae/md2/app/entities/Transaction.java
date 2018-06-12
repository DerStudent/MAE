package de.fh.mae.md2.app.entities;

import java.util.Date;
import android.arch.persistence.room.*;
import de.fh.mae.md2.app.entities.Category;

@Entity(tableName = "transactions", foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id"))
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private double value;

    @ColumnInfo(name = "category_id")
    private int  categoryID;

    private Date date;


    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }
}