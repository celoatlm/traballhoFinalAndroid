package br.edu.utfpr.trabalhofinal.bd;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.edu.utfpr.trabalhofinal.model.Oportunidade;

public class OportunidadeDAO extends GenericDAO<Oportunidade> {

	private CategoriaDAO categoriaDAO;
	private CursoDAO cursoDAO;

	public OportunidadeDAO(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		categoriaDAO = new CategoriaDAO(context);
		categoriaDAO.open();
		
		cursoDAO = new CursoDAO(context);
		cursoDAO.open();
		this.columns = new String[] {"id","descricao", "categoriaId",
				"cursoId" };
		
		
	}

	@Override
	public Oportunidade get(int id) {
		// TODO Auto-generated method stub
		return get(DBHelper.TABLE_OPORTUNIDADE, columns, id);
	}

	@Override
	public boolean create(Oportunidade obj) {
		// TODO Auto-generated method stub
		database.insert(DBHelper.TABLE_OPORTUNIDADE, null,
				getContentValues(obj));
		return true;
	}

	@Override
	public boolean delete(Oportunidade obj) {
		// TODO Auto-generated method stub
		database.delete(DBHelper.TABLE_OPORTUNIDADE, "id=" + obj.getId(), null);
		return true;
	}

	@Override
	public boolean update(Oportunidade obj) {
		// TODO Auto-generated method stub
		database.update(DBHelper.TABLE_OPORTUNIDADE, getContentValues(obj),
				"id=" + obj.getId(), null);
		return true;
	}

	@Override
	public List<Oportunidade> getAll() {
		// TODO Auto-generated method stub
		Cursor cursor = database.query(DBHelper.TABLE_OPORTUNIDADE, columns,
				null, null, null, null, columns[1]);

		return processarResultadoQuery(cursor);
	}

	@Override
	protected List<Oportunidade> processarResultadoQuery(Cursor cursor) {
		// TODO Auto-generated method stub
		List<Oportunidade> oportunidades = new ArrayList<Oportunidade>();

		// Movimenta o cursor para a primeira posi��o da lista de cursores
		cursor.moveToFirst();

		while (!cursor.isAfterLast()) {
			// Adicionar cada "linha da tabela" convertida para Obj. Evento
			oportunidades.add(cursorToObject(cursor));
			cursor.moveToNext(); // movimenta o cursor para o pr�x. elemento
		}

		return oportunidades; // retorna a lista de eventos
	}

	@Override
	protected Oportunidade cursorToObject(Cursor cursor) {
		// TODO Auto-generated method stub
		
		Oportunidade oportunidade = new Oportunidade(cursor.getInt(0),
				cursor.getString(1), categoriaDAO.get(cursor.getInt(2)),
				cursoDAO.get(cursor.getInt(3)));
		return oportunidade;
	}

	private ContentValues getContentValues(Oportunidade oportunidade) {
		ContentValues values = new ContentValues();

		//values.put("id", oportunidade.getId());
		values.put("descricao", oportunidade.getDescricao());
		values.put("categoriaId", oportunidade.getCategoria().getId());
		values.put("cursoId", oportunidade.getCurso().getId());
		
		return values;
	}

}
