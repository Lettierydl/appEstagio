package br.com.ufpb.logica;

import java.util.LinkedList;
import java.util.List;

public class BuscaEmLargura extends Algoritmo {

	private List<No> visitados;
	
	protected BuscaEmLargura(Grafo grafo) {
		super(grafo);
		visitados = new LinkedList<No>();
	}

	@Override
	public List<Tupla> getResposta(No raiz){
		List<Tupla> resposta = new LinkedList<Tupla>();
		LinkedList<No> fila = new LinkedList<No>();
		visitados.add(raiz);
		fila.add(raiz);
		
		while(!fila.isEmpty()){
			No n1 = fila.get(0);
			for(No p : n1.arestas.keySet()){
				if(!visitados.contains(p)){
					visitados.add(p);
					fila.addLast(p);
					resposta.add(new Tupla(n1.id, p.id));
				}else if(fila.contains(p)){
					resposta.add(new Tupla(p.id, n1.id));
				}
			}
			fila.removeFirst();
		}
		System.out.println(resposta);
		return resposta;	
	}
	

	
}
