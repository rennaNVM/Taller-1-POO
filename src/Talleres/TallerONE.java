// Renato Argandoña 22290100-6 ICCI
// Dominique Montiel 22325504-3 ICCI

package Talleres;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TallerONE {

	static int cantidadAlumnos = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String[][] datosAlumnos = new String[0][0];
		String[][] datosSolicitudes = new String[0][0];
		String[][] alumnoRegistrado = new String[50][4];
		String[][] alumnoNoPertenece = new String[50][4];
		String resp;
		int cont = 0;
		int contR = 0;
		int res = 0;
		int datosAux = 0;
		int elec = 0;

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
			res = Integer.parseInt(sc.nextLine());

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
					for (int i = 0; i < 50; i++) {
						try {
							if (datosSolicitudes[i][0].equals(null)) {
							}
						} catch (Exception e) {
							break;
						}

						int aux = 1;

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
							contR++;
						}

					}
					System.out.println("--- Admitidos ---"); // BORRAR DESPUES
					for (int k = 0; k < cont; k++) {
						System.out.println(alumnoRegistrado[k][0] + " " + alumnoRegistrado[k][1] + " "
								+ alumnoRegistrado[k][2] + " " + alumnoRegistrado[k][3]);
					}
					System.out.println("--- Rechazados ---"); // BORAR DESPUES
					for (int k = 0; k < contR; k++) {
						System.out.println(alumnoNoPertenece[k][0] + " " + alumnoNoPertenece[k][1]);
					}
				}
			}

			else if (res == 3) { // EN DESARROLLO
				System.out.println("Como desea ingresar a la persona?");
				System.out.println("1) Por nombre completo");
				System.out.println("2) Por rut");
				System.out.print(">>");
				elec = Integer.parseInt(sc.nextLine());

				if (elec == 1) {
					System.out.print("Ingrese el nombre completo: ");
					String nom = sc.nextLine();
				} else if (elec == 2) {
					System.out.println("Ingrese RUT: ");
					String rut = sc.nextLine();

				}
			} else if (res == 4) { // EN DESARROLLO
				if (datosAux == 0) {
					System.out.println("No has cargados los datos de los alumnos!");
					System.out.println("");
				} else {
					System.out.printf("%n ==== MENÚ DE ADMINISTRACIÓN ==== %n");
					System.out.println("1. Cambiar de paralelo a un alumno");
					System.out.println("2. Eliminar un alumno del curso");
					System.out.println("3. Inscribir un alumno al curso");
					System.out.println("4. Salir");
					System.out.print(">> ");
					res = Integer.parseInt(sc.nextLine());
					if (res == 1) {
						System.out.println("=== DATOS DE LOS ALUMNOS ===");
						for (int i = 0; i < cantidadAlumnos; i++) {
							System.out.printf("%s. ", (i + 1));
							System.out.println(datosAlumnos[i][0] + " " + datosAlumnos[i][1] + " " + datosAlumnos[i][2]
									+ " " + datosAlumnos[i][3]);

						}
						System.out.println("¿A quien deseas cambiar de paralelo? [Número]");
						System.out.print(">> ");
						res = Integer.parseInt(sc.nextLine());

						for (int i = 0; i < cantidadAlumnos; i++) {
							if (res == i + 1) {
								System.out.print("¿A que paralelo deseas cambiar a ");
								System.out.println(datosAlumnos[i][0] + " " + datosAlumnos[i][1] + "? [C1 o C2]");
								System.out.println("0. Salir");
								System.out.print(">> ");
								resp = sc.nextLine();
								String[] partes = resp.split("");
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
									}
								} while (!(partes[1].equals("1")) || !(partes[1].equals("2")) || resp.equals("0"));

							}
						}
					}
					if (res == 2) {

					}
				}
			}

		} while (res != 7);

	}

	public static String[][] cargarSolicitudes() throws IOException {
		File arch = new File("textos/Solicitudes.txt");
		Scanner sFile = new Scanner(arch);

		int o = 0;
		String[][] solicitudes = new String[50][2];

		while (sFile.hasNextLine() && o < 50) {
			String linea = sFile.nextLine();
			String[] partes = linea.split("-");
			solicitudes[o][0] = partes[0];
			solicitudes[o][1] = partes[1];
			o++;
		}

		return solicitudes;
	}

	public static String[][] cargarAlumnos() throws IOException {
		File arch = new File("textos/Alumnos.txt");
		Scanner sFile = new Scanner(arch);

		int i = 0;
		String[][] alumnos = new String[50][4];

		while (sFile.hasNextLine() && i < 50) {
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

}
