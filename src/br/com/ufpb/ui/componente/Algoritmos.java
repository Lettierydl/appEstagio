package br.com.ufpb.ui.componente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultComboBoxModel;

import br.com.ufpb.logica.Algoritmo;
import br.com.ufpb.logica.Facade;
import br.com.ufpb.logica.TipoDeAlgoritmo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Algoritmos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblSelecioneOAlgoritmo;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Algoritmos dialog = new Algoritmos(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Algoritmos(JFrame frame) {
		super(frame, true);
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);

		lblSelecioneOAlgoritmo = new JLabel("Selecione o Algoritmo que Deseja Execultar");

		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPanel.add(lblSelecioneOAlgoritmo);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Selecionar No Raiz");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setarAlgoritmo();
			}
		});
		okButton.setToolTipText("Execultar Algoritmo Selecionado");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(TipoDeAlgoritmo.values()));
		comboBox.setToolTipText("Selecione o Algoritmo Desejado");
		getContentPane().add(comboBox, BorderLayout.CENTER);

	}
	
	public void setarAlgoritmo(){
		Facade.getInstace().selecionarAlgoritmo(((TipoDeAlgoritmo)comboBox.getSelectedItem()));
		this.dispose();
	}

}
