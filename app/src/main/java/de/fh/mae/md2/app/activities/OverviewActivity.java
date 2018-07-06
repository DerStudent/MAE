package de.fh.mae.md2.app.activities;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
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
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
    private TextView incomeAmount;
    private TextView outcomeAmount;
    private TextView totalAmount;


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

    @Override
    public void onStart() {

        super.onStart();
        initOverview();
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

    public List<Transaction> getMonthlyTransaction(){
        Calendar calendar = MyPayments.getCustomCalendarInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date from = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date to = calendar.getTime();

        return TransactionsHelper.getTransactionsFromTo(from, to);
    }

    public List<Transaction> getMonthlyTransactionType(Integer type){
        Calendar calendar = MyPayments.getCustomCalendarInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date from = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date to = calendar.getTime();

        return TransactionsHelper.getTransactionsFromToByType(from, to, type);
    }

    // 0 = Income, 1 = Outcome
    public void calculateCredit(){
        List<Transaction> tmp = getMonthlyTransaction();
        double income = 0;
        double outcome = 0;

        for(Transaction i : tmp){
            if(i.getCategory().getType() == 0) {
                income += Double.parseDouble(i.getAmount().replace(",", "."));
            }else{
                outcome += Double.parseDouble(i.getAmount().replace(",", "."));
            }
        }
        incomeAmount.setText(numberWithOnAfterSeperator(String.valueOf(income).replace(".", ",")));
        outcomeAmount.setText(numberWithOnAfterSeperator(String.valueOf(outcome).replace(".", ",")));

        totalAmount.setText(numberWithOnAfterSeperator(String.valueOf(income-outcome).replace(".", ",")));
    }

    public String numberWithOnAfterSeperator(String tmp){
        int after = -1;
        boolean b = false;
        for(int i = 0; i < tmp.length(); i++){
            if(tmp.charAt(i) == '.' || tmp.charAt(i) == ','){
                b = true;
            }
            if(b){
                after++;
            }
        }
        if(after == 1){
            return tmp + "0";
        }
        return tmp;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if(data == null) {
            return;
        }
    }

    public void initOverview() {

        incomeAmount = (TextView) activity.findViewById(R.id.text_overview_income_value);
        outcomeAmount = (TextView) activity.findViewById(R.id.text_overview_outcome_value);
        totalAmount = (TextView) activity.findViewById(R.id.text_overview_total_value);

        recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_overview);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        List<Transaction> monthlyTransactions = getMonthlyTransaction();

        //creating recyclerview adapter
        TransactionAdapter adapter = new TransactionAdapter(activity, monthlyTransactions);

        calculateCredit();

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
