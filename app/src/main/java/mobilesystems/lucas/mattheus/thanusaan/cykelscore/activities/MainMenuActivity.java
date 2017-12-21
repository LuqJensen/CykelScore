package mobilesystems.lucas.mattheus.thanusaan.cykelscore.activities;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mobilesystems.lucas.mattheus.thanusaan.cykelscore.CykelScoreApplication;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.R;

public class MainMenuActivity extends AppCompatActivity {

    private Button btnStartCycling, btnViewRuns;

    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_ACCESS_FINE_LOCATION);
            }
        }

        btnStartCycling = (Button) findViewById(R.id.btnStartCycling);
        btnStartCycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CykelScoreApplication.activityIntentSwitch(new NewTripActivity(), MainMenuActivity.this);
            }
        });

        btnViewRuns = (Button) findViewById(R.id.btnViewRuns);
        btnViewRuns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CykelScoreApplication.activityIntentSwitch(new ViewTripsActivity(), MainMenuActivity.this);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
