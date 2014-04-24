package br.com.ufpb.ui.componente;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;

import javax.swing.plaf.FontUIResource;

public class ArestaDesenhavel extends Desenhavel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static int VARIACAO_DAS_CORES = 20;

	private NoDesenhavel n1, n2;
	
	private Integer valor = null;
	
	private Color cor;
	
	private Line2D linha;
	
	public ArestaDesenhavel(NoDesenhavel n1, NoDesenhavel n2){
		this.n1 = n1;
		this.n2 = n2;
		linha =  new Line2D.Double(n1.getCenterX(), n1.getCenterY(),
								   n2.getCenterX(), n2.getCenterY());
		cor = getColor();
	}
	
	
	
	@Override
	public void desenhar(Graphics2D g2d) {
		Stroke i = g2d.getStroke();
		
		BasicStroke select = new BasicStroke(1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f);
		g2d.setColor(cor);
		g2d.setStroke(select);
		
		g2d.draw(linha);
		if(valor != null){
			int x = (int) ((n1.getCenterX() + n2.getCenterX()) / 2)-10;
			int y = (int) ((n1.getCenterY() + n2.getCenterY()) / 2)-10;
			Font inicial = g2d.getFont();
			g2d.setFont(new FontUIResource("georgia", Font.PLAIN, 18));
			g2d.drawString(valor.toString(), x, y);
			g2d.setFont(inicial);
		}
		g2d.setColor(Color.BLACK);
		g2d.setStroke(i);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((n1 == null) ? 0 : n1.hashCode());
		result = prime * result + ((n2 == null) ? 0 : n2.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArestaDesenhavel other = (ArestaDesenhavel) obj;
		if (n1 == null) {
			if (other.n1 != null)
				return false;
		} else if (!n1.equals(other.n1))
			return false;
		if (n2 == null) {
			if (other.n2 != null)
				return false;
		} else if (!n2.equals(other.n2))
			return false;
		return true;
	}
	
	private static Color getColor(){
		int r = VARIACAO_DAS_CORES % 255;
		int g = ( VARIACAO_DAS_CORES * 23 ) % 255;
		int b = ( VARIACAO_DAS_CORES * 7 ) % 255;
		VARIACAO_DAS_CORES += 25;
		return new Color(r,g,b);
	}



	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	
	
}
