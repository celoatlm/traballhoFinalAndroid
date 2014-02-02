package br.edu.utfpr.trabalhofinal.view;

import br.edu.utfpr.trabalhofinal.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	
	private Button bLogar;
	private EditText etUsuario;
	@SuppressWarnings("unused")
	private EditText etSenha;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		bLogar = (Button) findViewById(R.id.bLogar);
		etUsuario = (EditText) findViewById(R.id.etUsuario);
		etSenha = (EditText)findViewById(R.id.etSenha);
		
		bLogar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences prefs = getSharedPreferences(MainActivity.APP_PREFS, MODE_PRIVATE);
				String username = etUsuario.getText().toString();
				Editor editor = prefs.edit();
				editor.putString(MainActivity.USER, username);
				editor.commit();
				finish();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	

}
