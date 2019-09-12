

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class ProjetoIntegrador2 {
	
	private int var_fi[];
	private int var_xi[];
	private int var_Fi[];
	private float[] var_fr;
	private float[] var_Fr;
	private int[] var_xi_fi;
	private float media;
	private float variancia;
	private float desvioPadrao;
	
	private float[] var_xi_media_fi;

	public int[] getVar_fi() {
		return var_fi;
	}
	public int[] getVar_xi() {
		return var_xi;
	}

	public int[] getVar_Fi() {
		return var_Fi;
	}

	public float[] getVar_fr() {
		return var_fr;
	}

	public float[] getVar_Fr() {
		return var_Fr;
	}

	public int[] getVar_xi_fi() {
		return var_xi_fi;
	}

	public float[] getVar_xi_media_fi() {
		return var_xi_media_fi;
	}
	public float getMedia() {
		return media;
	}

	public float getDesvioPadrao() {
		return desvioPadrao;
	}

	public float getVariancia() {
		return variancia;
	}
	public ProjetoIntegrador2(int[] teste) {
			
		teste = agruparNumeros(teste);
		
		this.var_fi = quantidadeNumeros(teste);
		this.var_xi = repetidoNumeros(teste);
		this.var_Fi = calculo_Fi(this.var_fi);
		this.var_fr = calculo_fr(this.var_fi);
		this.var_Fr = calculo_Fr(var_fr);
		this.var_xi_fi = calculo_xi_fi(this.var_fi,this.var_xi);
		this.media = somaVetor(this.var_xi_fi)/teste.length;
		this.var_xi_media_fi = qsw(this.var_xi,this.var_fi,media);
		this.variancia = somaVetor(this.var_xi_media_fi)/(teste.length -1);
		this.desvioPadrao = (float) Math.pow(this.variancia,0.5);
		
	}
	
	private static float[] qsw(int[] xi,int[] fi,float media) { //calcula ((xi - media)^2)*fi
		float[] qsw = new float[xi.length];
		for(int i = 0;i<xi.length;i++) {
			qsw[i] = (float) (Math.pow((xi[i] - media), 2)*fi[i]);
		}
		return qsw;
	}
	
	
	private static int[] calculo_xi_fi(int[] xi ,int[] fi) {
		int[] var_xi_fi = new int[xi.length];
		for(int i = 0; i<xi.length;i++) {
			var_xi_fi[i] = xi[i]*fi[i];
		}
		return var_xi_fi;	
	}
	
	private static int[] agruparNumeros(int[] numeros) {
		int temporario; 
		for(int i = 0; i < numeros.length - 1; i++) {
			for(int n = i + 1; n < numeros.length; n++) {
				if (numeros[i] > numeros[n]) {
					temporario = numeros[i];
					numeros[i] = numeros[n];
					numeros[n] = temporario;		
				}
			}
		}
		return numeros;		
	}
	
	private static String[] agruparNomes(String[] nomes) {
		String verificando, temporario;
		int quantidade, i = 0;
		while(i < nomes.length) {
			quantidade = i + 1;
			for(int n = i + 1; n< nomes.length; n++) {
				if (nomes[i].equalsIgnoreCase(nomes[n])) {
					temporario = nomes[quantidade];
					nomes[quantidade] = nomes[n];
					nomes[n] = temporario;
					quantidade++;
				}
			}
			i = quantidade;
		}
		return nomes;		
	}
	
	private static int[] quantidadeNomes(String[] nomes) {
		String atual = nomes[0];
		int soma = 1;
		for(int i = 1; i < nomes.length; i++) {
			if(!atual.equalsIgnoreCase(nomes[i])) {
				atual = nomes[i];
				soma++;
			}
		}
		int[] quantidade = new int[soma];
		
		int colocar = 0;
		atual = nomes[0];
		quantidade[0] = 1;
		for(int i = 1; i < nomes.length; i++) {
			if(!atual.equalsIgnoreCase(nomes[i])) {
				atual = nomes[i];
				colocar++;
			}
			quantidade[colocar]++;
		}
		return(quantidade);
	}
	
	private static float[] calculo_fr(int[] fi) {
		float quant = somaVetor(fi);
		float[] fr = new float[(int) quant];
		for(int i = 0;i<fi.length;i++) {
			//fr[i] = arredondar((fi[i]*100)/quant, 2, 0);
			fr[i] = (fi[i]*100/quant);
		}
		return fr;
	}
	
	public static float somaVetor(float[] numero) {
		float soma = 0;
		for(int i = 0; i<numero.length;i++) {
			soma += numero[i];
		}
		return soma;
	}
	
	public static float somaVetor(int[] numero) {
		float soma = 0;
		for(int i = 0; i<numero.length;i++) {
			soma += numero[i];
		}
		return soma;
	}
	
	private static int[] quantidadeNumeros(int[] numero) {
		int atual = numero[0];
		int soma = 1;
		for(int i = 1; i < numero.length; i++) {
			if(!(atual == numero[i])) {
				atual = numero[i];
				soma++;
			}
		}
		int[] quantidade = new int[soma];
		
		int colocar = 0;
		atual = numero[0];
		quantidade[0] = 1;
		for(int i = 1; i < numero.length; i++) {
			if(!(atual == numero[i])) {
				atual = numero[i];
				colocar++;
			}
			quantidade[colocar]++;
		}
		return(quantidade);
	}
	
	private static int[] calculo_Fi(int[] fi) {
		int[] Fi = new int[fi.length];
		Fi[0] = fi[0];
		for(int i = 1; i<fi.length;i++) {
			Fi[i] = fi[i] + Fi[i-1];
		}
		return Fi;
	}
	
	private static float[] calculo_Fr(float[] fr) {
		float[] Fr = new float[fr.length];
		Fr[0] = fr[0];
		for(int i = 1; i<fr.length;i++) {
			Fr[i] = fr[i] + Fr[i-1];
		}
		return Fr;
	}
	
	private static String[] repetidoNomes(String[] nomes) {
		String atual = nomes[0];
		int soma = 1;
		for(int i = 1; i < nomes.length; i++) {
			if(!atual.equalsIgnoreCase(nomes[i])) {
				atual = nomes[i];
				soma++;
			}
		}
		String[] repeti = new String[soma];
		
		int colocar = 0;
		atual = nomes[0];
		repeti[0] = nomes[0];
		for(int i = 1; i < nomes.length; i++) {
			if(!atual.equalsIgnoreCase(nomes[i])) {
				atual = nomes[i];
				colocar++;
				repeti[colocar] = nomes[i];
			}
		}
		return(repeti);
	}
	
	private static int[] repetidoNumeros(int[] numero) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		
		int atual = numero[0];
		int soma = 1;
		for(int i = 1; i < numero.length; i++) {
			if(!(atual == numero[i])) {
				atual = numero[i];
				soma++;
			}
		}
		int[] repeti = new int[soma];
		
		int colocar = 0;
		atual = numero[0];
		repeti[0] = numero[0];
		for(int i = 1; i < numero.length; i++) {
			if(!(atual == numero[i])) {
				atual = numero[i];
				colocar++;
				repeti[colocar] = numero[i];
			}
		}
		return(repeti);
	}
}
