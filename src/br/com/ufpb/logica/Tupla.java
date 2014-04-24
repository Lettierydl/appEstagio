package br.com.ufpb.logica;

public class Tupla {

	public int id_n1;
	public int id_n2;
	
	public Tupla(int id1, int id2){
		id_n1 = id1;
		id_n2 = id2;
	}

	@Override
	public String toString() {
		return "("+id_n1 + " -> " + id_n2 + ")";
	}
	
	
	
}
