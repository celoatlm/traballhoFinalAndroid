package rb.edu.utfpr.trabalhofinal.control;

import java.util.ArrayList;

import br.edu.utfpr.trabalhofinal.R;
import br.edu.utfpr.trabalhofinal.model.Categoria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdpterListViewCategoriaFiltro extends BaseAdapter{

	private ArrayList<Categoria> categorias;
	private LayoutInflater inflater;
	
	public AdpterListViewCategoriaFiltro(Context context, ArrayList<Categoria> categorias){
		this.categorias = categorias;
		this.inflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return categorias.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return categorias.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Categoria c = categorias.get(position);
		
		convertView = inflater.inflate(R.layout.listdialogfiltro, null);
		((TextView)convertView.findViewById(R.id.tvItemCheked)).setText(c.getDescricao());
		
		return convertView;
	}

}
