package modelo;

import java.util.Calendar;
import java.util.Date;

public class EvasaoCSV {
	private String turno;
	private String estadoCivil;
	private String sexo;
	private Calendar data;
	private String motivo;
	public EvasaoCSV() {
		
	}
	public EvasaoCSV(String turno, String estadoCivil, String sexo, Calendar data, String motivo, String curso,
			String area) {
		this.turno = turno;
		this.estadoCivil = estadoCivil;
		this.sexo = sexo;
		this.data = data;
		this.motivo = motivo;
		this.curso = curso;
		this.area = area;
	}
	@Override
	public String toString() {
		return "EvasaoCSV [turno=" + turno + ", estadoCivil=" + estadoCivil + ", sexo=" + sexo + ", data=" + data
				+ ", motivo=" + motivo + ", curso=" + curso + ", area=" + area + "]";
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	private String curso;
	private String area;
	
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	
	public void setData(Date data) {
		Calendar data2 = Calendar.getInstance();
		data2.setTime(data);
		this.data = data2;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
