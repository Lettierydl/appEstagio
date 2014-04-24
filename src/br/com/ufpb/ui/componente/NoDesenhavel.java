package br.com.ufpb.ui.componente;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

public class NoDesenhavel extends Desenhavel {

	private static final long serialVersionUID = 1L;
	public static int CONCENTRADOR_ID = 0;
	public static byte CONCENTRADOR_NOME = 'A';
	
	public static final int DIAMETRO = 30;
	public static final int DISTANCIA_NOS = 30;
	
	private Ellipse2D ellipse;
	private int x;
	private int y;
	private boolean selecionado = false;
	private String nome;
	private int id;
	
	private double diametrow = DIAMETRO;
	private double diametroh = DIAMETRO;
	private boolean animar = false;
	private double variante = 0;
	private Color cor = Color.BLACK;
	

	public NoDesenhavel(int x, int y){
		this.x = (int) (x - (diametrow/2));
		this.y = (int) (y - (diametroh/2));
		ellipse = new Ellipse2D.Double(this.x, this.y, diametrow, diametroh);
		id = CONCENTRADOR_ID++;
		byte[] bytes = {CONCENTRADOR_NOME++};
		nome = new String(bytes);
	}
	
	public boolean aumentar = false;
	public void animar(){
		if(animar){
			cor = new Color(255,0,0);
		}
	}
	
	public void desenhar(Graphics2D g2d) {
		Stroke i = g2d.getStroke();
		BasicStroke select = new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f);
		if(selecionado){
		    g2d.setStroke(select);
		}
		animar();
		g2d.setColor(cor);
		g2d.draw(ellipse);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(select);
		if(nome !=null && !nome.isEmpty()){
			g2d.drawString(nome, (int)getCenterX()-3, (int)getCenterY()+3);
		}
		g2d.setStroke(i);
	}

	public boolean existeColisao(NoDesenhavel n) {
		double distancia = Math.sqrt( Math.pow(n.x - this.x, 2) + Math.pow(n.y - this.y, 2) );
		if(distancia <= (DIAMETRO+DISTANCIA_NOS)){
			return true;
		}
		return false;
	}
	
	public boolean foiSelecionado(int x, int y) {
		double distancia = Math.sqrt( Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) );
		if(distancia <= (DIAMETRO)){
			return true;
		}
		return false;
	}
	
	public void selecionar(){
		selecionado = !selecionado;
	}

	public boolean estarSelecionado() {
		return selecionado;
	}

	public double getCenterX(){
		return ellipse.getCenterX();
	}
	
	public double getCenterY(){
		return ellipse.getCenterY();
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getId() {
		return id;
	}

	public boolean isAnimar() {
		return animar;
	}

	public void setAnimar(boolean animar) {
		this.animar = animar;
	}
	
	
	
}
