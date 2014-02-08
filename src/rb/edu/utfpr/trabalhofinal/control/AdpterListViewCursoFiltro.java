package rb.edu.utfpr.trabalhofinal.control;

import java.util.ArrayList;

import br.edu.utfpr.trabalhofinal.R;
import br.edu.utfpr.trabalhofinal.model.Curso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdpterListViewCursoFiltro extends BaseAdapter{

	private ArrayList<Curso> cursos;
	private LayoutInflater inflater;
	
	public AdpterListViewCursoFiltro(Context context, ArrayList<Curso> cursos){
		this.cursos = cursos;
		this.inflater = LayoutInflater.from(context);
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
		Curso c = cursos.get(position);
		
		convertView = inflater.inflate(R.layout.listdialogfiltro, null);
		((TextView)convertView.findViewById(R.id.tvItemCheked)).setText(c.getDescricao());
		
		return convertView;
	}

}
