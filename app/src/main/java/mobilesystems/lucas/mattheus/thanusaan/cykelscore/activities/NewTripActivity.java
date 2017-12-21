package mobilesystems.lucas.mattheus.thanusaan.cykelscore.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

import mobilesystems.lucas.mattheus.thanusaan.cykelscore.CykelScoreApplication;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.R;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.Location;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.Route;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.RouteDAO;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.Trip;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.TripDAO;

public class NewTripActivity extends AppCompatActivity {

    private static int PLACE_PICKER_REQUEST = 1;
    private Button btnNewRoute, btnAddRoute, btnCancelRoute;
    private EditText inputRouteName, inputStartLoc, inputEndLoc;
    private LinearLayout llNewRoute;
    private ListView listRoutes;
    private ArrayList<Route> routes = new ArrayList<>();
    private ArrayAdapter<Route> adapter;
    private RouteDAO routeDAO;
    private TripDAO tripDAO;
    private Route newRoute;
    private boolean isStartLoc;
    private Location startLoc, endLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);

        routeDAO = new RouteDAO(this);
        tripDAO = new TripDAO(this);

        llNewRoute = (LinearLayout) findViewById(R.id.layoutNewRoute);
        inputRouteName = (EditText) findViewById(R.id.inputRouteName);
        inputStartLoc = (EditText) findViewById(R.id.inputStartLoc);
        inputEndLoc = (EditText) findViewById(R.id.inputEndLoc);


        listRoutes = (ListView) findViewById(R.id.lvRoutes);
        try {
            routes = new ArrayList<Route>(routeDAO.getAllRoutes());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter = new ArrayAdapter<Route>(this,
                android.R.layout.simple_list_item_1, routes);

        listRoutes.setAdapter(adapter);
        listRoutes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Route r = (Route) listRoutes.getItemAtPosition(position);
                tripDAO.saveRun(new Trip(0, r.getId()));
                Trip trip;
                Intent mainIntent = new Intent(CykelScoreApplication.getContext(), new TripActivity().getClass());
                try {
                    trip = tripDAO.getlastRun();
                    mainIntent.putExtra("trip", (Serializable) trip);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                NewTripActivity.this.startActivity(mainIntent);
            }
        });

        btnNewRoute = (Button) findViewById(R.id.btnNewRoute);
        btnNewRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llNewRoute.setVisibility(View.VISIBLE);
                //CykelScoreApplication.activityIntentSwitch(new placePicker(), NewTripActivity.this);
            }
        });

        inputStartLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStartLoc = true;
                startPlacePickerActivity();
            }
        });

        inputEndLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStartLoc = false;
                startPlacePickerActivity();
            }
        });

        btnAddRoute = (Button) findViewById(R.id.btnAddRoute);
        btnAddRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Route r = new Route(inputRouteName.getText().toString(), startLoc, endLoc);
                routeDAO.saveRoute(r);
                try {
                    routes = new ArrayList<Route>(routeDAO.getAllRoutes());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                adapter.clear();
                adapter.addAll(routes);
                listRoutes = (ListView) findViewById(R.id.lvRoutes);
                llNewRoute.setVisibility(View.GONE);
            }
        });

        btnCancelRoute = (Button) findViewById(R.id.btnCancelRoute);
        btnCancelRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llNewRoute.setVisibility(View.GONE);
            }
        });
    }

    private void startPlacePickerActivity() {
        com.google.android.gms.location.places.ui.PlacePicker.IntentBuilder builder = new com.google.android.gms.location.places.ui.PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
            displaySelectedPlaceFromPlacePicker(data);

        }
    }

    private void displaySelectedPlaceFromPlacePicker(Intent data) {

        Place place = com.google.android.gms.location.places.ui.PlacePicker.getPlace(this, data);
        String name = place.getName().toString();
        String address = place.getAddress().toString();
        LatLng latlng = place.getLatLng();
        Location loc = new Location(latlng.latitude, latlng.longitude);
        if (isStartLoc) {
            startLoc = loc;
            inputStartLoc.setText(name);
        } else {
            endLoc = loc;
            inputEndLoc.setText(name);
        }
    }
}