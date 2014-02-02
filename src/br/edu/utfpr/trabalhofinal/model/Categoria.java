package br.edu.utfpr.trabalhofinal.model;

import java.io.Serializable;

public class Categoria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String Descricao;
	
	public Categoria(Integer id, String descricao) {
		this.id = id;
		Descricao = descricao;
	}
	
	public Categoria() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	
	

}
