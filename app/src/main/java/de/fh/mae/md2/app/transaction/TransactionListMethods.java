package de.fh.mae.md2.app.transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.fh.mae.md2.app.MyPayments;

public class TransactionListMethods {

    private List<Transaction> list;
    private List<Transaction> tmpList;

    public TransactionListMethods(){
        list = MyPayments.getList();
        tmpList = new ArrayList<>();
    }

    public List<Transaction> loadLastTransactions(int count) {
        clearTmpList();
        int tmp = list.size() - count;
        for(Transaction t : list.subList(tmp, list.size())){
            tmpList.add(t);
        }
        return tmpList;
    }

    public List<Transaction> loadTransactionInFromTo(Date begin, Date end) {
        clearTmpList();
        for(Transaction t : list){
            if(t.getCategory().getIncome() == 0) {
                if (t.getDate().getDay() >= begin.getDay() && t.getDate().getMonth() >= begin.getMonth() && t.getDate().getYear() >= begin.getYear()) {
                    if (t.getDate().getDay() <= end.getDay() && t.getDate().getMonth() <= end.getMonth() && t.getDate().getYear() <= end.getYear()) {
                        tmpList.add(t);
                    }
                }
            }
        }
        return tmpList;
    }

    public List<Transaction> loadTransactionOutFromTo(Date begin, Date end) {
        clearTmpList();
        for(Transaction t : list){
            if(t.getCategory().getIncome() == 1) {
                if (t.getDate().getDay() >= begin.getDay() && t.getDate().getMonth() >= begin.getMonth() && t.getDate().getYear() >= begin.getYear()) {
                    if (t.getDate().getDay() <= end.getDay() && t.getDate().getMonth() <= end.getMonth() && t.getDate().getYear() <= end.getYear()) {
                        tmpList.add(t);
                    }
                }
            }
        }
        return tmpList;
    }

    public List<Transaction> loadTransactionAllFromTo(final Date begin, final Date end){
        clearTmpList();
        for(Transaction t : list){
            if(t.getDate().getDay() >= begin.getDay() && t.getDate().getMonth() >= begin.getMonth() && t.getDate().getYear() >= begin.getYear()){
                if(t.getDate().getDay() <= end.getDay() && t.getDate().getMonth() <= end.getMonth() && t.getDate().getYear() <= end.getYear()){
                    tmpList.add(t);
                }
            }
        }
        return tmpList;
    }

    public void addToList(Transaction transaction){
        list.add(transaction);
    }

    public void removeFromList(Transaction transaction){
        list.remove(transaction);
    }

    public void clearTmpList(){
        tmpList.clear();
    }
}
