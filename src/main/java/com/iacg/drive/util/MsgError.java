package com.iacg.drive.util;

/**
 * Clase que contiene los mensajes de error
 * 
 * @author IACG
 */
public class MsgError {
	
	/**
	 * Constructor privado que evita la instancia
	 */
	private MsgError() {}
	
	/**
	 * Variable FOLDER_EXIST
	 */
	public static final String FOLDER_EXIST = "Ya existe carpeta con el mismo nombre, en el mismo espacio";
	
	/**
	 * Variable FILE_EXIST
	 */
	public static final String FILE_EXIST = "Ya existe archivo con el mismo nombre y extension, en la misma carpeta";
	
	/**
	 * Variable PROCESS_FILE
	 */
	public static final String PROCESS_FILE = "Error al procesar archivo multimedia";
	
	/**
	 * Variable NOT_FOUND_FILES
	 */
	public static final String NOT_FOUND_FILES = "No se encontraron archivos con los parametros ingresados";
}//Fin de clase
