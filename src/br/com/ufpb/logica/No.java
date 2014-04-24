package br.com.ufpb.logica;

import java.util.HashMap;

public class No {
	
	public int id;
	public HashMap<No, Integer> arestas;
	
	public No(int id){
		arestas = new HashMap<No, Integer>();
		this.id = id;
	}
	
	public boolean adicionarAresta(No no2, int valor){
		if(!arestas.containsValue(no2)){
			arestas.put(no2,valor);
			no2.arestas.put(this, valor);
			return true;
		}else{
			return false;
		}
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		No other = (No) obj;
		if (arestas == null) {
			if (other.arestas != null)
				return false;
		} else if (!arestas.equals(other.arestas))
			return false;
		return true;
	}
	
	
	
}
