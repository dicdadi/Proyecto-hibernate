package aed.hibernate.clases;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "estudiantes")
public class Estudiantes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int codEstudiante;
	@Column(length = 9)
private String dni;
	@Column(length = 50)
private String nomStudiante;
	@Column(length = 9)
private String telefonoEstudiante;
	
	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, mappedBy = "estudiantes")
	private List<Estancias> estancias = new ArrayList<Estancias>();
	public Estudiantes() {
		// TODO Auto-generated constructor stub
	}
public List<Estancias> getEstancias() {
		return estancias;
	}
	public void setEstancias(List<Estancias> estancias) {
		this.estancias = estancias;
	}
public Estudiantes(
		int codEstudiante,
		String dni,
		String nomStudiante,
		String telefonoEstudiante) {
	this.codEstudiante=codEstudiante;
	this.dni=dni;
	this.nomStudiante=nomStudiante;
	this.telefonoEstudiante=telefonoEstudiante;

}
public int getCodEstudiante() {
	return codEstudiante;
}
public void setCodEstudiante(int codEstudiante) {
	this.codEstudiante = codEstudiante;
}
public String getDni() {
	return dni;
}
public void setDni(String dni) {
	this.dni = dni;
}
public String getNomStudiante() {
	return nomStudiante;
}
public void setNomStudiante(String nomStudiante) {
	this.nomStudiante = nomStudiante;
}
public String getTelefonoEstudiante() {
	return telefonoEstudiante;
}
public void setTelefonoEstudiante(String telefonoEstudiante) {
	this.telefonoEstudiante = telefonoEstudiante;
}

}
