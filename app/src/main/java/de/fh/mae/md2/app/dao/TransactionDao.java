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

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    public List<Transaction> loadAllTransactions();

    //@Query("SELECT * FROM transactions ORDER BY date DESC LIMIT 10 OFFSET :offset")
    //public List<Transaction> loadAllTransactions(int offset);

    // TODO: LoadLastTransactions(int count)    Menge der letzten Transaktionen

    // TODO: loadTransactionsByTimespan(Date from, Date to)         Query mit ORDER BY date Desc

    // TODO: LoadTransaction(Long id)
}
