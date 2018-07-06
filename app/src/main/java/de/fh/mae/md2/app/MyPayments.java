package de.fh.mae.md2.app;

import android.app.Application;
import android.content.res.Configuration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.fh.mae.md2.app.Category.Category;
import de.fh.mae.md2.app.Category.CategoryHelper;
import de.fh.mae.md2.app.transaction.Transaction;

public class MyPayments extends Application {
    private static final int fractionalDigits = 2;
    private static final String zero = "0";

    private static String separator;
    private static String currencySymbol;
    private static String defaultAmount;
    private static String todayText;

    private static boolean premium = false;
    private static boolean signedIn = false;

    private static String pin ="";

    private static List<Transaction> transactionList;
    private static List<Category> categoryList;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        if (transactionList == null) {
            transactionList = new ArrayList<>();
        }

        if (categoryList == null) {
            categoryList = new ArrayList<>();
        }

        if (separator == null) {
            setSeparator(String.format(getResources().getString(R.string.separatorComma)));
        }
        if (currencySymbol == null) {
            setCurrencySymbol(String.format(getResources().getString(R.string.currencySymbolEuro)));
        }

        defaultAmount = zero + separator + getDefaultFractionalDigitValue();
        todayText = getResources().getString(R.string.today);

        transactionList.add(new Transaction("100,00", CategoryHelper.getFirstCategory(), "", MyPayments.getCustomCalendarInstance().getTime()));
    }

    public static String getDefaultFractionalDigitValue() {
        String value = "";
        for(int i = 0; i < fractionalDigits; i++) {
            value += zero;
        }

        return value;
    }

    public static Calendar getCustomCalendarInstance() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;
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

    public static List getTransactionList(){
        return transactionList;
    }

    public static List getCategoryList(){
        return categoryList;
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

    public static void setPremium(boolean premium) {
        MyPayments.premium = premium;
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
