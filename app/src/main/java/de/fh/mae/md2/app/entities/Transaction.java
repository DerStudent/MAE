package de.fh.mae.md2.app.entities;
/*
import java.util.Date;

import android.arch.persistence.room.*;
import android.support.annotation.NonNull;
import de.fh.mae.md2.app.enums.*;

@Entity(tableName = "transactions", foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id", childColumns = "category_id"))
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    @NonNull
    private String value;

    @ColumnInfo(name = "category_id")
    @NonNull
    private long categoryID;

    private String note;

    @NonNull
    private Date date;

    @NonNull
    @ITransactionStatus
    private int status;



    public Transaction(@NonNull String value, @NonNull long categoryID, String note, @NonNull Date date){
        this.value = value;
        this.categoryID = categoryID;
        this.note = note;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String value) {
        this.note = note;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @ITransactionStatus
    public int getStatus(){
        return  status;
    }

    public void setStatus(@ITransactionStatus int status) {
        this.status = status;
    }
}
*/