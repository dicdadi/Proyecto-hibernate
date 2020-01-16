package aed.hibernate.clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "residencias")
public class Residencias implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int codResidencia;
	
	@Column(length = 30)
	private String nomResidencia;
	
	@ManyToOne
	@JoinColumn(name = "codUniversidad", columnDefinition = "char(6)")
	private Universidades universidad;
	
	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, mappedBy = "residencias",orphanRemoval = true)
	private List<Estancias> estancias = new ArrayList<Estancias>();
	
	private int precioMensual;
	@Column(columnDefinition = "tinyint(1)")
	private boolean comedor;
	
	
	

	public Universidades getUniversidad() {
		return universidad;
	}
	public void setUniversidad(Universidades universidad) {
		this.universidad = universidad;
	}
	public List<Estancias> getEstancias() {
		return estancias;
	}
	public void setEstancias(List<Estancias> estancias) {
		this.estancias = estancias;
	}
	public Residencias() {
		// TODO Auto-generated constructor stub
	}
	public Residencias(int codResidencia, String nomResidencia, String codUniversidad, int precioMensual,
			boolean comedor) {
		this.codResidencia = codResidencia;
		this.nomResidencia = nomResidencia;
		this.precioMensual = precioMensual;
		this.comedor = comedor;
	}
	@OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL, mappedBy = "codResidencia")
	public int getCodResidencia() {
		return codResidencia;
	}

	public void setCodResidencia(int codResidencia) {
		this.codResidencia = codResidencia;
	}
	
	public String getNomResidencia() {
		return nomResidencia;
	}

	public void setNomResidencia(String nomResidencia) {
		this.nomResidencia = nomResidencia;
	}

	public int getPrecioMensual() {
		return precioMensual;
	}

	public void setPrecioMensual(int precioMensual) {
		this.precioMensual = precioMensual;
	}

	public boolean isComedor() {
		return comedor;
	}

	public void setComedor(boolean comedor) {
		this.comedor = comedor;
	}

}
