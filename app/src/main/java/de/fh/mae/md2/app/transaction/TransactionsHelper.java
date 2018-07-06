package de.fh.mae.md2.app.transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import de.fh.mae.md2.app.MyPayments;

public class TransactionsHelper {

    private static List<Transaction> list;

    static {
        list = MyPayments.getTransactionList();
    }

    public static Transaction getTransactionById(long id) {
        if(list.isEmpty()) {
            return null;
        }

        for(Transaction t : list) {
            if(t.getId() == id) {
                return t;
            }
        }

        return null;
    }

    public static List<Transaction> getLastTransactions(int count) {
        if(list.size() <= count) {
            return list;
        }
        
        List<Transaction> tmpList = new ArrayList<>();
        for(Transaction t : list.subList(list.size() - count, list.size())){
            tmpList.add(t);
        }

        return tmpList;
    }

    public static List<Transaction> getTransactionsFromTo(Date from, Date to) {
        return getTransactionsFromToByType(from, to, null);
    }

    public static List<Transaction> getTransactionsFromToByType(Date from, Date to, Integer type) {
        List<Transaction> tmpList = new ArrayList<>();
        if(from == null || to == null) {
            return tmpList;
        }

        Date tDate;
        for(Transaction t : list){
            tDate = t.getDate();

            if(type == null || t.getCategory().getType() == type) {
                if(tDate != null && tDate.getTime() >= from.getTime() && tDate.getTime() <= to.getTime()) {
                    tmpList.add(t);
                }
            }
        }

        Collections.sort(tmpList);
        return tmpList;
    }

    public static void add(Transaction transaction){
        list.add(transaction);
    }

    public static void delete(Transaction transaction){
        list.remove(transaction);
    }

    public static boolean hasTransactions(){
        return !list.isEmpty();
    }
}
