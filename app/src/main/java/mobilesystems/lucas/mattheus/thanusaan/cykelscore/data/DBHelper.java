package mobilesystems.lucas.mattheus.thanusaan.cykelscore.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lucas on 02-12-2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "CykelScore.db";
    private static int DB_VERSION = 6;

    public static String ROUTE_TABLE = "ROUTE";
    public static String RUN_TABLE = "RUN";
    public static String LOCATION_TABLE = "LOCATION";
    public static String ACTIVITY_TABLE = "ACTIVITY";

    private static String DB_ROUTE_CREATION = "CREATE TABLE IF NOT EXISTS `" + ROUTE_TABLE + "` (\n" +
            "  `id` INTEGER PRIMARY KEY,\n" +
            "  `name` TEXT,\n" +
            "  `startLatitude` REAL,\n" +
            "  `startLongitude` `+` REAL,\n" +
            "  `endLatitude` REAL,\n" +
            "  `endLongitude` REAL\n" +
            ");";

    private static String DB_RUN_CREATION = "CREATE TABLE IF NOT EXISTS `" + RUN_TABLE + "` (\n" +
            "  `id` INTEGER PRIMARY KEY,\n" +
            "  `routeid` REAL\n" +
            "  `time` INTEGER\n" +
            ");";

    private static String DB_LOCATION_CREATION = "CREATE TABLE IF NOT EXISTS `" + LOCATION_TABLE + "` (\n" +
            "  `id` INTEGER PRIMARY KEY,\n" +
            "  `latitude` REAL,\n" +
            "  `longitude` REAL,\n" +
            "  `timestamp` INTEGER,\n" +
            "  `runid` INTEGER\n" +
            ");";

    private static String DB_ACTIVITY_CREATION = "CREATE TABLE IF NOT EXISTS `" + ACTIVITY_TABLE + "` (\n" +
            "  `id` INTEGER PRIMARY KEY,\n" +
            "  `activity` INTEGER,\n" +
            "  `confidence` INTEGER,\n" +
            "  `timestamp` INTEGER,\n" +
            "  `runid` INTEGER\n" +
            ");";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("TABLE", "CREATE");
        db.execSQL(DB_ROUTE_CREATION);
        db.execSQL(DB_RUN_CREATION);
        db.execSQL(DB_LOCATION_CREATION);
        db.execSQL(DB_ACTIVITY_CREATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //todo
    }
}
