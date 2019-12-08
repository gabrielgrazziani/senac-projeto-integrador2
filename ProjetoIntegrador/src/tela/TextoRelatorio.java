package tela;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import tratamentoDeDados.DadosFiltrados;

public class TextoRelatorio {
	private String texto = "";
	private DadosFiltrados dados;
	private ArrayList<String> cursos;

	public String getTexto() {
		return texto;
	}
	
	public TextoRelatorio(DadosFiltrados dados,ArrayList<String> cursos) {
		this.dados = dados;
		this.cursos = cursos;
		String tep = "";
		if(dados.getMotivoEvasao().size() > 0) {
			String cur = cursos(cursos);
			String moda = listaParaString(dados.getModa(),"","\"");
			String periodo = periodo();
			tep += inicio(cur, periodo, moda);
			tep += listaParaString(porcentagemTexto(), "\n","");
		}
		texto = tep;
	}
	
	private String inicio(String cur,String periodo,String moda) {
		String tep = "Dos dados apresentados " + cur +
				periodo + ", a moda de motivos de evas�o foi " + moda + ".\n";
		return tep;
	}
	
	private String cursos(ArrayList<String> lista) {
		String tep = "";
		if(lista != null){
			if(lista.size() > 1) {
				tep += "dos cursos ";
			}
			else if(lista.size() == 1) {
				tep += "do curso ";
			}
			String cur = listaParaString(cursos,"","\"");
			tep += cur;
		}
		return tep;
	}
	
	private ArrayList<String> porcentagemTexto() {
		ArrayList<String> texto = new ArrayList<String>();
		for (int i = 0; i < dados.getMotivoEvasao().size(); i++) {
			String tep = dados.getFrequenciaEvasao().get(i) + " pessoas Sa�ram pelo motivo \"" + 
					dados.getMotivoEvasao().get(i) + "\" representando " + 
					dados.getFrequenciaPorcentagemEvasao().get(i) + "%";
			texto.add(tep);
		}
		return texto;
	}
	
	private String listaParaString(ArrayList<String> lista,String pulaLinha,String aspas) {
		String tep = "";
		if(lista != null) {
			for (int i = 0; i < lista.size(); i++){
				tep += aspas  + lista.get(i) + aspas;
				if(i < lista.size() -2) {
					tep += ", ";
				}
				else if(i == lista.size() -2){
					tep += " e ";
				}
				tep += pulaLinha;
			}
		}
		return tep;
	}
	
	private String periodo() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar fim = dados.getDataFim();
		Calendar inicio = dados.getDataInicio();
		String tep = "";
		if(fim == null && inicio != null) {
			tep += " a partir da data " + sdf.format(inicio.getTime()) ;
		}
		else if(fim != null && inicio == null){
			tep += " anteriores a data " + sdf.format(fim.getTime());
		}
		else if(fim != null && inicio != null) {
			tep += " entre as datas " + sdf.format(inicio.getTime()) + "e" + sdf.format(fim.getTime());
		}
		return tep;
	}
}
