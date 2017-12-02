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

    public void saveLocation(Location location)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("latitude", location.getLatitude());
        contentValues.put("longitude", location.getLongitude());
        contentValues.put("timestamp", location.getTime());
        contentValues.put("provider", location.getProvider());

        db.insert(DBHelper.LOCATION_TABLE, null, contentValues);
    }

    public List<Location> getAllLocations() throws ParseException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.query(DBHelper.LOCATION_TABLE, new String[]{"latitude", "longitude", "timestamp", "provider"}, null, null, null, null, "id DESC");
        List<Location> locations = new ArrayList<>();

        while(result.moveToNext())
        {
            Location l = new Location(result.getString(3));
            l.setLatitude(result.getDouble(0));
            l.setLongitude(result.getDouble(1));
            l.setTime(result.getLong(2));
            locations.add(l);
        }

        return locations;
    }
}
