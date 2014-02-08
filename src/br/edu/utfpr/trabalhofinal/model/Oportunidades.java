package br.edu.utfpr.trabalhofinal.model;

import java.util.ArrayList;

public class Oportunidades {
	
	private static Oportunidades oportunidades = null;
	private ArrayList<Oportunidade> arrayOportunidades;
	
	private Oportunidades(){
		arrayOportunidades = new ArrayList<Oportunidade>();
	}
	
	public static Oportunidades getInstance(){
		if(oportunidades == null){
			oportunidades = new Oportunidades();
		}
		return oportunidades;
	}

	public ArrayList<Oportunidade> getArrayOportunidades() {
		return arrayOportunidades;
	}

	public void setArrayOportunidades(ArrayList<Oportunidade> arrayOportunidades) {
		this.arrayOportunidades = arrayOportunidades;
	}
	
	

}
