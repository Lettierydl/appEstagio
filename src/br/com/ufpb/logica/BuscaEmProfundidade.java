package br.com.ufpb.logica;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BuscaEmProfundidade extends Algoritmo {

	private List<No> visitados;
	private LinkedList<Tupla> tuplasResposta;

	public BuscaEmProfundidade(Grafo grafo) {
		super(grafo);
		visitados = new LinkedList<No>();
		tuplasResposta = new LinkedList<Tupla>();
	}

	@Override
	public List<Tupla> getResposta(No inicio) {
		try {
			busca(inicio);
			return tuplasResposta;
		} finally {
			this.tuplasResposta = new LinkedList<Tupla>();
		}
	}

	public void busca(No raiz) {
		visitados.add(raiz);
		Collection<No> c = raiz.arestas.keySet();
		for (No filho : c) {
			if(!visitado(filho)){
				tuplasResposta.add(new Tupla(raiz.id, filho.id));
				busca(filho);
			}
		}

	}
	
	private boolean visitado(No filho){
		for(No v : visitados){
			if(v.id == filho.id){
				return true;
			}
		}
		return false;
	}

}
