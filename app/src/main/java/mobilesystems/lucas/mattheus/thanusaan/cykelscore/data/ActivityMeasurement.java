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
    private int runId;

    public ActivityMeasurement(int id, Long timeStamp, int activityType, int confidence, int runId) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.activityType = activityType;
        this.confidence = confidence;
        this.runId = runId;
    }


    public int getId() {
        return id;
    }

    public int getRunId() {
        return runId;
    }
    public void setRunId(int runId) {
        this.runId = runId;
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
