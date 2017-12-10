package mobilesystems.lucas.mattheus.thanusaan.cykelscore.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.R;



/**
 * Created by Mateusz on 10.12.2017.
 */

public class placePicker extends AppCompatActivity {


    int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        Button gotoButton = (Button) findViewById(R.id.gotoButton);
        Button gotoButton2 = (Button) findViewById(R.id.gotoButton2);
        gotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlacePickerActivity();
            }

        });
        gotoButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPlacePickerActivity();
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
        String latlng = place.getLatLng().toString();

        TextView selectedLocation = (TextView) findViewById(R.id.show_selected_location);
        selectedLocation.setText(name + ", " + address + "lat lng, " + latlng);
    }
}