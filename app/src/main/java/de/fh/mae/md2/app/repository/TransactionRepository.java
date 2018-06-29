package de.fh.mae.md2.app.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import de.fh.mae.md2.app.dao.TransactionDao;
import de.fh.mae.md2.app.database.AppDatabase;
import de.fh.mae.md2.app.entities.Transaction;

public class TransactionRepository {

    private TransactionDao transactionDAO;
    private List<Transaction> allTransactions;

    public TransactionRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);

        transactionDAO = db.transactionDao();

        allTransactions = transactionDAO.loadAllTransactions();
    }

    public List<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public void insert(Transaction transaction){
        new insertAsyncTask(transactionDAO).execute(transaction);
    }

    private static class insertAsyncTask extends AsyncTask<Transaction, Void, Void> {

        private TransactionDao mAsyncTaskDao;

        insertAsyncTask(TransactionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Transaction... params) {
            mAsyncTaskDao.insertTransaction(params[0]);
            return null;
        }
    }

    public void update(Transaction transaction){
        new updateAsyncTask(transactionDAO).execute(transaction);
    }

    private static class updateAsyncTask extends AsyncTask<Transaction, Void, Void> {

        private TransactionDao mAsyncTaskDao;

        updateAsyncTask(TransactionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Transaction... params) {
            mAsyncTaskDao.updateTransaction(params[0]);
            return null;
        }
    }
}
