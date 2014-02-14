package rb.edu.utfpr.trabalhofinal.control;

import java.util.ArrayList;
import java.util.Map;

import br.edu.utfpr.trabalhofinal.R;
import br.edu.utfpr.trabalhofinal.model.Curso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class AdpterListViewCursoFiltro extends BaseAdapter{

	private ArrayList<Curso> cursos;
	private LayoutInflater inflater;
	private Map<String,Boolean> mapStringCurso;
	
	public AdpterListViewCursoFiltro(Context context, ArrayList<Curso> cursos,Map<String,Boolean> mapStringCurso){
		this.cursos = cursos;
		this.inflater = LayoutInflater.from(context);
		this.mapStringCurso = mapStringCurso;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return cursos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return cursos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final Curso c = cursos.get(position);
		
		convertView = inflater.inflate(R.layout.listdialogfiltro, null);
		((TextView)convertView.findViewById(R.id.tvItemCheked)).setText(c.getDescricao());
		CheckBox cbItem = (CheckBox)convertView.findViewById(R.id.cbItem);
		cbItem.setChecked(mapStringCurso.get(c.getDescricao()));
		
		cbItem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Boolean aux = !mapStringCurso.get(c.getDescricao());
				mapStringCurso.put(c.getDescricao(),aux); 
			}
		});
		return convertView;
	}
	public Map<String, Boolean> getMapStringCurso() {
		return mapStringCurso;
	}

	
}
