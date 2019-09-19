package teste;

import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JOptionPane;
import tratamentoDeDados.DadosQualitativos;
import tratamentoDeDados.DadosQuantitativos;

public class teste2 {

	public static void main(String[] args) {
		Locale br = new Locale("pt","Brazil");
		NumberFormat nf = NumberFormat.getInstance(br);
		NumberFormat discreto = NumberFormat.getInstance(br);
		discreto.setMinimumIntegerDigits(3);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumIntegerDigits(3);
		
		
		String[] teste = {"gabriel","marcelo","gabriel","gabriel","diego","matheus","matheus"};
			
		DadosQualitativos dadosT = new DadosQualitativos(teste);
		
		String texto = "";
	
		texto += ( "    xi      |    fi     |     Fi    |     fr    |     Fr    |" + "\n");
		for(int i = 0;i<dadosT.getVar_xi().length;i++) {
			texto += ( dadosT.getVar_xi()[i]+" | "+discreto.format(dadosT.getVar_fi()[i])+" | "+discreto.format(dadosT.getVar_Fi()[i])+" | "+nf.format(dadosT.getVar_fr()[i])+"% | "+nf.format(dadosT.getVar_Fr()[i])
			+"% | " + "\n");
		}
		texto += ("\nModa = ");
		for(int i = 0;i < dadosT.getModa().length;i++) {
			texto += (dadosT.getModa()[i] + " | ");
		}
		JOptionPane.showMessageDialog(null,texto);
	}

}
