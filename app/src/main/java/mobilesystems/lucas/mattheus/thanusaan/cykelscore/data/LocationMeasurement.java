package mobilesystems.lucas.mattheus.thanusaan.cykelscore.data;

import java.io.Serializable;

/**
 * Created by lucas on 02-12-2017.
 */

public class LocationMeasurement implements Serializable {
    private int id;
    private double latitude;
    private double longitude;
    private long timestamp;
    private int routeId;

    public LocationMeasurement(int id, double latitude, double longitude, long timestamp, int routeId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.routeId = routeId;
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getRouteId() {
        return routeId;
    }
}
