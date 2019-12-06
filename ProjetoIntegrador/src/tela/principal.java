package tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.XChartPanel;
import com.toedter.calendar.JDateChooser;

import tratamentoDeDados.DadosFiltrados;
import tratamentoDeDados.Evasao;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class principal extends JFrame {
	
	private CategoryChart grafico;
	private JPanel contentPane;
	private JComboBox comboBoxCurso = new JComboBox();
	private JDateChooser caledarioComeco = new JDateChooser();
	private JDateChooser caledarioFim = new JDateChooser();
	private XChartPanel<CategoryChart> painelGrafico;
	private JTextArea textLegenda = new JTextArea();
	private DadosFiltrados dados;

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
	
	private void montarGrafico() {
		ArrayList<String> nomes = new ArrayList<String>();
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		
//		nomes.add("Outros Motivos");             
//		nomes.add("Dificuldades Financeiras");      
//		nomes.add("Não se identificou com o curso");
//		nomes.add("Não se adaptou a metodologia");  
//		nomes.add("Motivo de Trabalho");            
//		nomes.add("Motivo de Estudo");            
//		nomes.add("Motivo de Saúde");          
//		nomes.add("Motivo de Viagens");            
//		nomes.add("Mudança de Município");          
//		nomes.add("Cancelamento da Programação");  
//		nomes.add("Adiamento da Programação");  
//		nomes.add("Incompatibilidade de horário");
//		nomes.add("Distância / meio de transporte");
//		nomes.add("Cancelamento de matr¿cula");
//		nomes.add("Transferência de Programação");
//		nomes.add("Abandono sem Justificativa");
//		nomes.add("Alteração de Horário");  
		nomes.add("Sem dados"); 
		
		
		ArrayList<String> legenda = legendaArrayList(nomes);
		 
		for (int i = 0; i < nomes.size(); i++) {
			numeros.add(i);
		}
		
		arumarLegenda(nomes, legenda);
		grafico = new CategoryChart(30, 30);
		grafico.addSeries("Motivos Evasão", legenda, numeros);
	}
	
	private void atualisarGrafico(DadosFiltrados dados) {
		ArrayList<String> nomes = dados.getMotivoEvasao();
		ArrayList<Integer> numeros = dados.getFrequenciaEvasao();
		ArrayList<String> legenda = legendaArrayList(nomes);
		arumarLegenda(nomes, legenda);
		
		grafico.updateCategorySeries("Motivos Evasão", nomes, numeros, null);
		painelGrafico.repaint();
	}
	
	private ArrayList<String> legendaArrayList(ArrayList<String> nomes){
		ArrayList<String> legenda = new ArrayList<String>();
		for (int i = 0; i < nomes.size(); i++) {
			legenda.add("Motivo " + (i+1));
		}
		return legenda;
	}
	
	private void arumarLegenda(ArrayList<String> nomes,ArrayList<String> legenda) {
		String le = "";
		for (int i = 0; i < nomes.size(); i++) {
			le += legenda.get(i) + " - " + nomes.get(i) + "\n";
		}
		textLegenda.setText(le);
	}
	
	private void arumarTextRelatorio(DadosFiltrados dados) {
		//textArea.setText("teste teste\nteste");
	}

	/**
	 * Create the frame.
	 */
	public principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		panelMenu.setLayout(new GridLayout(2, 1, 0, 0));
		GridBagConstraints gbc_lblDataComeo = new GridBagConstraints();
		
		JPanel panelOp = new JPanel();
		panelMenu.add(panelOp);
		panelOp.setLayout(new GridLayout(12, 1, 0, 0));
		
		JLabel lblCurso = new JLabel("Curso                          ");
		panelOp.add(lblCurso);
		panelOp.add(comboBoxCurso);
		
		JLabel lblDataComeo = new JLabel("Data come\u00E7o");
		panelOp.add(lblDataComeo);
		panelOp.add(caledarioComeco);
		
		caledarioComeco.setDateFormatString("dd/MM/yy");
		
		JLabel lblDataFim = new JLabel("Data fim");
		panelOp.add(lblDataFim);
		panelOp.add(caledarioFim);
		
		caledarioFim.setDateFormatString("dd/MM/yy");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelMenu.add(scrollPane_1);
		
		textLegenda.setEditable(false);
		scrollPane_1.setViewportView(textLegenda);
		
		JPanel panelDados = new JPanel();
		contentPane.add(panelDados, BorderLayout.CENTER);
		panelDados.setLayout(new GridLayout(2, 1, 0, 0));
		
		montarGrafico();
		painelGrafico = new XChartPanel<CategoryChart>(grafico);
		
		//panelDados.add(painelGrafico);
		
		JScrollPane scrollPane = new JScrollPane();
		panelDados.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}

}
