package aed.hibernate.clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "universidades")
public class Universidades implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "codUniversidad", columnDefinition = "char(6)")
	private String codUniversidad;
	
	@Column(length = 30)
	private String nomUniversidad;
	
	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, mappedBy = "universidad")
	private List<Residencias> residencias = new ArrayList<Residencias>();
public Universidades() {
	// TODO Auto-generated constructor stub
}
	public Universidades(String codUniversidad, String nomUniversidad) {
		this.codUniversidad = codUniversidad;
		this.nomUniversidad = nomUniversidad;

	}

	public String getCodUniversidad() {
		return codUniversidad;
	}

	public void setCodUniversidad(String codUniversidad) {
		this.codUniversidad = codUniversidad;
	}

	public String getNomUniversidad() {
		return nomUniversidad;
	}

	public void setNomUniversidad(String nomUniversidad) {
		this.nomUniversidad = nomUniversidad;
	}
}
