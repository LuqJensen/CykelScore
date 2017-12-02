package mobilesystems.lucas.mattheus.thanusaan.cykelscore.data;

import java.io.Serializable;

/**
 * Created by Thanusaan on 23-11-2017.
 */

public class ActivityMeasurement implements Serializable {
    private int id;
    private Long timeStamp;
    private int activityType;
    private int confidence;
    private int routeId;

    public int getId() {
        return id;
    }

    public int getRouteId() {
        return routeId;
    }

    public ActivityMeasurement(int id, Long timeStamp, int activityType, int confidence, int routeId) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.activityType = activityType;
        this.confidence = confidence;
        this.routeId = routeId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public int getActivityType() {
        return activityType;
    }

    public int getConfidence() {
        return confidence;
    }
}
