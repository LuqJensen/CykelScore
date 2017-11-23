package mobilesystems.lucas.mattheus.thanusaan.cykelscore.activities;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;

import java.util.ArrayList;
import java.util.List;

import mobilesystems.lucas.mattheus.thanusaan.cykelscore.R;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.ActivityMeasurement;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.services.ActivityRecognitionService;

public class RecordActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public GoogleApiClient mApiClient;
    private ArrayList<ActivityMeasurement> data;

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            Log.e("ActivityRecogition", "Recieve");
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                ActivityMeasurement am = (ActivityMeasurement) bundle.getSerializable("ActivityMeasurement");
                data.add(am);
            }
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

        data = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter("ActivityUpdate"));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
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
