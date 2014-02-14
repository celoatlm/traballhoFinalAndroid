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
import android.widget.Toast;

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
		
		final Bundle b = new Bundle();
		Oportunidade op = (Oportunidade)b.get("oportunidade");
		if(op != null){
			setOportunidadeLabels(op);
		}
		
		
		bGravarOportunidade.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("unused")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(b == null){
					oportunidadeDAO.create(getOportunidadeLabels());
				}else{
					oportunidadeDAO.update(getOportunidadeLabels());
				}
				Toast.makeText(getApplicationContext(), "Oportunidade Cadastrada", Toast.LENGTH_SHORT).show();
				finish();

			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastra_op, menu);
		return true;
	}
	
	private void setOportunidadeLabels(Oportunidade op){
		
		sCategoria.setSelection(op.getCategoria().getId());
		sCurso.setSelection(op.getCurso().getId());
		etDescricao.setText(op.getDescricao());
	}
	private Oportunidade getOportunidadeLabels(){
		Oportunidade op = new Oportunidade(null, etDescricao
				.getText().toString(), mapCategorias.get((String) sCategoria.getSelectedItem()),
				mapCursos.get((String) sCurso.getSelectedItem()));
		return op;
	}

}
