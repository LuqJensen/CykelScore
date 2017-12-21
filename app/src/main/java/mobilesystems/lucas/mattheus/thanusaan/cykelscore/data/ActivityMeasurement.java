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
    private int tripId;

    public ActivityMeasurement(int id, Long timeStamp, int activityType, int confidence, int tripId) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.activityType = activityType;
        this.confidence = confidence;
        this.tripId = tripId;
    }


    public int getId() {
        return id;
    }

    public int getTripId() {
        return tripId;
    }
    public void setTripId(int tripId) {
        this.tripId = tripId;
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
