package mobilesystems.lucas.mattheus.thanusaan.cykelscore.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.io.Serializable;

import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.ActivityMeasurement;

/**
 * Created by Thanusaan on 23-11-2017.
 */

public class ActivityRecognitionService extends IntentService {
    public ActivityRecognitionService() {
        super("ActivityRecognizedService");
    }

    public ActivityRecognitionService(String name) {
        super(name);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities(result.getMostProbableActivity());
        }
    }

    private void handleDetectedActivities(DetectedActivity activity) {
        Log.e("ActivityRecogition", "handledetectedactivites");
        sendMessageToActivity(new ActivityMeasurement(System.currentTimeMillis() / 1000, activity.getType(), activity.getConfidence()));
    }

    private void sendMessageToActivity(ActivityMeasurement am) {
        Intent intent = new Intent("ActivityUpdate");
        // You can also include some extra data.
        intent.putExtra("ActivityMeasurement", (Serializable) am);
        sendBroadcast(intent);
        Log.e("ActivityRecogition", "Send");
    }
}
