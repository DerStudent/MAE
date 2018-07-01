package de.fh.mae.md2.app.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import de.fh.mae.md2.app.MyPayments;
import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.dialogs.DatePickerFragment;

public class AddTransactionActivity extends AppCompatActivity implements View.OnClickListener, EditText.OnEditorActionListener, DatePickerDialog.OnDateSetListener {
    private int AMOUNT_REQUEST = 1;

    private String separator;
    private String currencySymbol;
    private String amount;

    private TextView textAmount;
    private TextView textCategory;
    private TextView textCalendar;

    private Date dateCalendar;

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
        imageCategory.setImageDrawable(getResources().getDrawable(R.drawable.ic_category_store));

        textAmount = (TextView) findViewById(R.id.text_add_transaction_amount);

        textCategory = (TextView) findViewById(R.id.text_add_transaction_category);
        textCategory.setText("Supermarkt");

        EditText note = (EditText) findViewById(R.id.edit_add_transaction_note);
        note.setOnEditorActionListener(this);

        textCalendar = (TextView) findViewById(R.id.text_add_transaction_calendar);
        textCalendar.setText(MyPayments.getTodayText());
        dateCalendar = getCustomCalendarInstance().getTime();

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

        leaveEditText();

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
            Bundle datePickerBundle = new Bundle();
            datePickerBundle.putLong("DATE_TIME", dateCalendar.getTime());

            DialogFragment datePicker = new DatePickerFragment();
            datePicker.setArguments(datePickerBundle);
            datePicker.show(getSupportFragmentManager(), "date picker");
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId==EditorInfo.IME_ACTION_DONE){
           leaveEditText();
           return true;
        }

        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if(data == null) {
            return;
        }

        if (requestCode == AMOUNT_REQUEST) {
            amount = data.getStringExtra("AMOUNT");
        }

        refresh();
    }

    private void refresh() {
        textAmount.setText(amount + " " + currencySymbol);
    }

    private void leaveEditText() {
        View view = getCurrentFocus();

        if(view instanceof EditText) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }

    private Calendar getCustomCalendarInstance() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String currentDateText = MyPayments.getTodayText();
        Calendar calendar = getCustomCalendarInstance();

        Date currentDateTime = calendar.getTime();
        dateCalendar = currentDateTime;

        calendar.set(year, month, dayOfMonth);
        Date calendarDateTime = calendar.getTime();

        if(currentDateTime.getTime() != calendarDateTime.getTime()) {
            dateCalendar = calendarDateTime;
            currentDateText = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        }

        textCalendar.setText(currentDateText);
    }
}
