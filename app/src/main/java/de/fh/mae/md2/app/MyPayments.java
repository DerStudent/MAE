package de.fh.mae.md2.app;

import android.app.Application;
import android.content.res.Configuration;

public class MyPayments extends Application {
    private static final int fractionalDigits = 2;
    private static final String zero = "0";

    private static String separator;
    private static String currencySymbol;
    private static String defaultAmount;
    private static String todayText;

    private static boolean premium = false;
    private static boolean signedIn = false;

    private static String pin = "1234";

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
        todayText = getResources().getString(R.string.today);
    }

    public static String getDefaultFractionalDigitValue() {
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

    public static String getZero() {
        return zero;
    }

    public static int getFractionalDigits() {
        return fractionalDigits;
    }

    public static String getPin() {
        return pin;
    }

    public static void setPin(String pin) {
        MyPayments.pin = pin;
    }

    public static String getSeparator() {
        return separator;
    }

    public static String getCurrencySymbol() {
        return currencySymbol;
    }

    public static String getDefaultAmount() {
        return defaultAmount;
    }

    public static String getTodayText() {
        return todayText;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public static boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public static boolean isSignedIn() {
        return signedIn;
    }

    public static void signIn() {
        signedIn = true;
    }

    public static void signOut() {
        signedIn = false;
    }
}
