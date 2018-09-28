package com.androiddeft.jsonretrofit.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.androiddeft.jsonretrofit.Models.Discon_data;
import com.androiddeft.jsonretrofit.Models.Recon_data;
import com.androiddeft.jsonretrofit.Values.FunctionCall;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.sql.SQLException;

public class DatabaseHelper {
    private MyHelper mh;
    private SQLiteDatabase sqLiteDatabase;
    private static FunctionCall functionCall = new FunctionCall();
    private Dao<Discon_data, Integer> disconDao;
    private Dao<Recon_data, Integer> reconDao;

    private String databasepath = "";
    private String databasefolder = "database";
    private String database_name = "discon_recon.db";
    private File databasefile = null;


    public DatabaseHelper(Context context) {
        try {
            databasefile = functionCall.filestorepath(databasefolder, database_name);
            databasepath = functionCall.filepath(databasefolder) + File.separator + database_name;
            mh = new MyHelper(context, databasepath, null, 7);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        sqLiteDatabase = mh.getWritableDatabase();
    }

    public class MyHelper extends OrmLiteSqliteOpenHelper {

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
            try {
                TableUtils.createTable(connectionSource, Discon_data.class);
                TableUtils.createTable(connectionSource, Recon_data.class);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
            try {
                TableUtils.dropTable(connectionSource, Discon_data.class, true);
                TableUtils.dropTable(connectionSource, Recon_data.class, true);
                onCreate(sqLiteDatabase, connectionSource);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public Dao<Discon_data, Integer> getDisconDao() throws SQLException {
            if (disconDao == null) {
                disconDao = getDao(Discon_data.class);
            }
            return disconDao;
        }

        public Dao<Recon_data, Integer> getReconDao() throws SQLException {
            if (reconDao == null) {
                reconDao = getDao(Recon_data.class);
            }
            return reconDao;
        }
    }
}
