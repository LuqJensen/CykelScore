package mobilesystems.lucas.mattheus.thanusaan.cykelscore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanusaan on 03-12-2017.
 */

public class TripDAO {

    private DBHelper dbHelper;

    public TripDAO(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void saveRun(Trip trip)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("routeId", trip.getRouteId());

        db.insert(DBHelper.TRIP_TABLE, null, contentValues);
    }

    public List<Trip> getAllRuns() throws ParseException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.query(DBHelper.TRIP_TABLE, new String[]{"id", "routeId"}, null, null, null, null, "id DESC");
        List<Trip> trips = new ArrayList<>();

        while(result.moveToNext())
        {
            Trip r = new Trip(result.getInt(0), result.getInt(1));
            trips.add(r);
        }

        return trips;
    }

    public Trip getlastRun() throws ParseException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.query(DBHelper.TRIP_TABLE, new String[]{"id", "routeId"}, null, null, null, null, "id DESC");
        if (result.moveToFirst())
        {
            return(new Trip(result.getInt(0), result.getInt(1)));
        }
        return null;
    }

    public void saveTimedRun(Trip trip)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("time", trip.getTime());

        db.update(DBHelper.TRIP_TABLE, contentValues, "id = ?", new String[]{trip.getId() + ""});
    }


}
