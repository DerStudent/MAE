package de.fh.mae.md2.app.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import de.fh.mae.md2.app.MyPayments;
import de.fh.mae.md2.app.R;

public class AddTransactionAmountActivity extends AppCompatActivity implements View.OnClickListener {

    private String amount;
    private String separator;
    private String currencySymbol;

    private TextView textAmount;

    private ComponentName componentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction_amount);
        init();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private  void init() {
        componentName = this.getCallingActivity();

        currencySymbol = MyPayments.getCurrencySymbol();
        separator = MyPayments.getSeparator();

        Button separatorButton = (Button) findViewById(R.id.button_add_transaction_amount_separator);
        separatorButton.setText(separator);

        textAmount = (TextView) findViewById(R.id.text_add_transaction_amount_amount);
        amount = getIntent().getStringExtra("AMOUNT");
        if(isAmountEmpty()) {
            amount = MyPayments.getDefaultAmount();
        }

        refreshAmount();
        setOnClickListeners();

    }

    private void setOnClickListeners() {
        ViewGroup group = (ViewGroup) findViewById(R.id.layout_add_transaction_amount_input);
        View view;
        for(int i = 0; i < group.getChildCount(); i++) {
            view = group.getChildAt(i);
            if(view instanceof Button || view instanceof ImageButton) {
                view.setOnClickListener(this);
            }
        }
    }

    private void saveAmount() {
        if(isAmountEmpty()) {
            amount = MyPayments.getDefaultAmount();
        } else if(containsSeparator()) {
            String missingFractionalDigits = "";
            for(int i = (amount.length() - 1 - amount.indexOf(separator)); i < MyPayments.getFractionalDigits(); i++) {
                missingFractionalDigits += MyPayments.getZero();
            }

            amount += missingFractionalDigits;
        } else {
            amount += separator + MyPayments.getDefaultFractionalDigitValue();
        }

        Intent intent = new Intent(AddTransactionAmountActivity.this, AddTransactionActivity.class);
        intent.putExtra("AMOUNT", amount);
        if(componentName != null) {
            setResult(getIntent().getIntExtra("AMOUNT_REQUEST", -1), intent);
        }

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_transaction_amount, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

        switch (i) {
            case R.id.button_add_transaction_amount_backspace:
                removeCharacterFromAmount();
                break;
            case R.id.button_add_transaction_amount_check:
                saveAmount();
                break;
            case R.id.button_add_transaction_amount_separator:
                addSeparator();
                break;
            default:
                addInputToAmount(i);
                break;
        }

        refreshAmount();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (id == R.id.action_save_transaction_amount) {
            saveAmount();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean containsSeparator() {
        if(amount.contains(separator)) {
            return true;
        }

        return false;
    }

    private void addSeparator() {
        if(!containsSeparator()) {
            amount += separator;
        }
    }

    private String getButtonInputValue(int buttonId) {
        Button button = (Button) findViewById(buttonId);
        return String.valueOf(button.getText());
    }

    private void addInputToAmount(int buttonId) {
        String inputValue = getButtonInputValue(buttonId);
        if(containsSeparator() && !MyPayments.getDefaultAmount().equals(amount)) {
            int separatorPosition = amount.indexOf(separator);
            if(separatorPosition + MyPayments.getFractionalDigits() < amount.length()) {
                return;
            }
        }

        if(amount.equals(MyPayments.getDefaultAmount())) {
            amount = "";
        }

        amount += inputValue;
    }

    private void refreshAmount() {
        if(isAmountEmpty()) {
            amount = MyPayments.getDefaultAmount();
        }

        removeLeadingZeros();
        textAmount.setText(amount + " " + currencySymbol);
    }

    private void removeLeadingZeros() {
        String zero = MyPayments.getZero();

        while(amount.length() > 1 && amount.startsWith(zero) && amount.charAt(1) != separator.charAt(0)) {
            amount = amount.substring(1, amount.length());
        }
    }

    private void removeCharacterFromAmount() {
        if (!isAmountEmpty()) {
            amount = amount.substring(0, amount.length() - 1);
        }
    }

    private boolean isAmountEmpty() {
        if(amount != null && amount.length() > 0) {
            return  false;
        }

        return true;
    }
}
