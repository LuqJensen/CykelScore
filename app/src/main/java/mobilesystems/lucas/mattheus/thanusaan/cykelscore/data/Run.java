package mobilesystems.lucas.mattheus.thanusaan.cykelscore.data;

import java.io.Serializable;

/**
 * Created by Thanusaan on 03-12-2017.
 */

public class Run implements Serializable {
    private int id;
    private int routeId;

    public Run(int id, int routeId) {
        this.id = id;
        this.routeId = id;
    }

    public int getId() {
        return id;
    }

    public int getRouteId() {
        return routeId;
    }
}
