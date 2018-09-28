package com.androiddeft.jsonretrofit.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.androiddeft.jsonretrofit.Models.Discon_data;
import com.androiddeft.jsonretrofit.Models.Recon_data;
import com.androiddeft.jsonretrofit.Values.FunctionCall;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.io.IOException;

public class DataHelper extends OrmLiteSqliteOpenHelper {
    private SQLiteDatabase myDataBase;
    static FunctionCall fcall = new FunctionCall();
    //    public final Context myContext;
    private static final String DATABASE_NAME = "discon_recon.db";
    static String path = fcall.filepath("database");
    public final static String DATABASE_PATH = path + File.separator;
    public static final int DATABASE_VERSION = 1;
    private Dao<Discon_data, Integer> disconDao;
    private Dao<Recon_data, Integer> reconDao;


    //Constructor
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        getWritableDatabase();
    }

    public void openDatabase() {
        myDataBase = this.getWritableDatabase();
    }
    //Open database
//    public void openDatabase() throws SQLException {
//        String myPath = DATABASE_PATH + DATABASE_NAME;
//        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
//    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Discon_data.class);
            TableUtils.createTable(connectionSource, Recon_data.class);

        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource, Discon_data.class, true);
            TableUtils.dropTable(connectionSource, Recon_data.class, true);
            onCreate(database, connectionSource);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Discon_data, Integer> getDisconDao() throws java.sql.SQLException {
        if (disconDao == null) {
            disconDao = getDao(Discon_data.class);
        }
        return disconDao;
    }

    public Dao<Recon_data, Integer> getReconDao() throws java.sql.SQLException {
        if (reconDao == null) {
            reconDao = getDao(Recon_data.class);
        }
        return reconDao;
    }
}
