package aed.hibernate.clases;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import aed.hibernate.bdpruebas.noproyecto.HibernateUtil;

import javax.persistence.Query;
public class Funciones {
	public List<Universidades> getUniversidad() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Universidades");

		List<Universidades> universidades = null;
		universidades = query.getResultList();

		transaction.commit();
		session.close();

		return universidades;
	}
	public List<Estancias> getEstancias() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Estancias");

		List<Estancias> estancias = null;
		estancias = query.getResultList();

		transaction.commit();
		session.close();

		return estancias;
	}
	public List<Residencias> getResidencias() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Residencias");

		List<Residencias> residencias = null;
		residencias = query.getResultList();

		transaction.commit();
		session.close();

		return residencias;
	}
	public List<Estudiantes> getEstudiantes() {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Estudiantes");

		List<Estudiantes> estudiantes = null;
		estudiantes = query.getResultList();

		transaction.commit();
		session.close();

		return estudiantes;
	}


	public void insertarUniversidad(Universidades universidad) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		session.save(universidad);

		transaction.commit();
		session.close();
	}

	public void insertarResidencia(Residencias residencia) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		session.save(residencia);

		transaction.commit();
		session.close();
	}
	public void borrarResidencia(int id) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		Residencias residencia= sesion.find(Residencias.class, id);
		residencia.getEstancias().clear();
		sesion.close();
	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Observaciones resiOb = new Observaciones();
		resiOb.setCodResidencia(residencia);
		session.delete(resiOb);
		session.delete(residencia);
		transaction.commit();
		session.close();
	}
	public void borrarEstancia(int id) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		Estancias estancia= sesion.find(Estancias.class, id);
		sesion.close();
	
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transasction = session.beginTransaction();
		
		session.remove(estancia);
		
		transasction.commit();
		session.close();
	}

	public void insertarObservación(Observaciones observacion) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		session.save(observacion);

		transaction.commit();
		session.close();
	}
	public  void insertarEstudiante(Estudiantes estudiante) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = session.beginTransaction();

		session.save(estudiante);

		transaction.commit();
		session.close();
	}
	public  void insertarEstancia(Estancias estancia) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.save(estancia);
		
		transaction.commit();
		session.close();
	}
	public void actualizaEstancia(Estancias estancia) {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		sesion.update(estancia);
		sesion.getTransaction().commit();
		sesion.close();
	}
	public void actualizaResidencia(Residencias residencia) {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		sesion.update(residencia);
		sesion.getTransaction().commit();
		sesion.close();
	}
	public void verResiObservaciones() {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		String sel = "SELECT r.codResidencia, r.nomResidencia, r.universidad, r.precioMensual, r.comedor, ro.observaciones FROM Residencias r LEFT JOIN Observaciones ro ON r.codResidencia = ro.codResidencia";
		List<Object[]> results = sesion.createQuery(sel).getResultList();
		Universidades uni = new Universidades();
		System.out.println();
		for (Object[] result : results) {
			System.out.println("--------------------------------------------------------------------------------");
			uni = (Universidades) result[2];
			if (result[5] == null) {
				System.out.println("-codResidencia: "+result[0] + "\n -nomResidencia: " + result[1] + "\n -codUniversidad: " + uni.getCodUniversidad() + "\n -preciomensual: "
						+ result[3] + "\n -comedor: " + result[4] + "\n  -No hay observaciones");
			} else {
				System.out.println("-codResidencia: "+result[0] + "\n -nomResidencia: " + result[1] + "\n -codUniversidad: " + uni.getCodUniversidad() + "\n -preciomensual: "
						+ result[3] + "\n -comedor: " + result[4] + "\n -Observaciones:"+ result[5]);
			}
			System.out.println("--------------------------------------------------------------------------------");
		}
		sesion.getTransaction().commit();
		sesion.close();
	
	}
	public void verTodo() {
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		Query cons = sesion.createQuery("from Estancias e");
		List<Estancias> listEstancias = cons.getResultList();
		System.out.println();
	
		for (Estancias e : listEstancias) {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println(
					"Codigo residencia: "+e.getResidencias().getCodResidencia()+
					"\nNombre residencia: "+e.getResidencias().getNomResidencia()+
					"\nCodigo universidad: "+e.getResidencias().getUniversidad().getCodUniversidad()+
					"\nNombre universidad: "+ e.getResidencias().getUniversidad().getNomUniversidad()+
					"\nPrecio mensual: "+ e.getResidencias().getPrecioMensual()+
					"\nComedor: "+ e.getResidencias().isComedor()+
					"\nCodigo estudiante: "+e.getEstudiantes().getCodEstudiante()+
					"\nDni estudiante: "+e.getEstudiantes().getDni()+
					"\nNombre estudiante: "+e.getEstudiantes().getNomStudiante()+
					"\nCodigo estancia: "+e.getCodEstancia()+
					"\nFecha de inicio: "+e.getFechaInicio()+
					"\nFecha fin: "+e.getFechaFin()+
					"\nPrecio pagado: "+e.getPreciopagado()
					);
			System.out.println("--------------------------------------------------------------------------------");
		}
		sesion.getTransaction().commit();
		sesion.close();
	
	}
				
	}
