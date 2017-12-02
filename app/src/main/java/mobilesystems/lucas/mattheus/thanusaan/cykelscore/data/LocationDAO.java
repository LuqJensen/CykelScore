package mobilesystems.lucas.mattheus.thanusaan.cykelscore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lucas on 02-12-2017.
 */

public class LocationDAO
{
    private DBHelper dbHelper;

    public LocationDAO(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void saveLocation(LocationMeasurement l)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("latitude", l.getLatitude());
        contentValues.put("longitude", l.getLongitude());
        contentValues.put("timestamp", l.getTimestamp());
        contentValues.put("routeid", l.getRouteId());

        db.insert(DBHelper.LOCATION_TABLE, null, contentValues);
    }

    public List<LocationMeasurement> getAllLocations() throws ParseException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.query(DBHelper.LOCATION_TABLE, new String[]{"id", "latitude", "longitude", "timestamp", "routeid"}, null, null, null, null, "id DESC");
        List<LocationMeasurement> locations = new ArrayList<>();

        while(result.moveToNext())
        {
            LocationMeasurement lm = new LocationMeasurement(result.getInt(0), result.getDouble(1), result.getDouble(2), result.getLong(3), result.getInt(4));
            locations.add(lm);
        }

        return locations;
    }
}
