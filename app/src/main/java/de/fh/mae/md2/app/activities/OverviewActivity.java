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
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.fh.mae.md2.app.Category.Category;
import de.fh.mae.md2.app.Category.CategoryHelper;
import de.fh.mae.md2.app.MyPayments;
import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.enums.ICategroryType;
import de.fh.mae.md2.app.transaction.*;

public class OverviewActivity extends Fragment implements  View.OnClickListener {
    private FragmentActivity activity;
    private RecyclerView recyclerView;
    private Calendar cal = Calendar.getInstance();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();

        activity.setTitle(getString(R.string.overview_label));
        initOverview();
        setOnClickListeners();
        /*recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //Call your method here for next set of data
                }
            }
        });*/
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
            if (CategoryHelper.hasCategories()) {
                Intent myIntent = new Intent(activity, AddTransactionActivity.class);
                startActivity(myIntent);
            } else {
                Toast.makeText(activity, getResources().getString(R.string.add_transaction_create_category), Toast.LENGTH_LONG).show();
            }
        }
    }

    public Date getFirstMonth(){
        Date d = new Date();
        d.setYear(cal.get(Calendar.YEAR));
        d.setMonth(cal.get(Calendar.MONTH));
        d.setDate(1);
        return d;
    }

    public Date getLastMonth(){
        Date d = new Date();
        d.setYear(cal.get(Calendar.YEAR));
        d.setMonth(cal.get(Calendar.MONTH));
        d.setDate(cal.getActualMaximum(cal.get(Calendar.MONTH)));
        return d;
    }

    public List<Transaction> getMonthlyTransaction(){
        Calendar calendar = MyPayments.getCustomCalendarInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date from = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date to = calendar.getTime();

        return TransactionsHelper.getTransactionsFromTo(from, to);
    }

    public void initOverview() {

        recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_overview);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        List<Transaction> monthlyTransactions = getMonthlyTransaction();
        Calendar calendar = Calendar.getInstance();
        DateFormat df = DateFormat.getDateInstance();
        Date to = calendar.getTime();

        //adding some items to our list
        //TransactionsHelper.add(new Transaction("100", new CategoryHelper().getFirstCategory(), "Test", calendar.getTime()));

        Transaction t = new Transaction("100", CategoryHelper.getFirstCategory(), "", MyPayments.getCustomCalendarInstance().getTime());

        TransactionsHelper.add(t);

        //creating recyclerview adapter
        TransactionAdapter adapter = new TransactionAdapter(activity, monthlyTransactions);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
