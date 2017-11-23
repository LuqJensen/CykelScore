package mobilesystems.lucas.mattheus.thanusaan.cykelscore;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Thanusaan on 23-11-2017.
 */

public class CykelScoreApplication extends Application {
    private static CykelScoreApplication instance;

    public CykelScoreApplication() {
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }

    /**
     * Takes an instance of the activity you want to switch to, and the instance of the activity you are swithcing from.
     *
     * @param newIntent
     * @param oldIntent
     */
    public static void activityIntentSwitch(Activity newIntent, Activity oldIntent) // Instead of duplicating same three lines everywhere, just call this.
    {
        Intent mainIntent = new Intent(getContext(), newIntent.getClass());
        oldIntent.startActivity(mainIntent);
        oldIntent.finish();
    }

    public static void activityIntentSwitch(Activity newIntent, Activity oldIntent, int anim1, int anim2) {
        Intent mainIntent = new Intent(getContext(), newIntent.getClass());
        mainIntent.setFlags(mainIntent.FLAG_ACTIVITY_NO_ANIMATION);
        oldIntent.startActivity(mainIntent);
        oldIntent.overridePendingTransition(anim1, anim2);
        oldIntent.finish();
    }
}
