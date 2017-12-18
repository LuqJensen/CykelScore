package mobilesystems.lucas.mattheus.thanusaan.cykelscore.data;

import java.io.Serializable;

/**
 * Created by Thanusaan on 03-12-2017.
 */

public class Route implements Serializable {

    private int id;
    private String name;
    private Location startLocation;
    private Location endLocation;

    public Route(String name, Location startLocation, Location endLocation) {
        this.name = name;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public Route(int id, String name, Location startLocation, Location endLocation) {
        this.id = id;
        this.name = name;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    @Override
    public String toString() {
        return name;
    }
}
