package de.fh.mae.md2.app;

import android.app.Application;
import android.content.res.Configuration;

public class MyPayments extends Application {
    private static final int fractionalDigits = 2;
    private static final String zero = "0";
    private String separator;
    private String currencySymbol;
    private String defaultAmount;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        if (separator == null) {
            setSeparator(String.format(getResources().getString(R.string.separatorComma)));
        }
        if (currencySymbol == null) {
            setCurrencySymbol(String.format(getResources().getString(R.string.currencySymbolEuro)));
        }

        defaultAmount = zero + separator + getDefaultFractionalDigitValue();
    }

    public String getDefaultFractionalDigitValue() {
        String value = "";
        for(int i = 0; i < fractionalDigits; i++) {
            value += zero;
        }

        return value;
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public  String getZero() {
        return zero;
    }

    public int getFractionalDigits() {
        return fractionalDigits;
    }

    public String getSeparator() {
        return separator;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public String getDefaultAmount() {
        return defaultAmount;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

}
