package by.yarik.tagimage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import by.yarik.tagimage.db.model.Image;

public class DBHelper extends OrmLiteSqliteOpenHelper {

    public static final long WRONG_RESULT = -1;
    public static final String DB_NAME = "images.db";
    private static final int DB_VERSION = 1;

    private Dao<Image, Integer> imageDao;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getWritableDatabase();
    }

    public Dao<Image, Integer> getImageDao() throws SQLException {
        if(imageDao == null){
            imageDao = getDao(Image.class);
        }
        return imageDao;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Image.class);
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    @Override
    public void close() {
        imageDao = null;
        super.close();
    }
}
