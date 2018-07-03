package de.fh.mae.md2.app.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.*;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import de.fh.mae.md2.app.dao.CategoryDao;
import de.fh.mae.md2.app.dao.TransactionDao;
import de.fh.mae.md2.app.entities.Category;
import de.fh.mae.md2.app.entities.Transaction;
import de.fh.mae.md2.app.typeConverter.Converters;

@Database(entities = {Transaction.class, Category.class}, version = 1)
@TypeConverters({Converters.class})

public abstract class AppDatabase extends RoomDatabase {

    public abstract TransactionDao transactionDao();

    public abstract CategoryDao categoryDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "MyPaymentsDatabase")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CategoryDao mDao;
        Category[] categories = Category.populateData();

        PopulateDbAsync(AppDatabase db) {
            mDao = db.categoryDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();

            for (int i = 0; i <= categories.length - 1; i++) {
                Category category = categories[i];
                mDao.insertCategory(category);
            }
            return null;
        }
    }
}