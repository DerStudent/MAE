package de.fh.mae.md2.app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.fh.mae.md2.app.R;

public class AddTransactionActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setOnClickListeners();

        ImageView imageCategory = (ImageView) findViewById(R.id.image_add_transaction_category);
        imageCategory.setImageDrawable(getResources().getDrawable(R.drawable.ic_category_store));

        TextView textAmount = (TextView) findViewById(R.id.text_add_transaction_amount);
        String currency = String.format(getResources().getString(R.string.add_transaction_currency));
        textAmount.setText("0,00 " + currency);

        TextView textCategory = (TextView) findViewById(R.id.text_add_transaction_category);
        textCategory.setText("Supermarkt");

        TextView textCalendar = (TextView) findViewById(R.id.text_add_transaction_calendar);
        textCalendar.setText("Heute");
    }

    private void setOnClickListeners() {
        RelativeLayout addTransactionAmount = (RelativeLayout) findViewById(R.id.layout_add_transaction_amount);
        addTransactionAmount.setOnClickListener(this);
        RelativeLayout addTransactionCategory = (RelativeLayout) findViewById(R.id.layout_add_transaction_category);
        addTransactionCategory.setOnClickListener(this);
        RelativeLayout addTransactionNote = (RelativeLayout) findViewById(R.id.layout_add_transaction_note);
        addTransactionNote.setOnClickListener(this);
        RelativeLayout addTransactionCalendar = (RelativeLayout) findViewById(R.id.layout_add_transaction_calendar);
        addTransactionCalendar.setOnClickListener(this);
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
            Intent myIntent = new Intent(AddTransactionActivity.this, AddTransactionAmountActivity.class);
            startActivity(myIntent);
        } else if (i == R.id.layout_add_transaction_category) {

        } else if (i == R.id.layout_add_transaction_note) {
            EditText note = (EditText) findViewById(R.id.edit_add_transaction_note);
            note.setFocusableInTouchMode(true);
            note.requestFocus();
        } else if (i == R.id.layout_add_transaction_calendar) {

        }
    }

}
