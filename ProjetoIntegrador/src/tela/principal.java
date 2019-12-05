package tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.GridLayout;

public class principal extends JFrame {

	private JPanel contentPane;
	JComboBox comboBoxCurso = new JComboBox();
	JDateChooser caledarioComeco = new JDateChooser();
	JDateChooser caledarioFim = new JDateChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal frame = new principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		menuBar.add(btnAtualizar);
		
		JButton btnCarregarCsv = new JButton("Carregar CSV");
		menuBar.add(btnCarregarCsv);
		
		JMenu menu = new JMenu("");
		menu.setEnabled(false);
		menuBar.add(menu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panelMenu = new JPanel();
		contentPane.add(panelMenu, BorderLayout.WEST);
		
		JLabel lblCurso = new JLabel("Curso");
		panelMenu.setLayout(new GridLayout(10, 1, 0, 0));
		panelMenu.add(lblCurso);
		
		
		panelMenu.add(comboBoxCurso);
		
		JLabel lblDataComeo = new JLabel("Data come\u00E7o");
		GridBagConstraints gbc_lblDataComeo = new GridBagConstraints();
		panelMenu.add(lblDataComeo);
		
		
		caledarioComeco.setDateFormatString("dd/MM/yy");
		panelMenu.add(caledarioComeco);
		
		JLabel lblDataFim = new JLabel("Data fim");
		panelMenu.add(lblDataFim);
		
		
		caledarioFim.setDateFormatString("dd/MM/yy");
		panelMenu.add(caledarioFim);
	}

}
