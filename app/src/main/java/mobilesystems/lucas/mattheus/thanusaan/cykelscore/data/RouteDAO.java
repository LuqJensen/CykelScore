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

public class RouteDAO {

    private DBHelper dbHelper;

    public RouteDAO(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public void saveRoute(Route route)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", route.getName());
        contentValues.put("startLatitude", route.getStartLocation().getLatitude());
        contentValues.put("startLongitude", route.getStartLocation().getLongitude());
        contentValues.put("endLatitude", route.getEndLocation().getLongitude());
        contentValues.put("endLongitude", route.getEndLocation().getLongitude());

        db.insert(DBHelper.ROUTE_TABLE, null, contentValues);
    }

    public List<Route> getAllRoutes() throws ParseException {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor result = db.query(DBHelper.ROUTE_TABLE, new String[]{"id", "name", "startLatitude", "startLongitude", "endLatitude", "endLongitude"}, null, null, null, null, "id DESC");
        List<Route> routes = new ArrayList<>();

        while(result.moveToNext())
        {
            Route r = new Route(result.getInt(0), result.getString(1), new Location(result.getInt(2), result.getInt(3)), new Location(result.getInt(4), result.getInt(5)));
            routes.add(r);
        }

        return routes;
    }
}
