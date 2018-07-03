
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
import android.widget.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.entities.Transaction;
import de.fh.mae.md2.app.transaction.TransactionAdapter;

public class CalendarActivity extends Fragment {

    private FragmentActivity activity;
    List<Transaction> transactionList;

    //the recyclerview
    RecyclerView recyclerView;

    CalendarView calendarView;

    Date today;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();
        calendarView = (CalendarView) activity.findViewById(R.id.calendar);
        setCalendarToday(calendarView);
        getActivity().setTitle(getString(R.string.calendar_label));
        initCalendar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_calendar, container, false);
    }

    private void initCalendar() {
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_calendar);
        LinearLayoutManager manager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);


        //initializing the productlist
        transactionList = new ArrayList<Transaction>();

        //creating recyclerview adapter
        TransactionAdapter transactionAdapter = new TransactionAdapter(activity, transactionList);

        //setting adapter to recyclerview
        recyclerView.setAdapter();
    }


    private void setCalendarToday(CalendarView calendarView){
        today = Calendar.getInstance().getTime();
        calendarView.setDate(today.getTime());
    }
}
