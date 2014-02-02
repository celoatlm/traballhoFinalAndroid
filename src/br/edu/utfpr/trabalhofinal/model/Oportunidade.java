package br.edu.utfpr.trabalhofinal.model;

import java.io.Serializable;

public class Oportunidade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String Descricao;
	private Categoria categoria;
	private Curso curso;
	
	public Oportunidade(Integer id, String descricao, Categoria categoria, Curso curso) {
		this.id = id;
		this.Descricao = descricao;
		this.categoria = categoria;
		this.curso = curso;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	
	
	
	
	
	

}
