package de.fh.mae.md2.app.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

import de.fh.mae.md2.app.entities.Transaction;
import de.fh.mae.md2.app.repository.TransactionRepository;

public class TransactionViewModel extends AndroidViewModel {

    private TransactionRepository transactionRepository;

    public TransactionViewModel(Application application){
        super(application);
        transactionRepository = new TransactionRepository(application);
    }

    public void insert(Transaction transaction){
        transactionRepository.insert(transaction);
    }

    public void update(Transaction transaction){
        transactionRepository.update(transaction);
    }

    public void delete(Transaction transaction) {transactionRepository.delete(transaction);}

    public List<Transaction> loadLastTransactions(int count){
        return transactionRepository.loadLastTransactions(count);
    }

    public List<Transaction> loadTransactionAllFromTo(Date begin, Date end){
        return transactionRepository.loadTransactionAllFromTo(begin, end);
    }

    public List<Transaction> loadTransactionInFromTo(Date begin, Date end){
        return transactionRepository.loadTransactionInFromTo(begin, end);
    }

    public List<Transaction> loadTransactionOutFromTo(Date begin, Date end){
        return transactionRepository.loadTransactionOutFromTo(begin, end);
    }

    public Transaction getTransactionById(int id){
        return transactionRepository.getTransactionById(id);
    }

}
