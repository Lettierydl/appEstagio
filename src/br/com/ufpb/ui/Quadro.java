package br.com.ufpb.ui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.ufpb.logica.Facade;
import br.com.ufpb.logica.Tupla;
import br.com.ufpb.ui.componente.ArestaDesenhavel;
import br.com.ufpb.ui.componente.NoDesenhavel;

public class Quadro extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private boolean arestasComPeso = false;
	private boolean execultandoAnimacao = false;
	
	
	private Facade f;
	
	private JLabel labelResposta;
	
	private Graphics2D g2d;
	private List<ArestaDesenhavel> arestas;
	private List<NoDesenhavel> nos;
	
	private NoDesenhavel selecionado1 = null;
	private NoDesenhavel selecionado2 = null;
	
	
	public Quadro(){
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		arestas= new LinkedList<ArestaDesenhavel>();
		nos= new LinkedList<NoDesenhavel>();
		f = Facade.getInstace();
		this.labelResposta = new JLabel("");
		this.add(this.labelResposta,BorderLayout.SOUTH);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		for(ArestaDesenhavel a: arestas){
			a.desenhar(g2d);
		}
		for(NoDesenhavel n: nos){
			n.desenhar(g2d);
		}
	}

	public void click(MouseEvent e){
		if(!addNoDesenhavel(e)){
			selecionaNoDesenhavel(e);
			if(this.selecionado1 != null && this.selecionado2 != null){
				addArestaDesenhavel();
			}
		}
		new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}.start();
	}

	public void configurarEntradas(){
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(execultandoAnimacao){
					return;
				}
				click(e);
				if(f.algoritmoJaSelecionado()){
					execultarAnimacao(selecionado1);
				}
				f.limparSelecaoDoAlgoritm();
			}
			});
	}
	
	public NoDesenhavel getNo(int id){
		for(NoDesenhavel no : nos){
			if(no.getId() == id){
				return no;
			}
		}
		return null;
	}
	
	public void execultarAnimacao(NoDesenhavel noRaiz){
		final List<Tupla> resposta = f.getRespostaAlgoritmo(noRaiz.getId());
		execultandoAnimacao = true;
		new Thread(){
			@Override
			public void run() {
				try {
					execultandoAnimacao = true;
					String resp = "Resposta: | ";
					for(Tupla t : resposta){
						NoDesenhavel n1 = getNo(t.id_n1);
						n1.setAnimar(true);
			
						
						NoDesenhavel n2 = getNo(t.id_n2);
						n2.setAnimar(true);
						resp += n1.getNome() + " <-> " + n2.getNome()+ " | ";
						labelResposta.setText(resp);
						repaint();
						Thread.sleep(1500);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(Tupla t : resposta){
					getNo(t.id_n1).setAnimar(false);
					getNo(t.id_n2).setAnimar(false);
				}
				execultandoAnimacao = false;
				repaint();
			}
		}.start();
	}
	
	private void addArestaDesenhavel() {
		ArestaDesenhavel a = new ArestaDesenhavel(this.selecionado1 , this.selecionado2 );
		for(ArestaDesenhavel ar : arestas){
			if(ar.equals(a)){
				this.selecionado1.selecionar();
				this.selecionado2.selecionar();
				return;
			}
		}
		if(arestasComPeso){
			String valor = JOptionPane.showInputDialog("Digite o Peso desta Arestas.");
			try{
				a.setValor(Integer.valueOf(valor));
			}catch(NumberFormatException ne){
				a.setValor(0);
			}
		}else{
			a.setValor(0);
		}
		arestas.add(a);
		if(!f.adicionarAresta(this.selecionado1.getId(), this.selecionado2.getId(), a.getValor())){
			System.out.println("deu merda na aresta com n1="+selecionado1.getId());
		}
		this.selecionado1.selecionar();
		this.selecionado2.selecionar();
		this.selecionado1 = this.selecionado2 = null;
	}
	
	private boolean addNoDesenhavel(MouseEvent e){
		NoDesenhavel n = new NoDesenhavel(e.getPoint().x,e.getPoint().y);
		for(NoDesenhavel nd: nos){
			if(nd.existeColisao(n)){
				return false;
			}
		}
		nos.add(n);
		if(!f.adicionarNo(n.getId())){
			System.out.println("deu merda no nó id="+n.getId());
		}
		return true;
	}
	
	private void selecionaNoDesenhavel(MouseEvent e) {
		for(NoDesenhavel nd: nos){
			if(nd.foiSelecionado(e.getPoint().x, e.getPoint().y)){
				nd.selecionar();
				if(nd.estarSelecionado()){
					if(this.selecionado1 == null){
						selecionado1 = nd;
					}else{
						selecionado2 = nd;
					}
					return;
				}
				if(selecionado1 == nd){
					selecionado1 = null;
				}
				return;
			}
		}
	}
}
