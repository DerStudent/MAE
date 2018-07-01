package de.fh.mae.md2.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import de.fh.mae.md2.app.transaction.*;

public class OverviewActivity extends Fragment implements  View.OnClickListener{
    private FragmentActivity activity;
    List<Transaction> transactionList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();

        getActivity().setTitle(getString(R.string.overview_label));
        initOverview();
        setOnClickListeners();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_overview, container, false);
    }

    private void setOnClickListeners() {
        FloatingActionButton buttonFloatingMain = (FloatingActionButton) activity.findViewById(R.id.button__floating_main);
        if(buttonFloatingMain != null) {
            buttonFloatingMain.setVisibility(View.VISIBLE);
            buttonFloatingMain.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

        if (i == R.id.button__floating_main) {
            Intent myIntent = new Intent(activity, AddTransactionActivity.class);
            startActivity(myIntent);
        }
    }

    public void initOverview() {

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_overview);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


        //initializing the productlist
        transactionList = new ArrayList<Transaction>();
        Calendar cal = Calendar.getInstance();

        //adding some items to our list

        //creating recyclerview adapter
        TransactionAdapter adapter = new TransactionAdapter(activity, transactionList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
