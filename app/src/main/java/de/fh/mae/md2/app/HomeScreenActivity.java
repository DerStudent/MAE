package de.fh.mae.md2.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {
    ListView listView ;
    List<Transaction> transactionList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        /* Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[] { "+ 2000€ Gehalt",
                "- 900€ Miete",
                "- 400€ Fixkosten",
                "- 100€ Einkaufen",
                "- 100€ Wochenend Ausgaben"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int itemPosition     = position;

                String  itemValue    = (String) listView.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(),"Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG).show();
            }
        });*/

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
                        new Category("Fast Food"),
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
