package de.fh.mae.md2.app.transaction;

import java.util.Date;

import de.fh.mae.md2.app.Category.Category;

public class Transaction {
    private long id;
    private static long idCounter = 0L;

    private String amount;

    private Category category;

    private String note;

    private Date date;

    public Transaction(String value, Category category, String note, Date date){
        this.id = idCounter++;
        this.amount = value;
        this.category = category;
        this.note = note;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public void setNote(String note) {
        this.note = note;
    }
}