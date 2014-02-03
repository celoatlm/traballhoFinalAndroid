package br.edu.utfpr.trabalhofinal.list;

import java.util.ArrayList;

import br.edu.utfpr.trabalhofinal.R;
import br.edu.utfpr.trabalhofinal.model.Oportunidade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdpterListViewOportunidade extends BaseAdapter{

	private LayoutInflater mInflater;
	private ArrayList<Oportunidade> oportunidades;
	
	public AdpterListViewOportunidade(Context context, ArrayList<Oportunidade> oportunidades){
		
		this.oportunidades = oportunidades;
		
		mInflater = LayoutInflater.from(context);
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return oportunidades.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return oportunidades.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		Oportunidade oportunidade = oportunidades.get(arg0);
				
		view = mInflater.inflate(R.layout.listoportunidade, null);
		
		((TextView)view.findViewById(R.id.tvOportunidade)).setText(oportunidade.getDescricao());
		
		((TextView)view.findViewById(R.id.tvCurso)).setText(oportunidade.getCurso().getDescricao());
		((TextView)view.findViewById(R.id.tvCategoria)).setText(oportunidade.getCategoria().getDescricao());	
		
		
		return view;
	}

	
	
}
