package de.fh.mae.md2.app.transaction;

import java.util.Date;

public class Transaction {
    private int id;
    private double value;
    private Category category;
    private Date date;
    private int image;
    private static int numberOfTransactions = 0;

    public Transaction(double value, Category category, Date date, int image) {
        this.id = numberOfTransactions;
        numberOfTransactions++;
        this.value = value;
        this.category = category;
        this.date = date;
        this.image = image;
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

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
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
}