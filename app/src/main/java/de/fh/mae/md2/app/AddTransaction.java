package de.fh.mae.md2.app;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AddTransaction extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_transaction);

        View current = getCurrentFocus();
        if (current != null) {
            current.clearFocus();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setOnClickListeners();

        TextView amountText = (TextView) findViewById(R.id.transaction_amount_text);
        String currency = String.format(getResources().getString(R.string.add_transaction_currency));
        amountText.setText("0,00 " + currency);

        TextView categoryText = (TextView) findViewById(R.id.transaction_category_text);
        categoryText.setText("Supermarkt");

        TextView calendarText = (TextView) findViewById(R.id.transaction_calendar_text);
        calendarText.setText("Heute");
    }

    private void setOnClickListeners() {
        RelativeLayout addTransactionAmount = (RelativeLayout) findViewById(R.id.layout_add_transaction_amount);
        addTransactionAmount.setOnClickListener(this);
        RelativeLayout addTransactionCategory = (RelativeLayout) findViewById(R.id.layout_add_transaction_category);
        addTransactionCategory.setOnClickListener(this);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.action_floatingActionButton);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_transaction, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

        if (i == R.id.layout_add_transaction_amount) {

        } else if (i == R.id.layout_add_transaction_category) {

        }
    }

}
