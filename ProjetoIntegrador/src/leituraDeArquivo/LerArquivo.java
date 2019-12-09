package leituraDeArquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.EvasaoCSV_Dao;
import modelo.EvasaoCSV;

public class LerArquivo {

	private static File escolherArquivo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		String look = UIManager.getSystemLookAndFeelClassName();
		UIManager.setLookAndFeel(look);
		
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Procurar CSV");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
		chooser.setFileFilter(filter);
	    int ret = chooser.showOpenDialog(null);
	    
	    if(ret == chooser.APPROVE_OPTION) {
	    	File file = chooser.getSelectedFile();
	    	return file;
	    }
	    else {
	    	JOptionPane.showMessageDialog(null, "Arquivo n�o selecionado");
	    	return null;
	    }
	}
	
	public static void main(String[] args) {
		lerEInserir();
	}
	
	public static void lerEInserir() {
		try {
			File file = escolherArquivo();
			if(file != null) {
				ArrayList<EvasaoCSV> lista = ler(file);
				Object[] options = { "Adicionar", "Sobrescrever" };
				int op = JOptionPane.showOptionDialog(null, "Deseja adicionar os dados deste arquivo\n aos dados atuais, ou sobrescrev�-los? ", "Arquivo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				if(op == 0) { // Adicionar
					adicinar(lista);
				}
				else if (op == 1) { // Sobrescrever
					
				}
				JOptionPane.showMessageDialog(null, "Pronto");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao ler o arquivo.");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static void adicinar(ArrayList<EvasaoCSV> lista) {
		try {
			EvasaoCSV_Dao.inserir(lista);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	private static ArrayList<EvasaoCSV> ler(File arq) throws Exception {
		ArrayList<EvasaoCSV> lista = new ArrayList<EvasaoCSV>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		EvasaoCSV csv;
		if(arq.exists()) {
				//FileReader fr = new FileReader(arq);
				//BufferedReader br = new BufferedReader(fr);
				FileInputStream fis = new FileInputStream(arq);
				InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				br.readLine();
				while (br.ready()) {
					csv = new EvasaoCSV();
					String linha = br.readLine();
					String[] campos = linha.split(",");
					csv.setTurno(campos[0]);
					csv.setEstadoCivil(campos[1]);
					csv.setSexo(campos[2]);
			    	Date data = sdf.parse(campos[3]); 
					csv.setData(data);
					csv.setMotivo(campos[4]);
					csv.setCurso(campos[5]);
					csv.setArea(campos[6]);
					lista.add(csv);
				}
				br.close();
				fis.close();
		}
		return lista;
	}

}
