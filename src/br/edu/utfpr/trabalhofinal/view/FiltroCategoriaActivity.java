package br.edu.utfpr.trabalhofinal.view;

import br.edu.utfpr.trabalhofinal.R;
import br.edu.utfpr.trabalhofinal.R.layout;
import br.edu.utfpr.trabalhofinal.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.Menu;

public class FiltroCategoriaActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filtro_categoria);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filtro_categoria, menu);
		return true;
	}

}
