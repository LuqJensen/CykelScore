package mobilesystems.lucas.mattheus.thanusaan.cykelscore.data;

import java.io.Serializable;

/**
 * Created by Thanusaan on 03-12-2017.
 */

public class Route implements Serializable {

    private int id;
    private String name;
    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;

    public Route(String name, double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        this.name = name;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
    }

    public Route(int id, String name, double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        this.id = id;
        this.name = name;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getStartLatitude() {
        return startLatitude;
    }

    public double getStartLongitude() {
        return startLongitude;
    }

    public double getEndLatitude() {
        return endLatitude;
    }

    public double getEndLongitude() {
        return endLongitude;
    }

    @Override
    public String toString() {
        return name;
    }
}
