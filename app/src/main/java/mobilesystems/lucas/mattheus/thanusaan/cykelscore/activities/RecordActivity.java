package mobilesystems.lucas.mattheus.thanusaan.cykelscore.activities;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

import java.util.ArrayList;
import java.util.List;

import mobilesystems.lucas.mattheus.thanusaan.cykelscore.CykelScoreApplication;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.R;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.ActivityDAO;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.ActivityMeasurement;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.LocationDAO;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.LocationMeasurement;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.Run;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.RunDAO;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.services.ActivityRecognitionService;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.services.FusedLocationService;

public class RecordActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public GoogleApiClient mApiClient;
    private FusedLocationService fls;
    private LocationDAO locationDAO;
    private ArrayList<ActivityMeasurement> data;
    private ActivityDAO activityDAO;
    private Run run;
    private Button btnStart;
    private Context context;

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.e("ActivityRecogition", "Recieve");
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                ActivityMeasurement am = (ActivityMeasurement) bundle.getSerializable("ActivityMeasurement");
                am.setRunId(run.getId());
                activityDAO.saveActivity(am);
            }
        }
    };

    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            Location l = locationResult.getLastLocation();
            LocationMeasurement lm = new LocationMeasurement(0, l.getLatitude(), l.getLongitude(), l.getTime(), 0);

            locationDAO.saveLocation(lm);
            Log.i("LocationResult", "lat:" + l.getLatitude() + ", long:" + l.getLongitude());
            // TODO SAVE currentLocation TO DATABASE
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        mApiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mApiClient.connect();
        context = this;

        run = (Run) getIntent().getExtras().getSerializable("run");
        Log.e("Record", "routeId: " + run.getRouteId() + " RunId: " + run.getId());

        data = new ArrayList<>();
        locationDAO = new LocationDAO(this);
        activityDAO = new ActivityDAO(this);



        btnStart = (Button) findViewById(R.id.btnStartCycling);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fls = new FusedLocationService(context, locationCallback);
                registerReceiver(receiver, new IntentFilter("ActivityUpdate"));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        fls.stop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Intent intent = new Intent( this, ActivityRecognitionService.class );
        PendingIntent pendingIntent = PendingIntent.getService( this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates( mApiClient, 3000, pendingIntent );

        Log.e( "ActivityRecogition", "onconnected");
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
