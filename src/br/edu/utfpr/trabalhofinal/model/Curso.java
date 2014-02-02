package br.edu.utfpr.trabalhofinal.model;

import java.io.Serializable;

public class Curso implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	
	public Curso(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Curso() {
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
	

}
