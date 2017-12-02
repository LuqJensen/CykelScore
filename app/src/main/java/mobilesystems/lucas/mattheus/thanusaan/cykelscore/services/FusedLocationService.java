package mobilesystems.lucas.mattheus.thanusaan.cykelscore.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;

/**
 * Created by lucas on 02-12-2017.
 */

public class FusedLocationService {
    private static final String TAG = FusedLocationService.class.getSimpleName();

    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private LocationSettingsRequest locationSettingsRequest;

    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;

    @SuppressLint("MissingPermission")
    public FusedLocationService (Context context, LocationCallback callback) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        locationCallback = callback;

        this.locationRequest = new LocationRequest();
        this.locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        this.locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        this.locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(this.locationRequest);
        this.locationSettingsRequest = builder.build();

        this.mFusedLocationClient.requestLocationUpdates(this.locationRequest, callback, Looper.myLooper());
    }

    public void stop() {
        this.mFusedLocationClient.removeLocationUpdates(this.locationCallback);
        Log.i(TAG, "stop() Stopping location tracking");
    }
}
