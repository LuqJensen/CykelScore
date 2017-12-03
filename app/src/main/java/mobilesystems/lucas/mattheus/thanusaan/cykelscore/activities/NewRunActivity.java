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

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

import mobilesystems.lucas.mattheus.thanusaan.cykelscore.CykelScoreApplication;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.R;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.Route;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.RouteDAO;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.Run;
import mobilesystems.lucas.mattheus.thanusaan.cykelscore.data.RunDAO;

public class NewRunActivity extends AppCompatActivity {

    private Button btnNewRoute, btnAddRoute, btnCancelRoute;
    private EditText inputRouteName, inputStartLat, inputStartLong, inputEndLat, inputEndLong;
    private LinearLayout llNewRoute;
    private ListView listRoutes;
    private ArrayList<Route> routes = new ArrayList<>();
    private ArrayAdapter<Route> adapter;
    private RouteDAO routeDAO;
    private RunDAO runDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_run);

        routeDAO = new RouteDAO(this);
        runDAO= new RunDAO(this);

        llNewRoute = (LinearLayout) findViewById(R.id.layoutNewRoute);
        inputRouteName = (EditText) findViewById(R.id.inputRouteName);
        inputStartLat = (EditText) findViewById(R.id.inputStartLat);
        inputStartLong = (EditText) findViewById(R.id.inputStartLong);
        inputEndLat = (EditText) findViewById(R.id.inputEndLat);
        inputEndLong = (EditText) findViewById(R.id.inputEndLong);

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
                runDAO.saveRun(new Run(0, r.getId()));
                Run run;
                Intent mainIntent = new Intent(CykelScoreApplication.getContext(), new RecordActivity().getClass());
                try {
                    run = runDAO.getlastRun();
                    mainIntent.putExtra("run",(Serializable) run);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                NewRunActivity.this.startActivity(mainIntent);
            }
        });

        btnNewRoute = (Button) findViewById(R.id.btnNewRoute);
        btnNewRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llNewRoute.setVisibility(View.VISIBLE);
            }
        });

        btnAddRoute = (Button) findViewById(R.id.btnAddRoute);
        btnAddRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Route r = new Route(inputRouteName.getText().toString(), Integer.parseInt(inputStartLat.getText().toString()), Integer.parseInt(inputStartLong.getText().toString()), Integer.parseInt(inputEndLat.getText().toString()), Integer.parseInt(inputEndLong.getText().toString()));
                routeDAO.saveRoute(r);
                try {
                    routes = new ArrayList<Route>(routeDAO.getAllRoutes());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                adapter.clear();
                adapter.addAll(routes);
                listRoutes = (ListView) findViewById(R.id.lvRoutes);
                llNewRoute.setVisibility(View.INVISIBLE);
            }
        });

        btnCancelRoute = (Button) findViewById(R.id.btnCancelRoute);
        btnCancelRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llNewRoute.setVisibility(View.INVISIBLE);
            }
        });



    }
}
