package br.edu.utfpr.trabalhofinal.bd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.edu.utfpr.trabalhofinal.model.Curso;

public class CursoDAO extends GenericDAO<Curso> {

	public CursoDAO(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.columns = new String[]{"id", "descricao"};
	}
	
	public Curso get(String descricao){
		//String where = columns[1] + " LIKE '%" + descricao +"%'";
		String sql = "select * from "+ DBHelper.TABLE_CURSO +" where " +columns[1] + " LIKE '%" + descricao +"%';";
		Cursor cursor = database.rawQuery(sql, null);
		cursor.moveToFirst();
		return cursorToObject(cursor);
	}

	@Override
	public Curso get(int id) {
		// TODO Auto-generated method stub
		return get(DBHelper.TABLE_CURSO, columns, id);
	}

	@Override
	public boolean create(Curso obj) {
		// TODO Auto-generated method stub
		database.insert(DBHelper.TABLE_CURSO, null, getContentValues(obj));
		return true;
	}

	@Override
	public boolean delete(Curso obj) {
		// TODO Auto-generated method stub
		database.delete(DBHelper.TABLE_CURSO, "id="+obj.getId(), null);
		return true;
	}

	@Override
	public boolean update(Curso obj) {
		// TODO Auto-generated method stub
		database.update(DBHelper.TABLE_CURSO, getContentValues(obj), "id= " + obj.getId() ,null);
		return false;
	}

	@Override
	public List<Curso> getAll() {
		// TODO Auto-generated method stub
		Cursor cursor = database.query(DBHelper.TABLE_CURSO, columns, 
				null, null, null, null, columns[1]);
		
		return processarResultadoQuery(cursor);
	}

	@Override
	protected List<Curso> processarResultadoQuery(Cursor cursor) {
		// TODO Auto-generated method stub
		List<Curso> cursos = new ArrayList<Curso>();
		
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast()) {
			cursos.add( cursorToObject(cursor) );	
			cursor.moveToNext(); 
		}

		return cursos;
	}

	@Override
	protected Curso cursorToObject(Cursor cursor) {
		// TODO Auto-generated method stub
		Curso c = new Curso(cursor.getInt(0),cursor.getString(1));
		
		return c;
	}
	
	private ContentValues getContentValues(Curso curso){
		ContentValues values = new ContentValues();
		
		values.put("id", curso.getId());
		values.put("descricao", curso.getDescricao());
		
		return values;
	}

}
