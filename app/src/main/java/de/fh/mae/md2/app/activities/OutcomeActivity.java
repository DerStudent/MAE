package de.fh.mae.md2.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.transaction.Transaction;
import de.fh.mae.md2.app.transaction.Category;
import de.fh.mae.md2.app.transaction.TransactionAdapter;

public class OutcomeActivity extends Fragment {

    private FragmentActivity activity;
    List<Transaction> transactionList;

    //the recyclerview
    RecyclerView recyclerView;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();

        getActivity().setTitle(getString(R.string.outcome_label));
        initOutcome();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_outcome, container, false);
    }

    private void initOutcome() {
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_outcome);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


        //initializing the productlist
        transactionList = new ArrayList<Transaction>();
        Calendar cal = Calendar.getInstance();

        //adding some items to our list
        cal.set(2018,5,5);
        transactionList.add(
            new Transaction(
                -100,
                new Category("Fast Food"),
                cal.getTime(),
                R.drawable.ic_category_fastfood));

        transactionList.add(
            new Transaction(
                -20,
                new Category("Transport"),
                cal.getTime(),
                R.drawable.ic_category_transport));

        transactionList.add(
            new Transaction(
                -8,
                new Category("Movie"),
                cal.getTime(),
                R.drawable.ic_category_localmovie));

        transactionList.add(
            new Transaction(
                -23.56,
                new Category("Supermarket"),
                cal.getTime(),
                R.drawable.ic_category_store));

        transactionList.add(
            new Transaction(
                -30,
                new Category("Restaurant"),
                cal.getTime(),
                R.drawable.ic_category_restaurant));

        transactionList.add(
                new Transaction(
                        -15,
                        new Category("Filme"),
                        cal.getTime(),
                        R.drawable.ic_category_ondemandvideo));

        transactionList.add(
                new Transaction(
                        -2,
                        new Category("WC"),
                        cal.getTime(),
                        R.drawable.ic_category_wc));

        //creating recyclerview adapter
        TransactionAdapter adapter = new TransactionAdapter(activity, transactionList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}