package de.fh.mae.md2.app.dao;

import de.fh.mae.md2.app.entities.Transaction;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.Date;
import java.util.List;

@Dao
public interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertTransaction(Transaction... transaction);

    @Update
    public void updateTransaction(Transaction... transaction);

    @Delete
    public void deleteTransaction(Transaction... transaction);

    //@Query("SELECT * FROM transactions ORDER BY date DESC LIMIT 10 OFFSET :offset")
    //public List<Transaction> loadAllTransactions(int offset);


    @Query("select * from (select * from transactions order by id ASC limit :count) order by id DESC")
    public List<Transaction> loadLastTransactions(int count);


    @Query("SELECT * FROM transactions WHERE date BETWEEN :begin AND :end  AND status = 0 Order by date DESC")
    public List<Transaction> loadTransactionInFromTo(Date begin, Date end);

    @Query("SELECT * FROM transactions WHERE date BETWEEN :begin AND :end  AND status = 1 Order by date DESC")
    public List<Transaction> loadTransactionOutFromTo(Date begin, Date end);

    @Query("SELECT * FROM transactions WHERE date BETWEEN :begin AND :end Order by date DESC")
    public List<Transaction> loadTransactionAllFromTo(Date begin, Date end);

    @Query("SELECT * FROM transactions where id=:id")
    public Transaction getTransactionById(int id);
}
