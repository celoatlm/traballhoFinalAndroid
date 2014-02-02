package br.edu.utfpr.trabalhofinal.bd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.edu.utfpr.trabalhofinal.model.Categoria;

public class CategoriaDAO extends GenericDAO<Categoria> {

	public CategoriaDAO(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.columns = new String[] {"id", "descricao"};
	}

	public Categoria get(String descricao){
		//String where = columns[1] + " LIKE '%" + descricao +"%'";
		String sql = "select * from "+ DBHelper.TABLE_CURSO +" where " +columns[1] + " LIKE '%" + descricao +"%';";
		Cursor cursor = database.rawQuery(sql, null);
		cursor.moveToFirst();
		return cursorToObject(cursor);
	}

	
	@Override
	public Categoria get(int id) {
		// TODO Auto-generated method stub
		return get(DBHelper.TABLE_CATEGORIA, columns, id);
	}

	@Override
	public boolean create(Categoria obj) {
		// TODO Auto-generated method stub
		
		database.insert(DBHelper.TABLE_CATEGORIA, null, getContentValues(obj));
		return true;
	}

	@Override
	public boolean delete(Categoria obj) {
		// TODO Auto-generated method stub
		database.delete(DBHelper.TABLE_CATEGORIA, "id="+obj.getId(),null);
		return false;
	}

	@Override
	public boolean update(Categoria obj) {
		// TODO Auto-generated method stub
		database.update(DBHelper.TABLE_CATEGORIA, getContentValues(obj), "id="+obj.getId(), null);
		return false;
	}

	@Override
	public List<Categoria> getAll() {
		// TODO Auto-generated method stub
		Cursor cursor = database.query(DBHelper.TABLE_CATEGORIA, columns, null, null, null, null, columns[1]);
		
		return processarResultadoQuery(cursor);
	}

	@Override
	protected List<Categoria> processarResultadoQuery(Cursor cursor) {
		// TODO Auto-generated method stub
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast()) {
			categorias.add( cursorToObject(cursor) );
			cursor.moveToNext();
		}
		
		cursor.close();
		
		return categorias;
	}

	@Override
	protected Categoria cursorToObject(Cursor cursor) {
		// TODO Auto-generated method stub
		Categoria categoria = new Categoria(cursor.getInt(0),cursor.getString(1));
				
		return categoria;
	}
	
	private ContentValues getContentValues(Categoria categoria){
		ContentValues values = new ContentValues();
		
		values.put("id", categoria.getId());
		values.put("descricao", categoria.getDescricao());
		
		return values;
	}

}
