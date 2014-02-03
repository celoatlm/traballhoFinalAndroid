package br.edu.utfpr.trabalhofinal.view;

import java.util.ArrayList;
import java.util.List;

import rb.edu.utfpr.trabalhofinal.control.AdapterListVireOportunidade;

import br.edu.utfpr.trabalhofinal.R;
import br.edu.utfpr.trabalhofinal.bd.OportunidadeDAO;
import br.edu.utfpr.trabalhofinal.model.Oportunidade;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	final static String APP_PREFS = "app_prefs";
	final static String USER = "username";

	private SharedPreferences prefs;
	private OportunidadeDAO oportunidadeDAO;
//	private MenuItem iLogar;
	ListView lvOportunidades;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = getSharedPreferences(APP_PREFS, MODE_PRIVATE);
		//setContentView(R.layout.splash);
		oportunidadeDAO = new OportunidadeDAO(getApplicationContext());
		
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		setContentView(R.layout.activity_main);
//		String user = prefs.getString(USER, null);
//		iLogar = (MenuItem)findViewById(R.id.iLogar);
//		if(user != null){
//			iLogar.setTitle(R.string.sDeslogar);
//		}
		ArrayList<String> arrayList = new ArrayList<String>();
		
		oportunidadeDAO.open();
		
		
		
		List<Oportunidade> oportunidades = oportunidadeDAO.getAll();
		for(Oportunidade op:oportunidades){
			arrayList.add(op.getCategoria().getDescricao());
			Log.e("oportunidades",op.getId()+":"+ op.getDescricao()+":"+op.getCategoria().getDescricao()+":"+op.getCurso().getDescricao());
		}
		AdapterListVireOportunidade adapter = new AdapterListVireOportunidade(getApplicationContext(), (ArrayList<Oportunidade>) oportunidades);
//		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList));
		setListAdapter(adapter);
		//lvOportunidades = (ListView)findViewById(R.id.lvOportunidades);
		lvOportunidades = getListView();
		
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
			if(user == null){
			
				Intent logar = new Intent(this, LoginActivity.class);
				startActivity(logar);
				
			}else{
				Editor editor = prefs.edit();
				editor.putString(MainActivity.USER, null);
				editor.commit();
				
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
//	@Override
//	protected void onResume() {
//		// TODO Auto-generated method stub
//		
//		prefs = getSharedPreferences(APP_PREFS, MODE_PRIVATE);
//		String user = prefs.getString(USER, null);
//		iLogar = (MenuItem)findViewById(R.id.iLogar);
//		if(user != null){
//			iLogar.setTitle(R.string.sDeslogar);
//		}else{
//			iLogar.setTitle(R.string.sLogar);
//		}
//		super.onResume();
//	}
}
