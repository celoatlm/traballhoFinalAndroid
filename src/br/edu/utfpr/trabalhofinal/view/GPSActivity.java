package br.edu.utfpr.trabalhofinal.view;

import br.edu.utfpr.trabalhofinal.R;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class GPSActivity extends Activity {

	private LocationManager locationManager = null;
	private LocationListener locationListener; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
		getLocation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.g, menu);
		return true;
	}
	private void getLocation() {
		// TODO Auto-generated method stub

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		locationListener = new LocationListener() {

			@Override
			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub

				Log.e("on status changed", arg0+":"+arg1+":"+arg2.toString());
			}

			@Override
			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), R.string.sGPSFalha,
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(
						Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivityForResult(intent, 1);
				finish();
			}

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				Double la = location.getLatitude();
				Double lo = location.getLongitude();
				((TextView)findViewById(R.id.tvLatitude)).setText(la.toString());
				((TextView)findViewById(R.id.tvLongitude)).setText(lo.toString());
			}
		};
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100,
				0, locationListener);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if(locationManager != null){
			locationManager.removeUpdates(locationListener);
		}
		
		super.onDestroy();
	}
	}
