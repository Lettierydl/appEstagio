package br.com.ufpb.logica;

import java.util.List;

public abstract class Algoritmo {

	protected Grafo grafo;
	
	protected Algoritmo(Grafo grafo){
		this.grafo = grafo;
	}
	
	public abstract List<Tupla> getResposta(No inicio);
	
}
