package tratamentoDeDados;

import java.util.ArrayList;

public class DadosFiltrados {
	private ArrayList<Evasao> evasaoOriginal;
	private ArrayList<Evasao> evasao;
	private ArrayList<String> motivoEvasao = new ArrayList<String>();
	private ArrayList<Integer> frequenciaEvasao = new ArrayList<Integer>();
	
	public DadosFiltrados(ArrayList<Evasao> evasao) {
		this.evasaoOriginal = evasao;
		this.evasao = new ArrayList<Evasao>();
		this.evasao = (ArrayList<Evasao>) this.evasaoOriginal.clone();
		calcularDados(evasao);
	}
	
	public void filtroCurso(String curso) { //falta null
		this.evasao.clear();
		for (Evasao evasao2 : this.evasaoOriginal) {
			if(evasao2.getCurso().equals(curso)) {
				evasao.add(evasao2);
			}
		}
		calcularDados(evasao);
	}
	
	private void calcularDados(ArrayList<Evasao> evasao) {
		if(evasao.size() > 0 ) {
			String[] motivos = comverteListaDeEvasao(evasao);
			DadosQualitativos quali = new DadosQualitativos(motivos);
			setMotivoEvasao(quali.getVar_xi());
			setFrequenciaEvasao(quali.getVar_fi());
		}
		else {
			motivoEvasao.clear();
			frequenciaEvasao.clear();
		}
	}
	
	public ArrayList<Evasao> getEvasao() {
		return evasao;
	}

	public void setEvasao(ArrayList<Evasao> evasao) {
		this.evasao = evasao;
	}

	public ArrayList<String> getMotivoEvasao() {
		return motivoEvasao;
	}

	public ArrayList<Integer> getFrequenciaEvasao() {
		return frequenciaEvasao;
	}
	
	public static String[] comverteListaDeEvasao(ArrayList<Evasao> evasao) {
		ArrayList<String> lista = new ArrayList<String>();
		for (Evasao evasao2 : evasao) {
			lista.add(evasao2.getMotivo());
		}
		return lista.toArray( new String[lista.size()]);
	}
	
	private void setMotivoEvasao(String[] novo) {
		this.motivoEvasao.clear();
		for (String string : novo) {
			this.motivoEvasao.add(string);
		}
	}
	
	private void setFrequenciaEvasao(int[] novo) {
		this.frequenciaEvasao.clear();
		for (int num : novo) {
			this.frequenciaEvasao.add(num);
		}
	}
}
