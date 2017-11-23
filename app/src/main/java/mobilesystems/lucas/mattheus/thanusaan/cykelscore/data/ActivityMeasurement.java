package mobilesystems.lucas.mattheus.thanusaan.cykelscore.data;

import java.io.Serializable;

/**
 * Created by Thanusaan on 23-11-2017.
 */

public class ActivityMeasurement implements Serializable {

    private Long timeStamp;
    private int activityType;
    private int confidence;

    public ActivityMeasurement(Long timeStamp, int type, int confidence) {
        this.timeStamp = timeStamp;
        this.activityType = type;
        this.confidence = confidence;
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
