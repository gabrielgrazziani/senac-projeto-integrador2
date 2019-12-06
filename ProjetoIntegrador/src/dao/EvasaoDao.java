package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Evasao;

public class EvasaoDao {
	public static ArrayList<Evasao> listagem() {
		
		try {
			String sql = "select e.motivo_requerimento, e.data_cadastro_requerimento, e.turma, e.area_turma from evasao e";

			Connection conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			ArrayList<Evasao> listaEvasao = new ArrayList<Evasao>();
			while (rs.next()) {
				Evasao e = new Evasao();
				e.setArea(rs.getString("area_turma"));
				e.setCurso(rs.getString("turma"));
				e.setData(rs.getDate("data_cadastro_requerimento"));
				e.setMotivo(rs.getString("motivo_requerimento"));
				listaEvasao.add(e);
			}
			return listaEvasao;
		} catch (Exception e) {
			System.out.print("Erro ao listar! " + e.getMessage());
			return null;
		}
	}
}