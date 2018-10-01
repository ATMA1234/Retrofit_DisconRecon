package com.androiddeft.jsonretrofit.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.androiddeft.jsonretrofit.Models.Discon_data;
import com.androiddeft.jsonretrofit.Models.DisconnectionList;
import com.androiddeft.jsonretrofit.Models.Recon_data;
import com.androiddeft.jsonretrofit.Values.FunctionCall;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static SQLiteDatabase myDataBase;
    private static FunctionCall fcall = new FunctionCall();
    private static final String DATABASE_NAME = "discon_recon.db";
    private static String path = fcall.filepath("database");
    private final static String DATABASE_PATH = path + File.separator;
    private static final int DATABASE_VERSION = 1;
    private Dao<Discon_data, Integer> disconDao;
    private Dao<Recon_data, Integer> reconDao;

    //****************************************************************************************************************************************
    public DatabaseHelper(Context context) {
        super(context, DATABASE_PATH + DATABASE_NAME, null, DATABASE_VERSION);
    }

    //****************************************************************************************************************************************
    public void openDatabase() throws SQLException {
        myDataBase = this.getWritableDatabase();
    }

    //****************************************************************************************************************************************
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Discon_data.class);
            TableUtils.createTable(connectionSource, Recon_data.class);
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //****************************************************************************************************************************************
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

//        try {
//            TableUtils.dropTable(connectionSource, Discon_data.class, true);
//            TableUtils.dropTable(connectionSource, Recon_data.class, true);
//            onCreate(database, connectionSource);
//        } catch (java.sql.SQLException e) {
//            e.printStackTrace();
//        }
    }

    //****************************************************************************************************************************************
    public Dao<Discon_data, Integer> getDisconDao() throws java.sql.SQLException {
        if (disconDao == null) {
            disconDao = getDao(Discon_data.class);
        }
        return disconDao;
    }

    //****************************************************************************************************************************************
    public Dao<Recon_data, Integer> getReconDao() throws java.sql.SQLException {
        if (reconDao == null) {
            reconDao = getDao(Recon_data.class);
        }
        return reconDao;
    }
}
