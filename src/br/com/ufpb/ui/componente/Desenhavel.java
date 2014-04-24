package br.com.ufpb.ui.componente;

import java.awt.Graphics2D;

import javax.swing.JComponent;

public abstract class Desenhavel extends JComponent {
	private static final long serialVersionUID = 1L;

	public abstract void desenhar(Graphics2D g2d);
	
	
}
