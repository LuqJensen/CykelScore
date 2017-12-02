package mobilesystems.lucas.mattheus.thanusaan.cykelscore.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 02-12-2017.
 */

public class ActivityDAO {

    private DBHelper dbHelper;

    public ActivityDAO(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void saveActivity(ActivityMeasurement activity)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("activity", activity.getActivityType());
        contentValues.put("confidence", activity.getConfidence());
        contentValues.put("timestamp", activity.getTimeStamp());

        db.insert(DBHelper.ACTIVITY_TABLE, null, contentValues);
    }

    public List<ActivityMeasurement> getAllActivities() throws ParseException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.query(DBHelper.ACTIVITY_TABLE, new String[]{"activity", "confidence", "timestamp"}, null, null, null, null, "id DESC");
        List<ActivityMeasurement> activities = new ArrayList<>();

        while(result.moveToNext())
        {
            ActivityMeasurement am = new ActivityMeasurement(result.getLong(2), result.getInt(0), result.getInt(1));
            activities.add(am);
        }

        return activities;
    }
}
