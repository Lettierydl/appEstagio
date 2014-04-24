package br.com.ufpb.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import br.com.ufpb.ui.componente.Algoritmos;
import br.com.ufpb.ui.componente.NoDesenhavel;

public class Main {

	/**
	 * @param args
	 */
	
	static JFrame frame;
	public static void main(String[] args) {
		JFrame tela = new JFrame();
		frame = tela;
		configurarFrame(tela);
		adicionarQuadro(tela);
		tela.setBounds(50, 50, 800, 600);
		
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tela.setVisible(true);
	}
	
	public static void adicionarQuadro(JFrame tela){
		Quadro quadro = new Quadro();
		quadro.configurarEntradas();
		tela.setContentPane(quadro);
	}
	
	public static void configurarFrame(JFrame tela){
		JMenuBar menuBar = new JMenuBar();
		tela.setJMenuBar(menuBar);
		
		JButton modoCriacao = new JButton("Criar Grafo");
		menuBar.add(modoCriacao);
		
		JButton modoAlgoritmo = new JButton("Algoritmos");
		modoAlgoritmo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Algoritmos dialog = new Algoritmos(Main.frame);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
			
		});
		menuBar.add(modoAlgoritmo);
		
		JButton limparGrafo = new JButton("Apagar Grafo");
		limparGrafo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[]bt = {"Sim","Não"};
				int res = JOptionPane.showOptionDialog(null, "Deseja Apagar o Grafo", "Apagar Grafo", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE , null, bt, bt[0]);
				if(res == 0){
					reiniciarGrafo();
				}
			}
			
		});
		menuBar.add(limparGrafo); //ainda nao ta testado
	}
	private static void reiniciarGrafo(){
		NoDesenhavel.CONCENTRADOR_ID = 0;
		NoDesenhavel.CONCENTRADOR_NOME = 'A';
		adicionarQuadro(frame);
		frame.setVisible(true);
	}
}
