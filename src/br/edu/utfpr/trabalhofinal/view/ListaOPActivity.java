package br.edu.utfpr.trabalhofinal.view;

import br.edu.utfpr.trabalhofinal.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ListaOPActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_op);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_o, menu);
		return true;
	}

}
