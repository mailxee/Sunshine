package com.blogspot.xeeshan.sunshine;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
//import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.*;
import java.util.*;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener,
        View.OnClickListener, LocationListener
{
    LocationManager mLocationManager;
//    ListView mListView;
    List<String[]> gpsCoordinateAll;
    public static MyArrayAdapter mGPSAdapter;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        String[] gpsCoordinateSingle= (String[]) parent.getAdapter().getItem(position);

        Toast.makeText(this, "Main activity"+gpsCoordinateSingle[2], Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        mLocationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Button mButton=(Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);

//        ListView mListView=(ListView) findViewById(R.id.listview_all_gps_coordinates);
//        mListView.setOnItemClickListener(this);

    }
    //implementing the OnItemClick interface here (1 method)


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10,this);
        /*
        Location mLocation;
        try
        {
            mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            String[] mString=
                    {
                            Double.toString(mLocation.getLongitude()),
                            Double.toString(mLocation.getLatitude()),
                            "temp"
                    };
            mGPSAdapter.add(mString);
            mGPSAdapter.notifyDataSetChanged();
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        */
    }

    @Override
    public void onLocationChanged(Location location)
    {
        Location mLocation=location;

        try
        {
            //mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            String mLongitude;
            String mLatitude;
            mLongitude=Double.toString(mLocation.getLongitude());
            mLatitude=Double.toString(mLocation.getLatitude());
            Toast.makeText(this, mLongitude+"\n"+mLatitude, Toast.LENGTH_LONG).show();

            String[] mString=new String[3];
			mString[0]=mLongitude;
			mString[1]=mLatitude;
			mString[2]="rert";

            mGPSAdapter.add(mString);
            mGPSAdapter.notifyDataSetChanged();
			
			mLocationManager.removeUpdates(this);
        }
        catch(Exception e)
        {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }


    /**
     *
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements AdapterView.OnItemClickListener
    {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
/*
            List<String> weekForecast = new ArrayList<String>(
				Arrays.asList(forecastArray));
*/

            List<String[]> gpsCoordinateAll = new ArrayList<String[]>();
            for (int i = 0; i < 3; i++)
            {
                String[] gpsCoordinateSingle={
                        "0",
                        "1",
                        Integer.toString(i)};

                gpsCoordinateAll.add(gpsCoordinateSingle);
                //ArrayList is better than String array because
                //it supports removing and adding elements
            }
			//MyArrayAdapter 
			mGPSAdapter = new MyArrayAdapter(
                    getActivity(),
                    R.layout.gps_coordinate_item,
                    gpsCoordinateAll
            );
            
			ListView mListView=(ListView) rootView.findViewById(R.id.listview_all_gps_coordinates);
            mListView.setOnItemClickListener(this);

			mListView.setAdapter(mGPSAdapter);

            return rootView;
        }
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            String[] gpsCoordinateSingle= (String[]) parent.getAdapter().getItem(position);

            Toast.makeText(getActivity(), "Fragment" + gpsCoordinateSingle[2], Toast.LENGTH_LONG).show();
        }


    }
}
