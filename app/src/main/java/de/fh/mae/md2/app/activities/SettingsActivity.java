package de.fh.mae.md2.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import de.fh.mae.md2.app.MyPayments;
import de.fh.mae.md2.app.R;

public class SettingsActivity extends Fragment implements  View.OnClickListener{

    private int PIN_REQUEST = 1;
    private FragmentActivity activity;
    private String pin;

    @Override
    public void onClick(View view) {
        int i = view.getId();

        if (i == R.id.pin_switch_settings) {
            Switch tmp = (Switch) activity.findViewById(R.id.pin_switch_settings);
            if (MyPayments.getPin().equals("")) {
                Intent intent = new Intent(activity, UnlockActivity.class);
                intent.putExtra("PIN", MyPayments.getPin());
                startActivityForResult(intent, PIN_REQUEST);
            }else{
                MyPayments.setPin("");
            }
        }else if (i == R.id.premium_switch_settings) {
            Switch tmp = (Switch) activity.findViewById(R.id.pin_switch_settings);
            if (MyPayments.isPremium()) {
                MyPayments.setPremium(false);
                tmp.setEnabled(false);
            } else if (!MyPayments.isPremium()) {
                MyPayments.setPremium(true);
                tmp.setEnabled(true);
                if (tmp.isEnabled() && !MyPayments.getPin().equals("")) {
                    tmp.setChecked(true);
                }
            }
        }
    }

    @Override
    public void onStart() {

        super.onStart();
        init();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = getActivity();

        getActivity().setTitle("Einstellungen");
        if(!MyPayments.isPremium()){
            Switch tmp = (Switch) activity.findViewById(R.id.pin_switch_settings);
            tmp.setEnabled(false);
        }
        init();
        setOnClickListeners();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_settings, container, false);
    }

    public void setOnClickListeners(){
        Switch pinSwitch = (Switch) activity.findViewById(R.id.pin_switch_settings);
        pinSwitch.setOnClickListener(this);
        Switch premiumSwitch = (Switch) activity.findViewById(R.id.premium_switch_settings);
        premiumSwitch.setOnClickListener(this);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if(data == null) {
            return;
        }

        if (requestCode == PIN_REQUEST) {
            pin = data.getStringExtra("PIN");
        }

        MyPayments.setPin(pin);
    }

    public void init(){
        if(MyPayments.isPremium()){
            Switch premiumSwitch = (Switch) activity.findViewById(R.id.premium_switch_settings);
            Switch pinSwitch = (Switch) activity.findViewById(R.id.pin_switch_settings);
            premiumSwitch.setChecked(true);

            if(MyPayments.getPin() != null && !MyPayments.getPin().equals("") && MyPayments.isPremium()){
                pinSwitch.setChecked(true);
            }else{
                pinSwitch.setChecked(false);
            }
        }
    }
}
