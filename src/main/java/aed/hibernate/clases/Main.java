package aed.hibernate.clases;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import aed.hibernate.bdpruebas.noproyecto.HibernateUtil;

public class Main {
	private static int numeroMenu;

	public static void main(String[] args) {
//		Session sesion = HibernateUtil.getSessionFactory().openSession();
//		sesion.beginTransaction();
		Funciones funciones = new Funciones();
		Scanner entrada = new Scanner(System.in);
		System.out.print(
				"1.Insertar datos en tablas.\n2.Eliminar/Actualizar Residencia/Estancia\n3.Visualizar Residencias + observaciones\n4.Visualizar todo\n5.Salir");
		System.out.println();
		numeroMenu = entrada.nextInt();
		while (numeroMenu != 0) {

			switch (numeroMenu) {
			case 1:
				int numeroMenu2;
				System.out.println("Insertar datos en tablas.");
				System.out.print("1.Universidad.\n2.Residencia\n3.Estancia\n4.Estudiantes\n5.-Observaciones\n6.-Salir");
				System.out.println();
				numeroMenu2 = entrada.nextInt();
				switch (numeroMenu2) {

				case 1:

					entrada.nextLine();
					Universidades universidad = new Universidades();
					System.out.println("Introduce codigo universidad:");
					String cUni="      ";
							cUni=entrada.nextLine()+cUni;
					universidad.setCodUniversidad(cUni.substring(0, 6));
					System.out.println("Introduce nombreUniversidad:");
					universidad.setNomUniversidad(entrada.nextLine());
					funciones.insertarUniversidad(universidad);
					break;

				case 2:

					System.out.println("Comprobando si hay universidades para la creación de residencias.");
					List<Universidades> unis = funciones.getUniversidad();
					if (unis.size() == 0) {
						System.out.println("¡¡¡¡¡¡¡La base de datos no tiene universidades, agregue algunas!!!!!!!!");
					} else {
						entrada.nextLine();
						Residencias residencia = new Residencias();
						System.out.println("Introduce nombre residencia:");
						residencia.setNomResidencia(entrada.nextLine());
						System.out.println("Introduce precio mensual");
						residencia.setPrecioMensual(entrada.nextInt());
						entrada.nextLine();
						System.out.println("Introduce un codigo de universidad, actualmente están los siguientes:\n");
						for (final Universidades universidades : unis) {
							System.out.println("- " + universidades.getCodUniversidad() + "\n");
						}
						Universidades nuevaUni = new Universidades();
						nuevaUni.setCodUniversidad(entrada.nextLine());
						residencia.setUniversidad(nuevaUni);
						System.out.println("Introduce 1 para SÍ al comedor o 0 para NO");
						if (entrada.nextInt() == 0) {
							residencia.setComedor(false);
						} else {
							residencia.setComedor(true);
						}
						entrada.nextLine();
						funciones.insertarResidencia(residencia);
					}

					break;
				case 3:
					entrada.nextLine();
					System.out.println("Comprobando si hay Residencias y estudiantes para la creación de estancias");
					List<Residencias> resis = funciones.getResidencias();
					List<Estudiantes> estudis = funciones.getEstudiantes();
					Estancias estancias = new Estancias();
					if (resis.size() == 0) {
						System.out.println("Debe introducir al menos una residencia");
					} else if (estudis.size() == 0) {
						System.out.println("Debe introducir al menos un estudiante");
					} else {
						System.out.println("Introduce fecha de inicio, el formato es yyyy-MM-dd");
						try {
							estancias.setFechaInicio(new SimpleDateFormat("yyyy-MM-dd").parse(entrada.nextLine()));
						} catch (ParseException e) {
							System.out.println("fecha introducida incorrecta");
						}
						System.out.println("Introduce fecha fin, siguiendo el anterior formato");
						try {
							estancias.setFechaFin(new SimpleDateFormat("yyyy-MM-dd").parse(entrada.nextLine()));
						} catch (ParseException e) {
							System.out.println("fecha introducida incorrecta");
						}
						System.out.println("Introduce precio pagado");
						estancias.setPreciopagado(entrada.nextInt());
						entrada.nextLine();
						Estudiantes estudianteNuevo = new Estudiantes();
						System.out.println("Introduce cod estudiante, los disponibles son los siguientes:\n");
						for (final Estudiantes estudiantes : estudis) {
							System.out.println("- " + estudiantes.getCodEstudiante() + "\n");
						}
						estudianteNuevo.setCodEstudiante(entrada.nextInt());
						estancias.setEstudiantes(estudianteNuevo);
						entrada.nextLine();
						System.out.println("Introduce cod residencias, los disponibles son los siguientes:\n");
						Residencias resiNueva = new Residencias();
						for (final Residencias residencias : resis) {
							System.out.println("- " + residencias.getCodResidencia() + "\n");
						}
						resiNueva.setCodResidencia(entrada.nextInt());
						estancias.setResidencias(resiNueva);
						funciones.insertarEstancia(estancias);

					}

					break;
				case 4:
					entrada.nextLine();
					Estudiantes nuevoEstudiante = new Estudiantes();
					System.out.println("Introduce un dni válido");
					nuevoEstudiante.setDni(entrada.nextLine());
					System.out.println("Introduce nombre estudiante");
					nuevoEstudiante.setNomStudiante(entrada.nextLine());
					System.out.println("Introduce telefono");
					nuevoEstudiante.setTelefonoEstudiante(entrada.nextLine());
					funciones.insertarEstudiante(nuevoEstudiante);
					break;
				case 5:
					entrada.nextLine();
					System.out.println("Comprobando si hay Residenciasa las que agregar una observación");
					List<Residencias> compruebaResi = funciones.getResidencias();
					if (compruebaResi.size() == 0) {
						System.out.println("No hay residencias, cree algunas");
					} else {
						System.out.println(
								"Introduzca el código de residencia al que agregar la observación, las disponibles son:\n");
						for (final Residencias r : compruebaResi) {
							System.out.println("- " + r.getCodResidencia() + "\n");
						}
						Residencias nResi = new Residencias();
						Observaciones nuevaObservaciones = new Observaciones();
						nResi.setCodResidencia(entrada.nextInt());
						entrada.nextLine();
						nuevaObservaciones.setCodResidencia(nResi);
						System.out.println("Introduce la anotación");
						nuevaObservaciones.setObservaciones(entrada.nextLine());
						funciones.insertarObservación(nuevaObservaciones);
						entrada.nextLine();
					}

					break;
				case 6:
					break;
				default:
					break;

				}
				break;

			case 2:
				entrada.nextLine();
				int numero3;
				System.out.println(
						"1.-Eliminar residencia\n2.-Eliminar estancias\n3.-Actualizar residencia\n4.-Actualizar estancia\n5.-Salir");
				numero3 = entrada.nextInt();
				switch (numero3) {
				case 1:
					System.out.println("Comprobando si hay residencias para borrar");
					List<Residencias> compruebaResiBorrado = funciones.getResidencias();
					if (compruebaResiBorrado.size() == 0) {
						System.out.println("No hay residencias a borrar");
					} else {
						System.out.println("Mostrando los codigos de las residencias disponibles");
						for (final Residencias borradoResi : compruebaResiBorrado) {
							System.out.println("- " + borradoResi.getCodResidencia() + "\n");
						}
						System.out.println("Introduce un código");
						int codigoIntroducido = entrada.nextInt();
						entrada.nextLine();
						System.out.println("Estás seguro de eliminar la residencia con el código " + codigoIntroducido
								+ "? 1 para sí, 0 para no");
						if (entrada.nextInt() == 1) {
							funciones.borrarResidencia(codigoIntroducido);
						} else {
							System.out.println("Cancelando operación.");
						}
					}

					break;
				case 2:
					entrada.nextLine();
					System.out.println("Comprobando si hay Estancias para borrar");
					List<Estancias> compruebaEstanciasBorrado = funciones.getEstancias();
					if (compruebaEstanciasBorrado.size() == 0) {
						System.out.println("No hay estancias a borrar");
					} else {
						System.out.println("Mostrando los codigos de las estancias disponibles");
						for (final Estancias borradoEstancias : compruebaEstanciasBorrado) {
							System.out.println("- " + borradoEstancias.getCodEstancia() + "\n");
						}
						System.out.println("Seleccione un código:");
						int codEstan = entrada.nextInt();
						System.out.println("Estas seguro de querer eliminar la estancia con el código " + codEstan
								+ " ?, 1 para SÍ o 0 para NO");
						entrada.nextLine();
						if (entrada.nextInt() == 1) {
							funciones.borrarEstancia(codEstan);
						} else {
							System.out.println("Cancelando operación.");
						}
					}

					break;
				case 3:
					entrada.nextLine();
					System.out.println("Comprobando si hay Residencias que actualizar");
					List<Residencias> actualizaResi = funciones.getResidencias();
					if (actualizaResi.size() == 0) {
						System.out.println("No hay residencias, cree algunas");
					} else {
						System.out.println("Mostrando los codigos de las residencias disponibles");
						for (final Residencias aResidencias : actualizaResi) {
							System.out.println("- " + aResidencias.getCodResidencia() + "\n");
						}

						System.out.println("Introduce un código");
						Session sesionResi = HibernateUtil.getSessionFactory().openSession();
						sesionResi.beginTransaction();
						Residencias res1 = sesionResi.find(Residencias.class, entrada.nextInt());
						sesionResi.close();
						entrada.nextLine();
						System.out.println("Nombre de la residencia: " + res1.getNomResidencia()
								+ " , Pulsar enter para no actualizar");
						String str = entrada.nextLine();
						if (!str.isEmpty()) {
							res1.setNomResidencia(str);
						}
						System.out.println("Comedor actual: " + res1.isComedor()
								+ " , 0-> para FALSE , 1->TRUE , 2->No actualizar ");
						int p = entrada.nextInt();
						if (p == 0) {
							res1.setComedor(false);
						} else if (p == 1) {
							res1.setComedor(true);
						}
						entrada.nextLine();
						System.out.println("Precio mensual actual: " + res1.getPrecioMensual()
								+ " Pulsar 1 para actualizar, cualquier número para dejar el actual");
						p = entrada.nextInt();
						if (p == 1) {
							System.out.println("Introduce precio:");
							res1.setPrecioMensual(entrada.nextInt());
							entrada.nextLine();
						}
						funciones.actualizaResidencia(res1);

					}

					break;
				case 4:
					entrada.nextLine();
					System.out.println("Comprobando si hay Estancias que actualizar");
					List<Estancias> actualizaEstan = funciones.getEstancias();
					if (actualizaEstan.size() == 0) {
						System.out.println("No hay estancias, cree algunas");
					} else {
						System.out.println("Mostrando los codigos de las estancias disponibles");
						for (final Estancias aEstancias : actualizaEstan) {
							System.out.println("- " + aEstancias.getCodEstancia() + "\n");
						}
						////
						System.out.println("Introduce un código");
						Session sesionEstancia = HibernateUtil.getSessionFactory().openSession();
						sesionEstancia.beginTransaction();
						Estancias es1 = sesionEstancia.find(Estancias.class, entrada.nextInt());
						sesionEstancia.close();
						entrada.nextLine();
						System.out.println("Introduce fecha de inicio, el formato es yyyy-MM-dd, el actual es: "
								+ es1.getFechaInicio() + " Pulsar enter para no actualizar");
						String aFechaini = entrada.nextLine();
						if (!aFechaini.isEmpty()) {
							try {
								es1.setFechaInicio(new SimpleDateFormat("yyyy-MM-dd").parse(aFechaini));
							} catch (ParseException e) {
								System.out.println("fecha introducida incorrecta");
							}
						}

						System.out.println("Introduce fecha de fin, el formato es yyyy-MM-dd, el actual es: "
								+ es1.getFechaFin() + " Pulsar enter para no actualizar");
						String aFechafin = entrada.nextLine();
						if (!aFechafin.isEmpty()) {
							try {
								es1.setFechaFin(new SimpleDateFormat("yyyy-MM-dd").parse(aFechafin));
							} catch (ParseException e) {
								System.out.println("fecha introducida incorrecta");
							}
						}
						System.out.println("Precio pagado actual " + es1.getPreciopagado()
								+ " 1 para actualizar 0 para dejar el actual.");
						if (entrada.nextInt() == 1) {
							System.out.println("Introduce nuevo precio");
							es1.setPreciopagado(entrada.nextInt());
						}
						entrada.nextLine();
						System.out.println("Actualizar cod estudiante, 1 para SÍ, 0 para NO");
						if (entrada.nextInt() == 1) {
							Estudiantes estudianteActualizar = new Estudiantes();
							System.out.println("Introduce cod estudiante,actual: "+es1.getEstudiantes().getCodEstudiante()+ " ,los disponibles son los siguientes:\n");
							List<Estudiantes> estudisActual = funciones.getEstudiantes();
							for (final Estudiantes estudiantesActualizar : estudisActual) {
								System.out.println("- " + estudiantesActualizar.getCodEstudiante() + "\n");
							}
							estudianteActualizar.setCodEstudiante(entrada.nextInt());
							es1.setEstudiantes(estudianteActualizar);
							entrada.nextLine();
						}
						
						System.out.println("Actualizar cod residencias,actual:"+ es1.getResidencias().getCodResidencia()+", los disponibles son los siguientes:\n");
						List<Residencias> resActua = funciones.getResidencias();
						Residencias resiActualizar = new Residencias();
						for (final Residencias residenciasA : resActua) {
							System.out.println("- " + residenciasA.getCodResidencia() + "\n");
						}
						System.out.println("1 para actualizar o 0 para dejar el actual");
						if(entrada.nextInt()==1) {
							entrada.nextLine();
							System.out.println("Introduce la nueva residencia");
						resiActualizar.setCodResidencia(entrada.nextInt());
						es1.setResidencias(resiActualizar);
						}
						funciones.actualizaEstancia(es1);
						///
					}

					break;
				case 5:

					break;

				default:
					break;
				}

				break;
			case 3:
				System.out.println("Visualizar residencias y sus observaciones");
				funciones.verResiObservaciones();
				break;
			case 4:
				System.out.println("Visualizar TODO");
				funciones.verTodo();
				break;
			case 5:
				numeroMenu = 0;
				System.out.println("Saliendo");
				break;
			default:
				break;
			}

			if (numeroMenu != 0) {
				System.out.print(
						"1.Insertar datos en tablas.\n2.Eliminar/Actualizar Residencia/Estancia\n3.Visualizar Residencias + observaciones\n4.Visualizar todo\n5.Salir");
				System.out.println();
				numeroMenu = entrada.nextInt();
			}
		}

		entrada.close();
	}

}
