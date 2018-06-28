package de.fh.mae.md2.app.dao;

import de.fh.mae.md2.app.entities.Transaction;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface TransactionDao {

    @Insert
    public void insertTransaction(Transaction transaction);

    @Update
    public void updateTransaction(Transaction transaction);

    @Delete
    public void deleteTransaction(Transaction transaction);

    @Query("SELECT * FROM transactions")
    public List<Transaction> loadAllTransactions();
}
