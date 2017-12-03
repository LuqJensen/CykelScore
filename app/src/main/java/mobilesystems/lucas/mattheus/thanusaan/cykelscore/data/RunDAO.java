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

public class RunDAO {

    private DBHelper dbHelper;

    public RunDAO(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void saveRun(Run run)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("routeId", run.getRouteId());

        db.insert(DBHelper.RUN_TABLE, null, contentValues);
    }

    public List<Run> getAllRuns() throws ParseException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.query(DBHelper.RUN_TABLE, new String[]{"id", "routeId"}, null, null, null, null, "id DESC");
        List<Run> runs= new ArrayList<>();

        while(result.moveToNext())
        {
            Run r = new Run(result.getInt(0), result.getInt(1));
            runs.add(r);
        }

        return runs;
    }

    public Run getlastRun() throws ParseException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.query(DBHelper.RUN_TABLE, new String[]{"id", "routeId"}, null, null, null, null, "id DESC");
        if (result.moveToFirst())
        {
            return(new Run(result.getInt(0), result.getInt(1)));
        }
        return null;
    }


}
