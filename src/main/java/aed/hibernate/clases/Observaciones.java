package aed.hibernate.clases;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "observaciones")
public class Observaciones implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "myForeign")
	@GenericGenerator( name = "myForeign", strategy = "foreign",parameters = {@org.hibernate.annotations.Parameter(name = "property", value = "codResidencia")})
	@OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE}) 
	@JoinColumn(name = "codResidencia")
	private Residencias codResidencia;
	
	@Column(length = 200)
	private String observaciones;
	
	

public Residencias getCodResidencia() {
		return codResidencia;
	}

	public void setCodResidencia(Residencias codResidencia) {
		this.codResidencia = codResidencia;
	}
public Observaciones() {
	// TODO Auto-generated constructor stub
}
public Observaciones(
		Residencias residencia,
		String observaciones) {
this.codResidencia=residencia;
this.observaciones=observaciones;
}

public String getObservaciones() {
	return observaciones;
}
public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
}
}
