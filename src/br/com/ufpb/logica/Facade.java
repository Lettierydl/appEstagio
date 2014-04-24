package br.com.ufpb.logica;

import java.util.List;

public class Facade {
	
	private static Facade fac = null;
	private Algoritmo alg;
	
	private Grafo g;

	private Facade(){
		g = new Grafo();
	}
	
	public static Facade getInstace(){
		if(fac == null){
			fac =  new Facade();
		}
		return fac;
	}
	
	public boolean adicionarNo(int id){
		return g.adicionarNo(id);
	}
	
	public boolean adicionarAresta(int id_n1, int id_n2, int valor){
		return g.adicionarAresta(id_n1, id_n2, valor);
	}
	
	public boolean existeNo(int id){
		return g.getNo(id) != null;
	}
	
	public List<Tupla> getRespostaAlgoritmo(int idNoRaiz){
		return alg.getResposta(g.getNo(idNoRaiz));
	}
	
	public boolean algoritmoJaSelecionado(){
		return this.alg !=null;
	}
	
	public void limparSelecaoDoAlgoritm(){
		this.alg = null;
	}
	
	public void selecionarAlgoritmo(TipoDeAlgoritmo tipoDeAlgoritmo) {
		switch (tipoDeAlgoritmo) {
		case BUSCA_EM_LARGURA :
			this.alg = new BuscaEmLargura(g);
			break;
			
		case BUSCA_EM_PROFUNDIDADE :
			this.alg = new BuscaEmProfundidade(g);
			break;

		default:
			break;
		}
		
	}
	
}
