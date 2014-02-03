package br.edu.utfpr.trabalhofinal.view;

import java.util.HashMap;
import java.util.Map;

import br.edu.utfpr.trabalhofinal.R;
import br.edu.utfpr.trabalhofinal.bd.CategoriaDAO;
import br.edu.utfpr.trabalhofinal.bd.CursoDAO;
import br.edu.utfpr.trabalhofinal.bd.OportunidadeDAO;
import br.edu.utfpr.trabalhofinal.model.Categoria;
import br.edu.utfpr.trabalhofinal.model.Curso;
import br.edu.utfpr.trabalhofinal.model.Oportunidade;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CadastraOpActivity extends Activity {

	private CursoDAO cursoDAO;
	private CategoriaDAO categoriaDAO;
	private OportunidadeDAO oportunidadeDAO;
	
	private Spinner sCategoria;
	private Spinner sCurso;
	private EditText etDescricao;
	private Button bGravarOportunidade;
	private Map<String, Curso> mapCursos;
	private Map<String, Categoria> mapCategorias;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastra_op);
		mapCursos = new HashMap<String, Curso>();
		mapCategorias = new HashMap<String, Categoria>();
		
		cursoDAO = new CursoDAO(getApplicationContext());
		cursoDAO.open();
		categoriaDAO = new CategoriaDAO(getApplicationContext());
		categoriaDAO.open();
		oportunidadeDAO = new OportunidadeDAO(getApplicationContext());
		oportunidadeDAO.open();
		
		sCategoria = (Spinner)findViewById(R.id.sCategoria);
		sCurso = (Spinner)findViewById(R.id.sCurso);
		etDescricao = (EditText)findViewById(R.id.etDescricao);
		bGravarOportunidade = (Button)findViewById(R.id.bGravaOportunidade);
		
		ArrayAdapter<String> arrayAdapterCategoria = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item);
		ArrayAdapter<String> arrayAdapterCurso = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item);
		
		for(Categoria c: categoriaDAO.getAll()){
			arrayAdapterCategoria.add(c.getDescricao());
			mapCategorias.put(c.getDescricao(), c);
		}
		sCategoria.setAdapter(arrayAdapterCategoria);
		
		
		for(Curso c : cursoDAO.getAll()){
			arrayAdapterCurso.add(c.getDescricao());
			mapCursos.put(c.getDescricao(), c);
		}
		sCurso.setAdapter(arrayAdapterCurso);
	
		bGravarOportunidade.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Oportunidade op = new Oportunidade(null, etDescricao
						.getText().toString(), mapCategorias.get((String) sCategoria.getSelectedItem()),
						mapCursos.get((String) sCurso.getSelectedItem()));
				oportunidadeDAO.create(op);
				finish();
				Log.e("cop", op.getId()+":"+op.getDescricao()+":"+op.getCategoria().getId()+":"+op.getCurso().getId());
//				Curso cu = mapCursos.get((String) sCurso.getSelectedItem());
//				Log.e("spinner", (String) sCategoria.getSelectedItem()+":"+ca.getId()+":"+ca.getDescricao());
//				Log.e("spinner", (String) sCurso.getSelectedItem()+":"+cu.getId()+":"+cu.getDescricao());
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastra_op, menu);
		return true;
	}

}
