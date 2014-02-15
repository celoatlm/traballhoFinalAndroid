package br.edu.utfpr.trabalhofinal.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rb.edu.utfpr.trabalhofinal.control.AdapterListVireOportunidade;
import rb.edu.utfpr.trabalhofinal.control.AdpterListViewCategoriaFiltro;
import rb.edu.utfpr.trabalhofinal.control.AdpterListViewCursoFiltro;

import br.edu.utfpr.trabalhofinal.AcelerometroActivity;
import br.edu.utfpr.trabalhofinal.R;
import br.edu.utfpr.trabalhofinal.bd.CategoriaDAO;
import br.edu.utfpr.trabalhofinal.bd.CursoDAO;
import br.edu.utfpr.trabalhofinal.bd.OportunidadeDAO;
import br.edu.utfpr.trabalhofinal.model.Categoria;
import br.edu.utfpr.trabalhofinal.model.Curso;
import br.edu.utfpr.trabalhofinal.model.Oportunidade;
import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	final static String APP_PREFS = "app_prefs";
	final static String USER = "username";

	private SharedPreferences prefs;
	private OportunidadeDAO oportunidadeDAO;
	private CursoDAO cursoDAO;
	private CategoriaDAO categoriaDAO;
	private ArrayList<String> sCategorias;
	private ArrayList<String> sCursos;
	private Map<String, Boolean> mapStringCurso;
	private Map<String, Boolean> mapStringCategoria;

	// private MenuItem iLogar;
	// ListView lvOportunidades;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = getSharedPreferences(APP_PREFS, MODE_PRIVATE);
		// setContentView(R.layout.splash);
		oportunidadeDAO = new OportunidadeDAO(getApplicationContext());
		cursoDAO = new CursoDAO(getApplicationContext());
		categoriaDAO = new CategoriaDAO(getApplicationContext());

		mapStringCurso = new HashMap<String, Boolean>();
		mapStringCategoria = new HashMap<String, Boolean>();
		// try {
		// Thread.sleep(3000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// setContentView(R.layout.activity_main);
		// String user = prefs.getString(USER, null);
		// iLogar = (MenuItem)findViewById(R.id.iLogar);
		// if(user != null){
		// iLogar.setTitle(R.string.sDeslogar);
		// }
		cursoDAO.open();
		categoriaDAO.open();

		sCursos = new ArrayList<String>();
		sCategorias = new ArrayList<String>();

		for (Curso cu : cursoDAO.getAll()) {
			sCursos.add(cu.getDescricao());
			mapStringCurso.put(cu.getDescricao(), true);
		}
		for (Categoria ca : categoriaDAO.getAll()) {
			sCategorias.add(ca.getDescricao());
			mapStringCategoria.put(ca.getDescricao(), true);
		}
		setListOportunidade();
		// lvOportunidades = getListView();

	}

	private void setListOportunidade() {
		oportunidadeDAO.open();

		ArrayList<Oportunidade> oportunidades = new ArrayList<Oportunidade>();

		for (Oportunidade op : oportunidadeDAO.getAll()) {

			if (mapStringCategoria.get(op.getCategoria().getDescricao())
					&& mapStringCurso.get(op.getCurso().getDescricao())) {
				oportunidades.add(op);

			}
		}
		AdapterListVireOportunidade adapter = new AdapterListVireOportunidade(
				getApplicationContext(),
				(ArrayList<Oportunidade>) oportunidades);

		setListAdapter(adapter);

		oportunidadeDAO.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		String user = prefs.getString(USER, null);
		final Oportunidade o = (Oportunidade) l.getItemAtPosition(position);
		if (user != null) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setPositiveButton(R.string.sBADEditar,
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent i = new Intent(getApplicationContext(),
									CadastraOpActivity.class);
							i.putExtra("oportunidade", o);
							startActivity(i);
						}
					});
			builder.setNegativeButton(R.string.sBADVisualizar,
					new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent i = new Intent(getApplicationContext(),
									VerOpActivity.class);
							i.putExtra("oportunidade", o);
							startActivity(i);
						}
					});
			builder.setCancelable(true);
			builder.create();
			builder.show();
		} else {

			Intent i = new Intent(getApplicationContext(), VerOpActivity.class);
			i.putExtra("oportunidade", o);
			startActivity(i);
		}
		super.onListItemClick(l, v, position, id);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		String user = prefs.getString(USER, null);

		if (item.getItemId() == R.id.iLogar) {
			if (user == null) {

				Intent logar = new Intent(this, LoginActivity.class);
				startActivity(logar);

			} else {

				Editor editor = prefs.edit();
				editor.putString(MainActivity.USER, null);
				editor.commit();

			}

		}
		if (item.getItemId() == R.id.iCadastrarOp) {
			if (user == null) {
				Intent logar = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(logar);

			} else {

				Intent coa = new Intent(getApplicationContext(),
						CadastraOpActivity.class);
				// coa.putExtra("oportunidade", new Oportunidade(null, "", null,
				// null));
				startActivity(coa);
			}

		}
		if (item.getItemId() == R.id.iFiltroCategoria) {

			CategoriaDAO categoriaDAO = new CategoriaDAO(
					getApplicationContext());
			categoriaDAO.open();

			ArrayList<Categoria> categorias = new ArrayList<Categoria>(
					categoriaDAO.getAll());
			final AdpterListViewCategoriaFiltro adapter = new AdpterListViewCategoriaFiltro(
					getApplicationContext(), categorias, mapStringCategoria);

			AlertDialog.Builder b = new AlertDialog.Builder(this);
			b.setAdapter(adapter, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

				}
			});
			b.setPositiveButton("Ok", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

					mapStringCategoria = adapter.getMapStringCategoria();
					setListOportunidade();

				}
			});
			b.setNegativeButton("Cancel", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

				}
			});
			b.setCancelable(true);

			AlertDialog alert = b.create();
			alert.show();
			categoriaDAO.close();

		}
		if (item.getItemId() == R.id.iFiltroCurso) {

			CursoDAO cursoDAO = new CursoDAO(getApplicationContext());
			cursoDAO.open();

			ArrayList<Curso> cursos = new ArrayList<Curso>(cursoDAO.getAll());
			final AdpterListViewCursoFiltro adapter = new AdpterListViewCursoFiltro(
					getApplicationContext(), cursos, mapStringCurso);
			AlertDialog.Builder b = new AlertDialog.Builder(this);
			b.setAdapter(adapter, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

				}
			});
			b.setPositiveButton("Ok", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					mapStringCurso = adapter.getMapStringCurso();
					setListOportunidade();
				}
			});
			b.setNegativeButton("Cancel", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub

				}
			});
			b.setCancelable(true);

			AlertDialog alert = b.create();
			alert.show();
			cursoDAO.close();

		}
		if (item.getItemId() == R.id.iGPS) {
			Intent i = new Intent(getApplicationContext(), GPSActivity.class);
			startActivity(i);

		}
		if (item.getItemId() == R.id.iAcelerometro) {
			Intent i = new Intent(getApplicationContext(),
					AcelerometroActivity.class);
			startActivity(i);
		}
		if (item.getItemId() == R.id.iCamera) {
			
		}
		if(item.getItemId() == R.id.iLink){
			//LinkDialog ld = LinkDialog.newInstance();
			//ld.show(getFragmentManager(), "Link");
			
//			final Dialog d = new Dialog(this);
//			d.setContentView(R.layout.custondialoglink);
//			d.setTitle("Link");
			
//			final Button bOkLink = (Button)findViewById(R.id.bOkLink);
//			final Button bCancelLink = (Button)findViewById(R.id.bCancelLink);
			
//			bOkLink.setOnClickListener(new View.OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					String url = etLink.getText().toString();
//					Intent i = new Intent(Intent.ACTION_VIEW);
//					i.setData(Uri.parse(url));
//					startActivity(i);
//					d.dismiss();
//				}
//			});

//			bOKLink.setOnClickListener(new View.OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					
//				}
//			});
			
			//d.show();
			AlertDialog.Builder b = new AlertDialog.Builder(this);
			LayoutInflater inflater = this.getLayoutInflater();
			b.setView(inflater.inflate(R.layout.custondialoglink, null));
			b.setTitle("Link");
			b.setPositiveButton("Ok", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					final EditText etLink = (EditText)findViewById(R.id.etLink);
					String url = etLink.getEditableText().toString();
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse("google.com.br"));
					startActivity(i);
					//b.dismiss();
				}
			});
			b.setCancelable(true);
			b.setNegativeButton("Cancel", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			b.show();
		}
		return super.onOptionsItemSelected(item);
	}

	// @Override
	// protected void onResume() {
	// // TODO Auto-generated method stub
	//
	// prefs = getSharedPreferences(APP_PREFS, MODE_PRIVATE);
	// String user = prefs.getString(USER, null);
	// iLogar = (MenuItem)findViewById(R.id.iLogar);
	// if(user != null){
	// iLogar.setTitle(R.string.sDeslogar);
	// }else{
	// iLogar.setTitle(R.string.sLogar);
	// }
	// super.onResume();
	// }
	
	@SuppressLint("ValidFragment")
	public static class LinkDialog extends DialogFragment{
		
		
		public static LinkDialog newInstance() {
			LinkDialog frag = new LinkDialog();
			
			return frag;
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			
			super.onCreate(savedInstanceState);
			
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View v = inflater.inflate(R.layout.custondialoglink, container, false);
			getDialog().setTitle("Link");
			
			return v;
		}
		public String getLink(){
			return null;
		}
	}

}
