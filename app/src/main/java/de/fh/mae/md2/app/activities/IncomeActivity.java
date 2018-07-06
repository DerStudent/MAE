package de.fh.mae.md2.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.fh.mae.md2.app.MyPayments;
import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.enums.ICategroryType;
import de.fh.mae.md2.app.transaction.Transaction;
import de.fh.mae.md2.app.transaction.TransactionAdapter;
import de.fh.mae.md2.app.transaction.TransactionsHelper;

public class IncomeActivity extends Fragment {

    private FragmentActivity activity;
    List<Transaction> transactionList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();

        getActivity().setTitle(getString(R.string.income_label));
        initIncome();
    }

    @Override
    public void onStart() {

        super.onStart();
        initIncome();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_income, container, false);
    }

    private void initIncome() {
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_income);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        //initializing the productlist
        List<Transaction> incomeTransactions = TransactionsHelper.getAllTransactions(ICategroryType.INCOME);

        //creating recyclerview adapter
        TransactionAdapter adapter = new TransactionAdapter(activity, incomeTransactions);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}