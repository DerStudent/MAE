package de.fh.mae.md2.app.dao;

import de.fh.mae.md2.app.entities.Transaction;
import android.arch.persistence.room.*;

@Dao
public interface TransactionDao {

    @Insert
    public void insertTransaction(Transaction transaction);

    @Update
    public void updateTransaction(Transaction transaction);

    @Delete
    public void deleteTransaction(Transaction transaction);

    @Query("SELECT * FROM transactions")
    public Transaction[] loadAllTransactions();
}
