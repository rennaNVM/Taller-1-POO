// Renato Argandoña 22290100-6 ICCI
// Dominique Montiel 22325504-3 ICCI

package Talleres;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TallerONE{

	static int cantidadAlumnos = 0;
	static int cantidadSolicitudes = 0;
	static int cont = 0;
	static int contR = 0;
	static int inscripcionesManuales = 0;
	static int intentosManuales = 0;

	static String[][] alumnoRegistrado = new String[100][4];
	static String[][] alumnoNoPertenece = new String[100][3];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String[][] datosAlumnos = new String[0][0];
		String[][] datosSolicitudes = new String[0][0];
		String resp;
		int res = 0;
		int datosAux = 0;
		int elec = 0;
		int aux = 0;

		do {
			System.out.println("===== Sistema de Control del Grupo POO =====");
			System.out.println("1. Cargar archivos (Alumnos y Solicitudes)");
			System.out.println("2. Procesar solicitudes (Filtrado automatico)");
			System.out.println("3. Inscripcion manual al grupo");
			System.out.println("4. Administracion del curso");
			System.out.println("5. Generar reportes");
			System.out.println("6. Analisis estadistico");
			System.out.println("7. Salir");
			System.out.print(">> ");
			try {
				res = Integer.parseInt(sc.nextLine());
				if (res < 1 || res > 7){
					System.out.println("No es una opcion valida");
					System.out.println("");
				}
			}catch (Exception e){
				System.out.println("No es una opcion valida");
				System.out.println("");
			}
			

			if (res == 1) {
				if (datosAux >= 1) {
					System.out.println("Los datos ya estan cargados!");
					System.out.println("");
				} else {
					try {
						datosAlumnos = cargarAlumnos();
						datosSolicitudes = cargarSolicitudes();
						System.out.println("Datos cargados");
						System.out.println("");
						datosAux += 1;
					} catch (Exception e) {
						System.out.println("El archivo no existe");
					}
				}
			}

			
			else if (res == 2) {
				if (datosAux == 0) {
					System.out.println("No has cargados los datos de los alumnos!");
					System.out.println("");
				} else {
					for (int i = 0; i < 100; i++) {
						try {
							if (datosSolicitudes[i][0].equals(null)) {
							}
						} catch (Exception e) {
							break;
						}

						aux = 1;

						for (int o = 0; o < cantidadAlumnos; o++) {
							boolean mismoNombre = datosSolicitudes[i][0].equals(datosAlumnos[o][0]);
							boolean mismoApellido = datosSolicitudes[i][1].equals(datosAlumnos[o][1]);

							if (mismoNombre && mismoApellido) {
								alumnoRegistrado[cont][0] = datosAlumnos[o][0];
								alumnoRegistrado[cont][1] = datosAlumnos[o][1];
								alumnoRegistrado[cont][2] = datosAlumnos[o][2];
								alumnoRegistrado[cont][3] = datosAlumnos[o][3];
								cont++;
								aux -= 1;
								break;
							}
						}
						if (aux == 1) {
							alumnoNoPertenece[contR][0] = datosSolicitudes[i][0];
							alumnoNoPertenece[contR][1] = datosSolicitudes[i][1];
							alumnoNoPertenece[contR][2] = "nombre";
							contR++;
						}

					}
					System.out.println("--- Admitidos ---");
					for (int k = 0; k < cont; k++) {
						System.out.println(alumnoRegistrado[k][0] + " " + alumnoRegistrado[k][1] + " "
								+ alumnoRegistrado[k][2] + " " + alumnoRegistrado[k][3]);
					}
					System.out.println("--- Rechazados ---");
					for (int k = 0; k < contR; k++) {
						System.out.println(alumnoNoPertenece[k][0] + " " + alumnoNoPertenece[k][1]);
					}
				}
			}

			
			else if (res == 3) { 

				if (datosAux == 0) {
					System.out.println("No has cargado los datos de los alumnos");
					System.out.println("");
				} else {
					do {
						System.out.println("Como desea ingresar a la persona?");
						System.out.println("1) Por nombre completo");
						System.out.println("2) Por rut");
						System.out.print(">>");
						try {
							elec = Integer.parseInt(sc.nextLine());
						} catch (Exception e) {
							elec = 0;
						}
						if (elec < 1 || elec > 2) {
							System.out.println("No es una opcion valida");
							System.out.println("");
						}
					} while (elec < 1 || elec > 2);
					intentosManuales++;

					if (elec == 1) {
						System.out.print("Ingrese el nombre: ");
						String nom = sc.nextLine();
						System.out.print("Ingrese el apellido: ");
						String ape = sc.nextLine();

						boolean encontrado = false;
						boolean registradoAntes = false;

						for (int i = 0; i < cantidadAlumnos; i++) {
							if (nom.equalsIgnoreCase(datosAlumnos[i][0]) && ape.equalsIgnoreCase(datosAlumnos[i][1])) {
								encontrado = true;
								for (int j = 0; j < cont; j++) {
									if (datosAlumnos[i][2].equals(alumnoRegistrado[j][2])) {
										registradoAntes = true;
									}
								}
								if (registradoAntes == false) {
									alumnoRegistrado[cont][0] = datosAlumnos[i][0];
									alumnoRegistrado[cont][1] = datosAlumnos[i][1];
									alumnoRegistrado[cont][2] = datosAlumnos[i][2];
									alumnoRegistrado[cont][3] = datosAlumnos[i][3];

									cont++;
									inscripcionesManuales++;
									System.out.println("Alumno inscrito correctamente.");
								} else {
									System.out.println("El alumno ya pertenece al grupo.");
								}
								break;
							}
						}

						
						if (encontrado == false) {
							alumnoNoPertenece[contR][0] = nom;
							alumnoNoPertenece[contR][1] = ape;
							alumnoNoPertenece[contR][2] = "nombre";

							contR++;
							System.out.println("La persona no pertenece a ningun paralelo del curso.");
						}
					} else if (elec == 2) {
						System.out.print("Ingrese RUT: ");
						String rut = sc.nextLine();

						boolean encontrado = false;
						boolean registradoAntes = false;

						for (int i = 0; i < cantidadAlumnos; i++) {
							if (rut.equalsIgnoreCase(datosAlumnos[i][2])) {
								encontrado = true;
								for (int j = 0; j < cont; j++) {
									if (rut.equalsIgnoreCase(alumnoRegistrado[j][2])) {
										registradoAntes = true;
									}
								}

								if (registradoAntes == false) {
									alumnoRegistrado[cont][0] = datosAlumnos[i][0];
									alumnoRegistrado[cont][1] = datosAlumnos[i][1];
									alumnoRegistrado[cont][2] = datosAlumnos[i][2];
									alumnoRegistrado[cont][3] = datosAlumnos[i][3];

									cont++;
									inscripcionesManuales++;
									System.out.println("Alumno inscrito correctamente.");
								} else {
									System.out.println("El alumno ya pertenece al grupo.");
								}
								break;
							}
						}

						if (encontrado == false) {
							alumnoNoPertenece[contR][0] = "";
							alumnoNoPertenece[contR][1] = rut;
							alumnoNoPertenece[contR][2] = "rut";

							contR++;
							System.out.println("El rut no pertenece a ningun paralelo del curso.");
							System.out.println("No tenemos su nombre, por lo que se registrara solo el RUT.");
							System.out.println("");
						}
					} else {
						System.out.println("Opcion invalida");
					}
				}
			} 
			
			
			else if (res == 4) { 
				if (datosAux == 0) {
					System.out.println("No has cargados los datos de los alumnos!");
					System.out.println("");
				} else {
					boolean opcionAdminValida;
					do {
						opcionAdminValida = true;
						System.out.printf("%n ==== MENÚ DE ADMINISTRACIÓN ==== %n");
						System.out.println("1. Cambiar de paralelo a un alumno");
						System.out.println("2. Eliminar un alumno del curso");
						System.out.println("3. Inscribir un alumno al curso");
						System.out.println("4. Salir");
						System.out.print(">> ");
						try {
							res = Integer.parseInt(sc.nextLine());
							if (res < 1 || res > 4){
								System.out.println("No es una opcion valida");
								System.out.println("");
								opcionAdminValida = false;
							}
						}catch (Exception e){
							System.out.println("No es una opcion valida");
							System.out.println("");
							opcionAdminValida = false;
						}
					} while (!opcionAdminValida);

					if (res == 1) {
						System.out.println("=== DATOS DE LOS ALUMNOS ===");
						for (int i = 0; i < cantidadAlumnos; i++) {
							System.out.printf("%s. ", (i + 1));
							System.out.println(datosAlumnos[i][0] + " " + datosAlumnos[i][1] + " " + datosAlumnos[i][2] + " " + datosAlumnos[i][3]);
						}
						boolean numeroCambioValido;
						do {
							numeroCambioValido = true;
							System.out.println("¿A quien deseas cambiar de paralelo? [Número]");
							System.out.print(">> ");
							try {
								res = Integer.parseInt(sc.nextLine());
								if (res < 1 || res > cantidadAlumnos){
									System.out.println("No es una opcion valida");
									System.out.println("");
									numeroCambioValido = false;
								}
							}catch (Exception e){
								System.out.println("No es una opcion valida");
								System.out.println("");
								numeroCambioValido = false;
							}
						} while (!numeroCambioValido);

						for (int i = 0; i < cantidadAlumnos; i++) {
							if (res == i + 1) {
								System.out.print("¿A que paralelo deseas cambiar a ");
								System.out.println(datosAlumnos[i][0] + " " + datosAlumnos[i][1] + "? [C1 o C2]");
								System.out.println("0. Salir");
								System.out.print(">> ");
								resp = sc.nextLine();
								String[] partes = resp.split("");
								try {
									do {
										if (resp.equals("0")) {
											break;
										}
										if (partes[1].equals("1")) {
											datosAlumnos[i][3] = "C1";
										} else if (partes[1].equals("2")) {
											datosAlumnos[i][3] = "C2";
										} else {
											System.out.println("Paralelo no existente");
											System.out.print("¿A que paralelo deseas cambiar a ");
											System.out.println(datosAlumnos[i][0] + " " + datosAlumnos[i][1] + "? [C1 o C2]");
											System.out.println("0. Salir");
											System.out.print(">> ");
											resp = sc.nextLine();
											partes = resp.split("");
											if (resp.equals("0")) {
												break;
											}
										}
									} while (!(partes[1].equals("1")) && !(partes[1].equals("2")));
								}catch (Exception e) {
									System.out.println("No es una opcion valida");
									System.out.println("");
								}
								try {
									if (resp.equals("0")) {
										continue;
									}else {
										guardarAlumnos(datosAlumnos);
										System.out.println("Datos guardados correctamente");
									}
								} catch (IOException e) {
									System.out.println("No se pudo guardar el archivo");
								}
								break;
							}
						}
					}
					
					if (res == 2) {
						System.out.println("=== DATOS DE LOS ALUMNOS ===");
						for (int i = 0; i < cantidadAlumnos; i++) {
							System.out.printf("%s. ", (i + 1));
							System.out.println(datosAlumnos[i][0] + " " + datosAlumnos[i][1] + " " + datosAlumnos[i][2] + " " + datosAlumnos[i][3]);
						}
						boolean numeroEliminarValido;
						
						do {
							numeroEliminarValido = true;
							System.out.println("¿A quien deseas eliminar del paralelo? [Número]");
							System.out.print(">> ");
							try {
								res = Integer.parseInt(sc.nextLine());
								if (res < 1 || res > cantidadAlumnos){
									System.out.println("No es una opcion valida");
									System.out.println("");
									numeroEliminarValido = false;
								}
							}catch (Exception e){
								System.out.println("No es una opcion valida");
								System.out.println("");
								numeroEliminarValido = false;
							}
						} while (!numeroEliminarValido);

						datosAlumnos = eliminarAlumno(datosAlumnos, res);
						try {
							guardarAlumnos(datosAlumnos);
						} catch (IOException e) {
							System.out.println("No se pudo guardar el archivo");
						}
					}
					if (res == 3) {
						System.out.println("Ingrese los datos del alumno en el siguiente formato: ");
						System.out.println("NOMBRE;APELLIDO;RUT;PARALELO");
						System.out.println("0. Salir");
						System.out.print(">> ");
						resp = sc.nextLine();
						if (resp.equals("0")) {
							continue;
						} else {
							String[] partes = resp.split(";");
							datosAlumnos[cantidadAlumnos][0] = partes[0];
							datosAlumnos[cantidadAlumnos][1] = partes[1];
							datosAlumnos[cantidadAlumnos][2] = partes[2];
							datosAlumnos[cantidadAlumnos][3] = partes[3];
							cantidadAlumnos++;
							try {
								guardarAlumnos(datosAlumnos);
								System.out.println("Datos guardados correctamente");
							} catch (IOException e) {
								System.out.println("No se pudo guardar el archivo");
							}
						}
					}
				}
			} 
			
			
			else if (res == 5) { 
				if (datosAux == 0) {
					System.out.println("No has cargado los datos de los alumnos!");
					System.out.println("");
				} else {
					int opcionReport = 0;

					do {
						System.out.println("===== GENERAR REPORTES =====");
						System.out.println("1. Reporte C1");
						System.out.println("2. Reporte C2");
						System.out.println("3. Reporte rechazados");
						System.out.println("4. Salir");
						System.out.print(">> ");
						opcionReport = Integer.parseInt(sc.nextLine());

						if (opcionReport == 1) {
							generarReporteParalelo("C1");
						} 
						else if (opcionReport == 2) {
							generarReporteParalelo("C2");
						} 
						else if (opcionReport == 3) {
							generarReporteRechazados();
						} 
						else if (opcionReport != 4) {
							System.out.println("Opcion invalida");
						}

					} while (opcionReport != 4);
				}
			} 
			
			
			else if (res == 6) {
				if (datosAux == 0) {
					System.out.println("No has cargado los datos de los alumnos!");
					System.out.println("");
				} else {
					analisisEstadistico();
				}
			}
		} while (res != 7);

	}

	
	
	public static String[][] cargarSolicitudes() throws IOException {
		File arch = new File("textos/Solicitudes.txt");
		Scanner sFile = new Scanner(arch);

		int o = 0;
		String[][] solicitudes = new String[100][2];

		while (sFile.hasNextLine() && o < 100) {
			String linea = sFile.nextLine();
			String[] partes = linea.split("-");
			solicitudes[o][0] = partes[0];
			solicitudes[o][1] = partes[1];
			o++;
		}

		cantidadSolicitudes = o;
		return solicitudes;
	}

	public static String[][] cargarAlumnos() throws IOException {
		File arch = new File("textos/Alumnos.txt");
		Scanner sFile = new Scanner(arch);

		int i = 0;
		String[][] alumnos = new String[100][4];

		while (sFile.hasNextLine() && i < 100) {
			String linea = sFile.nextLine();
			String[] partes = linea.split(";");
			alumnos[i][0] = partes[0];
			alumnos[i][1] = partes[1];
			alumnos[i][2] = partes[2];
			alumnos[i][3] = partes[3];
			i++;
			cantidadAlumnos += 1;
		}
		if (sFile.hasNextLine()) {
			System.out.println("Te haz quedado sin espacio!");
			return alumnos;
		}
		return alumnos;
	}

	public static String[][] eliminarAlumno(String[][] data, int resp) {
		String[][] nuevaLista = new String[100][4];
		int index = 0;
		int aux = cantidadAlumnos;
		for (int i = 0; i < aux; i++) {
			if (i == (resp - 1)) {
				cantidadAlumnos -= 1;
			} else {
				nuevaLista[index][0] = data[i][0];
				nuevaLista[index][1] = data[i][1];
				nuevaLista[index][2] = data[i][2];
				nuevaLista[index][3] = data[i][3];
				index++;
			}
		}
		return nuevaLista;
	}

	public static void guardarAlumnos(String[][] data) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("textos/Alumnos.txt"));

		for (int i = 0; i < cantidadAlumnos; i++) {
			bw.write(data[i][0] + ";" + data[i][1] + ";" + data[i][2] + ";" + data[i][3]);
			bw.newLine();
		}
		bw.close();
	}

	public static void generarReporteParalelo(String paralelo) {
		File carpeta = new File("Reportes");

		if (carpeta.exists() == false) { 
			carpeta.mkdir(); 
		}

		int version = 1;
		File archivo = new File("Reportes/Reporte" + paralelo + "-V" + version + ".txt");

		while (archivo.exists()) {
			version++;
			archivo = new File("Reportes/Reporte" + paralelo + "-V" + version + ".txt");
		}
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo));
			
			escritor.write("=== Miembros del grupo - Paralelo " + paralelo + " ===");
			escritor.newLine();
			for (int i = 0; i < cont; i++) {
				if (alumnoRegistrado[i][3].equals(paralelo)) {
					escritor.write(
							alumnoRegistrado[i][0] + " " + alumnoRegistrado[i][1] + " - " + alumnoRegistrado[i][2]);
					escritor.newLine();
				}
			}
			escritor.close();

		} catch (IOException e) {
			System.out.println("No se pudo generar el reporte.");
		}

	}

	public static void generarReporteRechazados() {
		File carpeta = new File("Reportes");

		if (carpeta.exists() == false) {
			carpeta.mkdir();
		}
		
		int version = 1;
		File archivo = new File("Reportes/Rechazados-V" + version + ".txt");
		while (archivo.exists()) {
			version++;
			archivo = new File("Reportes/Rechazados-V" + version + ".txt");
		}
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo));

			escritor.write("=== Solicitudes rechazadas ===");
			escritor.newLine();
			for (int i = 0; i < contR; i++) {
				if (alumnoNoPertenece[i][2].equals("nombre")) {
					escritor.write(alumnoNoPertenece[i][0] + " " + alumnoNoPertenece[i][1] + " - No pertenece a ningun paralelo del curso");
				} else if (alumnoNoPertenece[i][2].equals("rut")) {
					escritor.write("Sin nombre registrado, RUT: " + alumnoNoPertenece[i][1]);
				}
				escritor.newLine();
			}
			escritor.close();
		} catch (IOException e) {
			System.out.println("No se pudo generar el reporte.");
		}
	}

	public static void analisisEstadistico() {

		int cantidadC1 = 0;
		int cantidadC2 = 0;
		int rechazadosRut = 0;
		int admitidosArchivo = cont - inscripcionesManuales;

		for (int i = 0; i < cont; i++) {
			if (alumnoRegistrado[i][3].equals("C1")) {
				cantidadC1++;
			} else if (alumnoRegistrado[i][3].equals("C2")) {
				cantidadC2++;
			}
		}

		for (int i = 0; i < contR; i++) {
			if (alumnoNoPertenece[i][2].equals("rut")) {
				rechazadosRut++;
			}
		}
		
		int totalIntentos = cantidadSolicitudes + intentosManuales;
		double porcentajeRechazados = 0;
		double porcentajeC1 = 0;
		double porcentajeC2 = 0;
		double tasaAdmision = 0;
		
		if (totalIntentos > 0) {
			porcentajeRechazados = (contR * 100.0) / totalIntentos;
			tasaAdmision = (cont * 100.0) / totalIntentos;
		}
		if (cont > 0) {
			porcentajeC1 = (cantidadC1 * 100.0) / cont;
			porcentajeC2 = (cantidadC2 * 100.0) / cont;
		}
		System.out.println("--- Analisis estadisticos ---");
		System.out.println("Tasa de admision: " + tasaAdmision + "%");
		System.out.println("Inscripciones -> Archivo: " + admitidosArchivo + " | Manual: " + inscripcionesManuales);
		System.out.println("Rechazados: " + contR + " (" + porcentajeRechazados + "%)");
		System.out.println("Admitidos por paralelo -> C1: " + cantidadC1 + "(" + porcentajeC1 + "%) | C2: " + cantidadC2 + "(" + porcentajeC2 + "%)");
	}

}
