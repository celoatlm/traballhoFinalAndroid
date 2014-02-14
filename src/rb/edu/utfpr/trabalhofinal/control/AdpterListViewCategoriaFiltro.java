package rb.edu.utfpr.trabalhofinal.control;

import java.util.ArrayList;
import java.util.Map;

import br.edu.utfpr.trabalhofinal.R;
import br.edu.utfpr.trabalhofinal.model.Categoria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class AdpterListViewCategoriaFiltro extends BaseAdapter{

	private ArrayList<Categoria> categorias;
	private LayoutInflater inflater;
	private Map<String,Boolean> mapStringCategoria;
	
	public AdpterListViewCategoriaFiltro(Context context, ArrayList<Categoria> categorias, Map<String,Boolean> mapStringCategoria){
		this.categorias = categorias;
		this.inflater = LayoutInflater.from(context);
		this.mapStringCategoria = mapStringCategoria;
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
		final Categoria c = categorias.get(position);
		
		convertView = inflater.inflate(R.layout.listdialogfiltro, null);
		((TextView)convertView.findViewById(R.id.tvItemCheked)).setText(c.getDescricao());
		final CheckBox cbItem = (CheckBox)convertView.findViewById(R.id.cbItem);
		cbItem.setChecked(mapStringCategoria.get(c.getDescricao()));
		
		cbItem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Boolean aux =  !mapStringCategoria.get(c.getDescricao());
				mapStringCategoria.put(c.getDescricao(),aux);
			}
		});
		return convertView;
	}
	public Map<String, Boolean> getMapStringCategoria() {
		return mapStringCategoria;
	}

}
