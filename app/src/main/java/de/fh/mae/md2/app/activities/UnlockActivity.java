package de.fh.mae.md2.app.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import de.fh.mae.md2.app.MyPayments;
import de.fh.mae.md2.app.Main;
import de.fh.mae.md2.app.R;

public class UnlockActivity extends AppCompatActivity implements View.OnClickListener{
    private AppCompatActivity activity;
    private String pin;
    private EditText txt;
    private ComponentName componentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock);

        txt = (EditText) findViewById(R.id.editText);
        txt.setFocusableInTouchMode(false);
        txt.setFocusable(false);
        txt.setClickable(true);

        componentName = this.getCallingActivity();

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
        Intent intent = new Intent(UnlockActivity.this, SettingsActivity.class);
        intent.putExtra("PIN", pin);
        if(componentName != null) {
            setResult(getIntent().getIntExtra("REQUEST", 0), intent);
            finish();

        }
        if(pin.equals(MyPayments.getPin()) && !MyPayments.getPin().equals("")){
            finish();
            Intent myIntent = new Intent(UnlockActivity.this, Main.class);
            startActivity(myIntent);
        }else{
            pin = "";
            refreshPin();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
//        else if (id == R.id.action_save_transaction_pin) {
//            //savePin();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

        switch (i) {
            case R.id.button_pin_backspace:
                removeCharacterFromPin();
                refreshPin();
                System.out.println(pin);
                break;
            case R.id.button_pin_check:
                System.out.println(pin);
                isPinCorrect(pin);
                break;
            default:
                addInputToPin(i);
                refreshPin();
                System.out.println(pin);
                break;
        }
    }

    private void refreshPin() {
        if(isPinEmpty()) {
            pin = "";
        }

        txt.setText(pin);
    }

    private String getButtonInputValue(int buttonId) {
        Button button = (Button) findViewById(buttonId);
        return String.valueOf(button.getText());
    }

    private void addInputToPin(int buttonId) {
        String inputValue = getButtonInputValue(buttonId);
        if(pin == null){
            pin = inputValue;
        }else {
            pin += inputValue;
        }
    }

    private void removeCharacterFromPin() {
        if (!isPinEmpty()) {
            pin = pin.substring(0, pin.length() - 1);
        }
    }

    private boolean isPinEmpty() {
        if(pin != null && pin.length() > 0) {
            return  false;
        }

        return true;
    }
}
