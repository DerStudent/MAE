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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.fh.mae.md2.app.Category.Category;
import de.fh.mae.md2.app.Category.CategoryHelper;
import de.fh.mae.md2.app.MyPayments;
import de.fh.mae.md2.app.R;
import de.fh.mae.md2.app.dialogs.DatePickerFragment;
import de.fh.mae.md2.app.enums.ICategroryType;
import de.fh.mae.md2.app.transaction.Transaction;
import de.fh.mae.md2.app.transaction.TransactionsHelper;

public class AddTransactionActivity extends AppCompatActivity implements View.OnClickListener, EditText.OnEditorActionListener, DatePickerDialog.OnDateSetListener {
    private int AMOUNT_REQUEST = 1;
    private int CATEGORY_REQUEST = 1;

    private String separator;
    private String currencySymbol;

    private long transactionId = -1L;
    private long categoryId = -1L;
    private Transaction transaction;

    private TextView textAmount;
    private TextView textCategory;
    private TextView textCalendar;

    private ImageView imageCategory;
    private EditText note;

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

        textAmount = (TextView) findViewById(R.id.text_add_transaction_amount);
        imageCategory = (ImageView) findViewById(R.id.image_add_transaction_category);
        textCategory = (TextView) findViewById(R.id.text_add_transaction_category);
        textCalendar = (TextView) findViewById(R.id.text_add_transaction_calendar);
        note = (EditText) findViewById(R.id.edit_add_transaction_note);
        note.setOnEditorActionListener(this);

        transactionId = getIntent().getLongExtra("TRANSACTION_ID", -1L);
        categoryId = getIntent().getLongExtra("CATEGORY_ID", -1L);

        if(hasCategoryId() && transaction != null) {
            Category category = CategoryHelper.getCategoryById(categoryId);
            if(category != null) {
                transaction.setCategory(category);
            }
        }

        if(hasTransactionId()) {
            showDeleteButton();

            transaction = TransactionsHelper.getTransactionById(transactionId);
            refresh();
        }

        if(transaction == null) {
            Category category = null;
            if(TransactionsHelper.hasTransactions()) {
                List<Transaction> tL = TransactionsHelper.getLastTransactions(1);
                if(!tL.isEmpty()) {
                    category = tL.get(0).getCategory();
                }
            } else {
                category = CategoryHelper.getFirstCategory();
            }

            transaction = new Transaction(MyPayments.getDefaultAmount(), category, "", MyPayments.getCustomCalendarInstance().getTime());
        }
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
        Button deleteTransaction = (Button) findViewById(R.id.delete_button);
        deleteTransaction.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_transaction) {
            saveTransaction();
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
            intent.putExtra("AMOUNT", transaction.getAmount());
            startActivityForResult(intent, AMOUNT_REQUEST);
        } else if (i == R.id.layout_add_transaction_category) {
            Intent intent = new Intent(AddTransactionActivity.this, AddTransactionAmountActivity.class);
            intent.putExtra("CATEGORY", transaction.getCategory().getId());
            startActivityForResult(intent, CATEGORY_REQUEST);
        } else if (i == R.id.layout_add_transaction_note) {
            EditText note = (EditText) findViewById(R.id.edit_add_transaction_note);
            note.setFocusableInTouchMode(true);
            note.requestFocus();
        } else if (i == R.id.layout_add_transaction_calendar) {
            Bundle datePickerBundle = new Bundle();
            datePickerBundle.putLong("DATE_TIME", transaction.getDate().getTime());

            DialogFragment datePicker = new DatePickerFragment();
            datePicker.setArguments(datePickerBundle);
            datePicker.show(getSupportFragmentManager(), "date picker");
        }else if(i == R.id.delete_button){
            TransactionsHelper.delete(transaction);
            setResult(RESULT_OK);
            finish();
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
            transaction.setAmount(data.getStringExtra("AMOUNT"));
        }

        refreshAmount();
    }

    private void refreshAmount() {
        textAmount.setText(transaction.getAmount() + " " + currencySymbol);
        if(transaction.getCategory().getType() == ICategroryType.INCOME) {
            textAmount.setTextColor(getResources().getColor(R.color.colorIncome));
        } else {
            textAmount.setTextColor(getResources().getColor(R.color.colorOutcome));
        }
    }

    private void refresh() {
        if (transaction != null) {
            refreshAmount();

            imageCategory.setImageDrawable(getResources().getDrawable(transaction.getCategory().getImage()));
            textCategory.setText(transaction.getCategory().getName());

            note.setText(transaction.getNote().toString());

            if(transaction.getDate().getTime() != MyPayments.getCustomCalendarInstance().getTime().getTime()) {
                textCalendar.setText(getFormattedDate(transaction.getDate()));
            } else {
                textCalendar.setText(MyPayments.getTodayText());
            }
        }
    }

    private void leaveEditText() {
        transaction.setNote(note.getText().toString());
        View view = getCurrentFocus();

        if(view instanceof EditText) {


            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = MyPayments.getCustomCalendarInstance();
        calendar.set(year, month, dayOfMonth);

        transaction.setDate(calendar.getTime());
        refresh();
    }

    private String getFormattedDate(Date date) {
        return DateFormat.getDateInstance(DateFormat.FULL).format(date);
    }

    private boolean hasTransactionId() {
        return transactionId >= 0;
    }

    private boolean hasCategoryId() {
        return categoryId >= 0;
    }

    private void showDeleteButton() {
        Button button = (Button) findViewById(R.id.delete_button);
        button.setVisibility(View.VISIBLE);
    }

    private void saveTransaction() {
        if(!hasTransactionId()) {
            TransactionsHelper.add(transaction);
        }
    }

}
