package de.fh.mae.md2.app.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {
    private Calendar calendar;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        long dateTime = getArguments().getLong("DATE_TIME");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateTime);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }
}
