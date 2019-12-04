package teste;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import tratamentoDeDados.DadosFiltrados;
import tratamentoDeDados.Evasao;

public class NovoTeste {
	public static void main(String[] args) {
		ArrayList<Evasao> lista = new ArrayList<Evasao>();
		Evasao eva = new Evasao();
		for (int i = 0; i < 5; i++) {
			eva = new Evasao();
			eva.setCurso("matematica");
			eva.setMotivo("1");
			lista.add(eva);
			eva = new Evasao();
			eva.setCurso("geografia");
			eva.setMotivo("2");
			lista.add(eva);
		}
		
		DadosFiltrados dados = new DadosFiltrados(lista);
		ArrayList<String> cursos = new ArrayList<String>();
		cursos.add("geografia");
		cursos.add("matematica");
		dados.setFiltro(cursos);
		
		String materias = "";
		for (Evasao evasao : dados.getEvasao()) {
			materias += evasao.getMotivo() + "\n";
		}
		JOptionPane.showMessageDialog(null, materias);
	}
}
