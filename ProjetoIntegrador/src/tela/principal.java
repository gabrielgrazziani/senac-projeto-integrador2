package tela;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.XChartPanel;

import com.sun.jndi.ldap.Connection;
import com.toedter.calendar.JDateChooser;

import dao.Conexao;
import dao.EvasaoDao;
import leituraDeArquivo.LerArquivo;
import tratamentoDeDados.DadosFiltrados;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class principal extends JFrame {
	
	private CategoryChart grafico;
	private JPanel contentPane;
	private JComboBox comboBoxCurso = new JComboBox();
	private JDateChooser caledarioComeco = new JDateChooser();
	private JDateChooser caledarioFim = new JDateChooser();
	private XChartPanel<CategoryChart> painelGrafico;
	private JTextArea textLegenda = new JTextArea();
	private DadosFiltrados dados;
	private ArrayList<String> cursos = new ArrayList<String>();
	private JTextArea textArea = new JTextArea();

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
		nomes.add("Sem dados"); 
		ArrayList<String> legenda = legendaArrayList(nomes);
		 
		for (int i = 0; i < nomes.size(); i++) {
			numeros.add(i);
		}
		
		arumarLegenda(nomes, legenda, numeros);
		grafico = new CategoryChart(30, 30);
		grafico.addSeries("Motivos Evasão", legenda, numeros);
	}
	
	private void atualisarGrafico(DadosFiltrados dados) {
		ArrayList<String> nomes = dados.getMotivoEvasao();
		ArrayList<Integer> numeros = dados.getFrequenciaEvasao();
		ArrayList<String> legenda = legendaArrayList(nomes);
		arumarLegenda(nomes, legenda, numeros);
		
		grafico.updateCategorySeries("Motivos Evasão", legenda, numeros, null);
		painelGrafico.repaint();
	}
	
	private ArrayList<String> legendaArrayList(ArrayList<String> nomes){
		ArrayList<String> legenda = new ArrayList<String>();
		for (int i = 0; i < nomes.size(); i++) {
			legenda.add("Motivo " + (i+1));
		}
		return legenda;
	}
	
	private void arumarLegenda(ArrayList<String> nomes,ArrayList<String> legenda, ArrayList<Integer> numeros) {
		String le = "";
		for (int i = 0; i < nomes.size(); i++) {
			le += legenda.get(i) + " - " + nomes.get(i) + " - com ( " + numeros.get(i) + " )" + "\n";
		}
		textLegenda.setText(le);
	}
	
	private void arumarTextRelatorio(DadosFiltrados dados, ArrayList<String> cursos) {
		TextoRelatorio tex = new TextoRelatorio(dados, cursos);
		String tep = tex.getTexto();
		textArea.setText(tep);
	}
	
	private Boolean testarConexao() throws SQLException {
		java.sql.Connection c = Conexao.getConnection();
		if(c == null) {
			Object[] options = { "tentar novamente", "sair" };
			int op = JOptionPane.showOptionDialog(null, "falha ao conectar com o banco de dados! ", "falha", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if(op == 0) { // tentar novamente
				return false;
			}
			else if (op == 1) { // sair
				System.exit(0);
			}
		}
		c.close();
		return true;
	}
	
	private ArrayList<String> getListCursosEspolhidos(){
		ArrayList<String> cursos = new ArrayList<String>();
		cursos.add((String) comboBoxCurso.getSelectedItem());
		if(cursos.get(0).equals("todos")){
			cursos = null; 
		}
		return cursos;
	}
	
	private void arumarComboBoxCurso() throws Exception{
		cursos = EvasaoDao.listagemCursos();
		ArrayList<String> list = cursos;
		list.add(0, "todos");
		String[] cur = (String[]) list.toArray(new String[list.size()]);
		
		comboBoxCurso.setModel(new DefaultComboBoxModel(cur));
	}
	
	private void atualizar() {
		Calendar dataInicio = caledarioComeco.getCalendar();
		Calendar dataFim = caledarioFim.getCalendar();
		ArrayList<String> cursosLista = getListCursosEspolhidos();
		dados.setFiltro(dataInicio, dataFim, cursosLista);
		atualisarGrafico(dados);
		arumarTextRelatorio(dados,cursosLista);
	}

	/**
	 * Create the frame.
	 */
	public principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					while (!testarConexao());
					dados = new DadosFiltrados(EvasaoDao.listagem());
					atualisarGrafico(dados);
					arumarTextRelatorio(dados,getListCursosEspolhidos());
					arumarComboBoxCurso();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		menuBar.add(btnAtualizar);
		
		JButton btnCarregarCsv = new JButton("Carregar CSV");
		btnCarregarCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean v = LerArquivo.lerEInserir();
				if(v) {
					dados = new DadosFiltrados(EvasaoDao.listagem());
					try {
						arumarComboBoxCurso();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					atualizar();
				}
			}
		});
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
		
		JLabel lblCurso = new JLabel("Curso                                                                                            ");
		panelOp.add(lblCurso);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setEnabled(false);
		scrollPane_2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelOp.add(scrollPane_2);
		scrollPane_2.setViewportView(comboBoxCurso);
		comboBoxCurso.setModel(new DefaultComboBoxModel(new String[] {"todos", "teste"}));
		comboBoxCurso.setToolTipText("");
		comboBoxCurso.setPreferredSize(new Dimension(100, 21));
		BoundsPopupMenuListener listener = new BoundsPopupMenuListener(true, false);
		comboBoxCurso.addPopupMenuListener(listener);
		
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
		
		panelDados.add(painelGrafico);
		
		JScrollPane scrollPane = new JScrollPane();
		panelDados.add(scrollPane);
		
		
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}

}
