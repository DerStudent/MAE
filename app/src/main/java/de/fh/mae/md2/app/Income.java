package de.fh.mae.md2.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

public class Income extends AppCompatActivity {

    private TextView mTextMessage;

    List<Transaction> transactionList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einnahmen);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        transactionList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();


        //adding some items to our list
        cal.set(2018,5,28);
        transactionList.add(
                new Transaction(
                        -100,
                        new Category("Fast Food Fast Food Fast Food Fast Food"),
                        cal.getTime(),
                        R.drawable.category_fastfood));

        transactionList.add(
                new Transaction(
                        -20,
                        new Category("Transport"),
                        cal.getTime(),
                        R.drawable.category_transport));

        transactionList.add(
                new Transaction(
                        -8,
                        new Category("Movie"),
                        cal.getTime(),
                        R.drawable.category_localmovie));

        transactionList.add(
                new Transaction(
                        -23.56,
                        new Category("Supermarket"),
                        cal.getTime(),
                        R.drawable.category_store));

        transactionList.add(
                new Transaction(
                        -30,
                        new Category("Restaurant"),
                        cal.getTime(),
                        R.drawable.category_restaurant));

        //creating recyclerview adapter
        TransactionAdapter adapter = new TransactionAdapter(this, transactionList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}