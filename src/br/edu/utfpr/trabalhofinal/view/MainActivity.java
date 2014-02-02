package br.edu.utfpr.trabalhofinal.view;

import br.edu.utfpr.trabalhofinal.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	final static String APP_PREFS = "app_prefs";
	final static String USER = "username";

	private SharedPreferences prefs;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = getSharedPreferences(APP_PREFS, MODE_PRIVATE);
		setContentView(R.layout.splash);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setContentView(R.layout.activity_main);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		String user = prefs.getString(USER, null);
		
		if (item.getItemId() == R.id.iLogar) {
			if(user != null){
				Intent logar = new Intent(this, LoginActivity.class);
				startActivity(logar);
			}
		}
		if(item.getItemId() == R.id.iCadastrarOp){
			//String user = prefs.getString(USER, null);
			if (user == null) {
				Intent logar = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(logar);
			} else {
				Intent coa = new Intent(getApplicationContext(),
						CadastraOpActivity.class);
				startActivity(coa);
			}
		}
		return super.onOptionsItemSelected(item);
	}
}
