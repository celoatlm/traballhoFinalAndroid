package rb.edu.utfpr.trabalhofinal.control;

import java.util.ArrayList;

import br.edu.utfpr.trabalhofinal.R;
import br.edu.utfpr.trabalhofinal.model.Oportunidade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterListVireOportunidade extends BaseAdapter {

	
	private ArrayList<Oportunidade> oportunidades;
	private LayoutInflater mInflater;
	
	public AdapterListVireOportunidade(Context context, ArrayList<Oportunidade> oportunidades){
		
		this.oportunidades = oportunidades;  
		mInflater = LayoutInflater.from(context);
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return oportunidades.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return oportunidades.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Oportunidade oportunidade = oportunidades.get(position);
		
		convertView = mInflater.inflate(R.layout.listoportunidades,null);
		
		((TextView)convertView.findViewById(R.id.tvLCategoria)).setText(oportunidade.getCategoria().getDescricao());
		((TextView)convertView.findViewById(R.id.tvLCurso)).setText(oportunidade.getCurso().getDescricao());	
		return convertView;
	}

}
