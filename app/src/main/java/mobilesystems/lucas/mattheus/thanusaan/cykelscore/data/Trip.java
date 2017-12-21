package mobilesystems.lucas.mattheus.thanusaan.cykelscore.data;

import java.io.Serializable;

/**
 * Created by Thanusaan on 03-12-2017.
 */

public class Trip implements Serializable {
    private int id;
    private int routeId;
    private long time;

    public Trip(int id, int routeId) {
        this.id = id;
        this.routeId = id;
    }

    public void setTime(long time) { this.time = time; }

    public int getId() {
        return id;
    }

    public int getRouteId() {
        return routeId;
    }

    public long getTime() { return time;}
}
