package br.edu.utfpr.trabalhofinal.view;

import br.edu.utfpr.trabalhofinal.R;
import br.edu.utfpr.trabalhofinal.model.Oportunidade;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class VerOpActivity extends Activity {

	private TextView tvVCurso;
	private TextView tvVCategoria;
	private TextView tvVOportunidade;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ver_op);
		tvVCurso = (TextView) findViewById(R.id.tvVCurso);
		tvVCategoria = (TextView)findViewById(R.id.tvVCategoria);
		tvVOportunidade = (TextView)findViewById(R.id.tvVOportunidade);
		Bundle b = getIntent().getExtras();
		Oportunidade o = (Oportunidade) b.get("oportunidade");
		
		tvVCurso.setText(o.getCurso().getDescricao());
		tvVCategoria.setText(o.getCategoria().getDescricao());
		tvVOportunidade.setText(o.getDescricao());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ver_op, menu);
		return true;
	}

}
