package aed.hibernate.clases;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "estancias")
public class Estancias {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codEstancia;
	@ManyToOne
	@JoinColumn(name = "codResidencia")
	private Residencias residencias;

	public Residencias getResidencias() {
		return residencias;
	}

	public void setResidencias(Residencias residencias) {
		this.residencias = residencias;
	}

	public Estudiantes getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(Estudiantes estudiantes) {
		this.estudiantes = estudiantes;
	}

	// private int codResidencia;
	@ManyToOne
	@JoinColumn(name = "codEstudiante")
	private Estudiantes estudiantes;
//private int codEstudiante;
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	@Temporal(TemporalType.DATE)
	private Date fechaFin;
	private int preciopagado;

	public Estancias() {
		// TODO Auto-generated constructor stub
	}

	public Estancias(int codEstancia, int codResidencia, int codEstudiante, Date fechaInicio, Date fechaFin,
			int precioPagado) {
		this.codEstancia = codEstancia;
		// this.codResidencia=codResidencia;
		// this.codEstudiante=codEstudiante;
		this.fechaFin = fechaInicio;
		this.fechaFin = fechaFin;
		this.preciopagado = precioPagado;

	}

	public int getCodEstancia() {
		return codEstancia;
	}

	public void setCodEstancia(int codEstancia) {
		this.codEstancia = codEstancia;
	}

//public int getCodResidencia() {
//	return codResidencia;
//}

//public void setCodResidencia(int codResidencia) {
//	this.codResidencia = codResidencia;
//}

//public int getCodEstudiante() {
//	return codEstudiante;
//}
//
//public void setCodEstudiante(int codEstudiante) {
//	this.codEstudiante = codEstudiante;
//}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getPreciopagado() {
		return preciopagado;
	}

	public void setPreciopagado(int preciopagado) {
		this.preciopagado = preciopagado;
	}

}
