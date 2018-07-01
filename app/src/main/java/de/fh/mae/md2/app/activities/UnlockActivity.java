package de.fh.mae.md2.app.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import de.fh.mae.md2.app.MyPayments;
import de.fh.mae.md2.app.R;

public class UnlockActivity extends AppCompatActivity implements View.OnClickListener{
    private AppCompatActivity activity;
    private String amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock);
;
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        ViewGroup group = (ViewGroup) findViewById(R.id.layout_pin_input);
        View view;
        for(int i = 0; i < group.getChildCount(); i++) {
            view = group.getChildAt(i);
            if(view instanceof Button || view instanceof ImageButton) {
                view.setOnClickListener(this);
            }
        }
    }

    public void isPinCorrect(String pin){
        Fragment fragment = new OverviewActivity();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.containe); // fragmen container id in first parameter is the  container(Main layout id) of Activity
        transaction.addToBackStack(null);  // this will manage backstack
        transaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        } else if (id == R.id.action_save_transaction_amount) {
            //saveAmount();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

        switch (i) {
            case R.id.button_pin_backspace:
                removeCharacterFromAmount();
                break;
            case R.id.button_pin_check:
                Intent myIntent = new Intent(this, OverviewActivity.class);
                startActivity(myIntent);
                break;
            default:
                addInputToAmount(i);
                break;
        }
    }

    private String getButtonInputValue(int buttonId) {
        Button button = (Button) findViewById(buttonId);
        return String.valueOf(button.getText());
    }

    private void addInputToAmount(int buttonId) {
        String inputValue = getButtonInputValue(buttonId);
        if(!MyPayments.getDefaultAmount().equals(amount)) {
            //if(MyPayments.getFractionalDigits() < amount.length()) {   //------------------------------
                return;
            //}
        }

        if(amount.equals(MyPayments.getDefaultAmount())) {
            amount = "";
        }

        amount += inputValue;
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
