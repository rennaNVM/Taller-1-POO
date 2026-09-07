package Talleres;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TallerONE {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		String[][] datosAlumnos;
		String[][] datosSolicitudes;
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
				filtrado();
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
		
		while (sFile.hasNextLine()) {
			String linea = sFile.nextLine();
			String[] partes = linea.split(";");
			nombres[i] = partes[0];
			apellidos[i] = partes[1];
			ruts[i] = partes[2];
			paralelo[i] = partes[3];
			i++;
			}
		return new String[][] {nombres, apellidos, ruts, paralelo};
		} 
	
}
