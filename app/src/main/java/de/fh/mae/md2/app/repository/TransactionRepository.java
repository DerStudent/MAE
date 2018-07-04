package de.fh.mae.md2.app.repository;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import de.fh.mae.md2.app.dao.TransactionDao;
import de.fh.mae.md2.app.database.AppDatabase;
import de.fh.mae.md2.app.entities.Transaction;

public class TransactionRepository extends AndroidViewModel {

    private TransactionDao transactionDAO;

    private List<Transaction> list;

    private Transaction transaction;

    public TransactionRepository(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);

        transactionDAO = db.transactionDao();
    }

    public List<Transaction> loadLastTransactions(final int count) {
        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    list = transactionDAO.loadLastTransactions(count);
                    return null;
                }
            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<Transaction> loadTransactionInFromTo(final Date begin, final Date end) {
        try{
            new  AsyncTask<Void, Void, Void>(){
                @Override
                protected Void doInBackground(Void... voids){
                    list = transactionDAO.loadTransactionInFromTo(begin, end);
                    return null;
                }
            }.execute().get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Transaction> loadTransactionOutFromTo(final Date begin, final Date end) {
        try{
            new  AsyncTask<Void, Void, Void>(){
                @Override
                protected Void doInBackground(Void... voids){
                    list = transactionDAO.loadTransactionOutFromTo(begin, end);
                    return null;
                }
            }.execute().get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return list;
    }

    public Transaction getTransactionById(final long id) {
        try{
            new  AsyncTask<Void, Void, Void>(){
                @Override
                protected Void doInBackground(Void... voids){
                    transaction = transactionDAO.getTransactionById(id);
                    return null;
                }
            }.execute().get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return transaction;
    }


    public List<Transaction> loadTransactionAllFromTo(final Date begin, final Date end){
        try{
            new  AsyncTask<Void, Void, Void>(){
                @Override
                protected Void doInBackground(Void... voids){
                    list = transactionDAO.loadTransactionAllFromTo(begin, end);
                    return null;
                }
            }.execute().get();
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        return list;
    }

    public void insert(Transaction transaction) {
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

    public void update(Transaction transaction) {
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

    public void delete(Transaction transaction) {
        new deleteAsyncTask(transactionDAO).execute(transaction);
    }

    private static class deleteAsyncTask extends AsyncTask<Transaction, Void, Void> {

        private TransactionDao mAsyncTaskDao;

        deleteAsyncTask(TransactionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Transaction... params) {
            mAsyncTaskDao.deleteTransaction(params[0]);
            return null;
        }
    }
}
