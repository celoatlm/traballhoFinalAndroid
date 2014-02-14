package br.edu.utfpr.trabalhofinal;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class AcelerometroActivity extends Activity implements
		SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private Sensor mLight;

	private TextView tvX;
	private TextView tvY;
	private TextView tvZ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acelerometro);
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

		tvX = (TextView) findViewById(R.id.tvX);
		tvY = (TextView) findViewById(R.id.tvY);
		tvZ = (TextView) findViewById(R.id.tvZ);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acelerometro, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
		mSensorManager.registerListener(this, mLight,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

//		ArrayList<Float> eventos = new ArrayList<Float>();
//
//		for (Float a : event.values) {
//			//Log.e("sensor changed", a.toString());
//		}

		Float xAux = event.values[0];
		Float yAux = event.values[1];
		Float zAux = event.values[2];
		
		
		if(event.sensor.getType() == Sensor.TYPE_LIGHT){
			Float light = event.values[0];
			Log.e("ligt",light.toString());
		}
		tvY.setText(yAux.toString());
		tvX.setText(xAux.toString());
		tvZ.setText(zAux.toString());

	}

}
