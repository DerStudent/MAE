package de.fh.mae.md2.app.database;

import android.arch.persistence.room.*;
import android.content.Context;

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
                            AppDatabase.class, "word_database")
                            .build();

                }
            }
        }
        return INSTANCE;
    }

}
