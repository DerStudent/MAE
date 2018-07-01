package de.fh.mae.md2.app.activities;

import android.content.ComponentName;
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

import de.fh.mae.md2.app.MyPayments;
import de.fh.mae.md2.app.R;

public class AddTransactionActivity extends AppCompatActivity implements View.OnClickListener {
    private int AMOUNT_REQUEST = 1;

    private String separator;
    private String currencySymbol;
    private String amount;

    private TextView textAmount;
    private TextView textCategory;
    private TextView textCalendar;

    private ImageView imageCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setOnClickListeners();

        init();
    }

    private void init() {
        currencySymbol = MyPayments.getCurrencySymbol();
        separator = MyPayments.getSeparator();
        amount = MyPayments.getDefaultAmount();

        imageCategory = (ImageView) findViewById(R.id.image_add_transaction_category);
        imageCategory.setImageDrawable(getResources().getDrawable(R.drawable.ic_category_icon_1));

        textAmount = (TextView) findViewById(R.id.text_add_transaction_amount);

        textCategory = (TextView) findViewById(R.id.text_add_transaction_category);
        textCategory.setText("Supermarkt");

        textCalendar = (TextView) findViewById(R.id.text_add_transaction_calendar);
        textCalendar.setText("Heute");

        refresh();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_transaction) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            Intent intent = new Intent(AddTransactionActivity.this, AddTransactionAmountActivity.class);
            intent.putExtra("REQUEST", AMOUNT_REQUEST);
            intent.putExtra("AMOUNT", amount);
            startActivityForResult(intent, AMOUNT_REQUEST);
        } else if (i == R.id.layout_add_transaction_category) {

        } else if (i == R.id.layout_add_transaction_note) {
            EditText note = (EditText) findViewById(R.id.edit_add_transaction_note);
            note.setFocusableInTouchMode(true);
            note.requestFocus();
        } else if (i == R.id.layout_add_transaction_calendar) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == AMOUNT_REQUEST) {
            amount = data.getStringExtra("AMOUNT");
        }

        refresh();
    }

    private void refresh() {
        textAmount.setText(amount + " " + currencySymbol);
    }
}
