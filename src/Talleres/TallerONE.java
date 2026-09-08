package Talleres;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TallerONE {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		String[][] datosAlumnos = new String [0][0];
		String[][] datosSolicitudes = new String [0][0];
		String[][] alumnoRegistrado = new String [50][4];
		String[][] alumnoNoPertenece = new String [50][4];
		int cont = 0 ;
		int contR = 0 ;
		int res = 0;
		
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
			
			if (res==1) {
				try {
					datosAlumnos = cargarAlumnos();
					datosSolicitudes = cargarSolicitudes();
					System.out.println("Datos cargados");
					System.out.println("");
				} catch (Exception e) {
					System.out.println("El archivo no existe");
				}
			}
			
			else if (res==2) {
				for (int i = 0; i<50 ; i++ ) {
					try {
						if (datosSolicitudes[0][i].equals(null)) {
						}
					} catch (Exception e) {
						break;
					}
					
					int aux = 1;
					
					for (int o = 0; o<50; o++) {
						try {
							if (datosAlumnos[0][o].equals(null)) {
							}
						} catch (Exception e) {
							break;
						}
						
						boolean mismoNombre = datosSolicitudes[0][i].equals(datosAlumnos[0][o]);
						boolean mismoApellido = datosSolicitudes[1][i].equals(datosAlumnos[1][o]);
						
						if (mismoNombre && mismoApellido) {
							alumnoRegistrado[cont][0] = datosAlumnos[0][o];
							alumnoRegistrado[cont][1] = datosAlumnos[1][o];
							alumnoRegistrado[cont][2] = datosAlumnos[2][o];
							alumnoRegistrado[cont][3] = datosAlumnos[3][o];
			                cont++;
			                aux-=1;
			                break;
						}
					}
					if (aux==1) {
					    alumnoNoPertenece[contR][0]=datosSolicitudes[0][i];
					    alumnoNoPertenece[contR][1]=datosSolicitudes[1][i]; 
					    contR++;
					}

				}
				System.out.println("--- Admitidos ---"); //BORRAR DESPUES
			    for (int k = 0; k < cont; k++) {
			        System.out.println(alumnoRegistrado[k][0] + " " + alumnoRegistrado[k][1] + " "
			                + alumnoRegistrado[k][2] + " " + alumnoRegistrado[k][3]);
			    }
			    System.out.println("--- Rechazados ---");
			    for (int k = 0; k < contR; k++) {
			        System.out.println(alumnoNoPertenece[k][0] + " " + alumnoNoPertenece[k][1]);
			    }
			}
			
		} while (res!=7) ;

	}

	private static void filtrado() {
		
		
	}

	private static String[][] cargarSolicitudes() throws IOException{
		File arch = new File("textos/Solicitudes.txt");
		Scanner sFile = new Scanner(arch);
		
		int o = 0;
		String[] nombresSolicitados = new String[50];
		String[] apellidosSolicitados = new String[50];
		
		while (sFile.hasNextLine()) {
			String linea = sFile.nextLine();
			String[] partes = linea.split("-");
			nombresSolicitados[o] = partes[0];
			apellidosSolicitados[o] = partes[1];
			o++;
		}
		
		return new String[][] {nombresSolicitados, apellidosSolicitados} ;
	}

	private static String[][] cargarAlumnos() throws IOException{
		File arch = new File("textos/Alumnos.txt");
		Scanner sFile = new Scanner(arch);
		
		int i = 0;
		String[] nombres = new String[50];
		String[] apellidos = new String[50];
		String[] ruts = new String[50];
		String[] paralelo = new String[50];
		
		while (sFile.hasNextLine() && i<50) {
			String linea = sFile.nextLine();
			String[] partes = linea.split(";");
			nombres[i] = partes[0];
			apellidos[i] = partes[1];
			ruts[i] = partes[2];
			paralelo[i] = partes[3];
			i++;
			}
		if (sFile.hasNextLine()) {
			System.out.println("Te haz quedado sin espacio!");
			return new String[][] {nombres, apellidos, ruts, paralelo};
		}
		return new String[][] {nombres, apellidos, ruts, paralelo};
		} 
	
}
