package tratamentoDeDados;

public class DadosQualitativos extends BaseTratamentoDeDados{
	private String var_xi[];
	private String moda[];
	
	public String[] getVar_xi() {
		return var_xi;
	}
	
	public DadosQualitativos(String[] teste) {
		teste = agruparNomes(teste);
		
		this.var_xi = repetidoNomes(teste);
		setFequencia(quantidadeNomes(teste));
		this.moda = super.descobrirModa(var_xi, getVar_fi());
	}
	
	public String[] getModa() {
		return moda;
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
}
