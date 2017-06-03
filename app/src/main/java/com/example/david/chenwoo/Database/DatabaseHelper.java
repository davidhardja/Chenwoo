package com.example.david.chenwoo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.david.chenwoo.Common.Constant;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by David on 31/03/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private ArrayList<Class> classes = new ArrayList<>();

    public DatabaseHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);

        //Add new class here for table
        classes = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        createAllTable(connectionSource);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        resetDatabase();
    }

    public void resetDatabase() {
        dropAllTable(getConnectionSource());
        createAllTable(getConnectionSource());
    }

    private void createAllTable(ConnectionSource connectionSource) {
        for (Class c : classes) {
            if (c != null) {
                try {
                    TableUtils.createTableIfNotExists(connectionSource, c);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void dropAllTable(ConnectionSource connectionSource) {
        for (Class c : classes) {
            if (c != null) {
                try {
                    TableUtils.dropTable(connectionSource, c, true);
                } catch (SQLException e) {
                }
            }

        }
    }

    @Override
    public void close() {
        super.close();
    }
}
