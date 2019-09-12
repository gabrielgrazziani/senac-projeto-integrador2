import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;



public class Teste {

	public static void main(String[] args) {
		Locale br = new Locale("pt","Brazil");
		NumberFormat nf = NumberFormat.getInstance(br);
		NumberFormat por = NumberFormat.getPercentInstance(br);
		NumberFormat discreto = NumberFormat.getInstance(br);
		discreto.setMinimumIntegerDigits(3);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumIntegerDigits(3);
		
		int[] teste = {3,90,23,46,2,42,47,37,12,51,11,1,3,3,45,3,4,11,2,8,56,39,22,16,5,52};
		//int[] teste = {5,7,10,15,7,5,23};
			
		ProjetoIntegrador2 dadosT = new ProjetoIntegrador2(teste);
		
		String texto = "";
		
		//int t = Integer.parseInt(nf.format(2));
		texto += ( "    xi      |    fi     |     Fi    |     fr    |     Fr    |    xi*fi  |   (xi-x)²*fi " + "\n");
		for(int i = 0;i<dadosT.getVar_xi().length;i++) {
			texto += ( discreto.format(dadosT.getVar_xi()[i])+" | "+discreto.format(dadosT.getVar_fi()[i])+" | "+discreto.format(dadosT.getVar_Fi()[i])+" | "+por.format(dadosT.getVar_fr()[i])+" | "+nf.format(dadosT.getVar_Fr()[i])
					+"% | "+ discreto.format(dadosT.getVar_xi_fi()[i])+" | "+nf.format(dadosT.getVar_xi_media_fi()[i]) + "\n");
		}
		
		
		
		texto +=("\nSx² = " + nf.format(dadosT.getVariancia() ) + "\n");
		texto += ("1Sx = " + nf.format(dadosT.getDesvioPadrao()) + "\n");
		texto += ("Media = " + dadosT.getMedia() + "\n");
		JOptionPane.showMessageDialog(null,texto);
	}

}
