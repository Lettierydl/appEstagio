package br.com.ufpb.logica;

import java.util.LinkedList;
import java.util.List;

public class Grafo {

	private List<No> nos;

	public Grafo() {
		nos = new LinkedList<No>();
	}

	public boolean adicionarNo(int id) {
		if(getNo(id)!= null){
			return false;
		}
		return nos.add(new No(id));
	}

	No getNo(int id){
		for(No n : nos){
			if(n.id == id){
				return n;
			}
		}
		return null;
	}
	
	public boolean adicionarAresta(int id1, int id2, int valor) {
		No n1 = getNo(id1);
		No n2 = getNo(id2);
		if(n1 != null && n2 != null){
			return n1.adicionarAresta(n2, valor);
		}
		return false;
	}

}
