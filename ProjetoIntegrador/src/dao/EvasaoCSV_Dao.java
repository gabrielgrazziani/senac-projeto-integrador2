package dao;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import modelo.EvasaoCSV;


public class EvasaoCSV_Dao {
	public static boolean inserir(EvasaoCSV csv) {
		try {
			String sql = "insert into facudade.evasao(turno_matricula,situacao_civil_aluno,sexo_aluno,data_cadastro_requerimento,motivo_requerimento,turma,area_turma) "
					+ "values (?,?,?,?,?,?,?)";

			Connection conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, csv.getTurno());
			pstm.setString(2, csv.getEstadoCivil());
			pstm.setString(3, csv.getSexo());
			Date data = new Date(csv.getData().getTimeInMillis());
			pstm.setDate(4,data);
			pstm.setString(5, csv.getMotivo());
			pstm.setString(6, csv.getCurso());
			pstm.setString(7, csv.getArea());
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.print("Erro ao inserir! " + e.getMessage());
			return false;
		}
	}
	
	
//	public static boolean inserir(ArrayList<EvasaoCSV> listaCSV) {
//		try {
//			String sql = "insert into evasao(turno_matricula,situacao_civil_aluno,sexo_aluno,data_cadastro_requerimento,motivo_requerimento,turma,area_turma) "
//					+ "values (?,?,?,?,?,?,?)";
//			Connection conn = Conexao.getConnection();
//			PreparedStatement pstm = conn.prepareStatement(sql);
//			int i = 0;
//			for (EvasaoCSV csv : listaCSV) {
//				pstm.setString(1, csv.getTurno());
//				pstm.setString(2, csv.getEstadoCivil());
//				pstm.setString(3, csv.getSexo());
//				Date data = new Date(csv.getData().getTimeInMillis());
//				pstm.setDate(4,data);
//				pstm.setString(5, csv.getMotivo());
//				pstm.setString(6, csv.getCurso());
//				pstm.setString(7, csv.getArea());
//				pstm.addBatch();
//				//System.out.println(csv.getCurso());
//				if(i%100 == 0) {
//					pstm.executeBatch();
//					pstm.clearBatch();
//					System.out.println(i);
//				}
//				i++;
//			}
//			System.out.println("q");
//			pstm.executeBatch();
//			System.out.println("f");
//			return true;
//		} catch (Exception e) {
//			System.out.print("Erro ao inserir! " + e.getMessage());
//			e.printStackTrace();;
//			return false;
//		}
//	}
	
	public static Boolean inserir(ArrayList<EvasaoCSV> listaCSV) {
		int quant = listaCSV.size()/140;
		int atual = 0;
		for (int y = 0; y < quant; y++) {
			ArrayList<EvasaoCSV> lista = new ArrayList<EvasaoCSV>();
			for (int i = atual; i < atual + 140; i++) {
				lista.add(listaCSV.get(i));
			}
			inserir2(lista);
			atual += 140;
		}
		if(quant%140 != 0) {
			ArrayList<EvasaoCSV> lista = new ArrayList<EvasaoCSV>();
			for (int i = atual; i < listaCSV.size(); i++) {
				lista.add(listaCSV.get(i));
			}
			inserir2(lista);
		}
		return true;
	}
	
	public static boolean inserir2(ArrayList<EvasaoCSV> listaCSV) {
		StringBuffer sql = new StringBuffer("INSERT INTO evasao SELECT " + 
				" ? AS 'turno_matricula'" + 
				" , ? AS 'situacao_civil_aluno'" + 
				" , ? AS 'sexo_aluno'\r\n" + 
				" , ? AS 'data_cadastro_requerimento'" + 
				" , ? AS 'motivo_requerimento'" + 
				" , ? AS \"turma\"" + 
				" , ? AS \"area_turma\" ");
	    for(int i=1;i< listaCSV.size();i++) {
	        sql.append(" UNION ALL SELECT ?,?,?,?,?,?,?");
	    }
	    sql.append(";");
	    try {
	    	Connection conn = Conexao.getConnection();
	        PreparedStatement pstm = conn.prepareStatement(sql.toString());
	        int i = 1;
	        for(EvasaoCSV csv : listaCSV) {
	        	pstm.setString(i++, csv.getTurno());
				pstm.setString(i++, csv.getEstadoCivil());
				pstm.setString(i++, csv.getSexo());
				Date data = new Date(csv.getData().getTimeInMillis());
				pstm.setDate(i++,data);
				pstm.setString(i++, csv.getMotivo());
				pstm.setString(i++, csv.getCurso());
				pstm.setString(i++, csv.getArea());
	        }
	        pstm.execute();
	        pstm.close();
	        return true;
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	
	}
	
	public static boolean deletarTabela() {
		try {
			String sql = "delete from evasao";
			Connection conn = Conexao.getConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.print("Erro ao deletar tabela! " + e.getMessage());
			return false;
		}
	}
}

