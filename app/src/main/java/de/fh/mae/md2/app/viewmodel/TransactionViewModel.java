package de.fh.mae.md2.app.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import de.fh.mae.md2.app.entities.Transaction;
import de.fh.mae.md2.app.repository.TransactionRepository;

public class TransactionViewModel extends AndroidViewModel {

    private TransactionRepository transactionRepository;

    private List<Transaction> allTransactions;

    public TransactionViewModel(Application application){
        super(application);
        transactionRepository = new TransactionRepository(application);
        allTransactions = transactionRepository.getAllTransactions();
    }

    public List<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public void insert(Transaction transaction){
        transactionRepository.insert(transaction);
    }

    public void  update(Transaction transaction){
        transactionRepository.update(transaction);
    }
}
