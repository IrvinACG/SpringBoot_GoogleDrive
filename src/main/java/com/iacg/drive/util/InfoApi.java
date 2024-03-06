package com.iacg.drive.util;

/**
 * Clase que contiene los mensajes para la documentacion de la api
 * 
 * @author IACG
 */
public class InfoApi {

	/**
	 * Variable FOLDER_CREATED
	 */
	public static final String FOLDER_CREATE_RES = "La carpeta se creo con exito";
	
	/**
	 * Variable FOLD_CRT_SUMM
	 */
	public static final String FOLDER_CREATE_SUMM = "Crear una carpeta nueva";
	
	/**
	 * Variable FOLD_CREATE_DESC
	 */
	public static final String FOLDER_CREATE_DESC = "Al crear la carpeta, se debe especificar en que carpeta, se creara la nueva carpeta. El valor del campo (idFolder) puede ser 'root' o nulo. Al ser nulo, se tomara el valor de la carpeta root";
	
	/**
	 * Variable FILE_CREATE_SUMM
	 */
	public static final String FILE_CREATE_SUMM = "Subir nuevo archivo multimedia";
	
	/**
	 * Variable FILE_CREATE_DESC
	 */
	public static final String FILE_CREATE_DESC = "Sube un archivo multimedia, el cual estara alojando en un carpeta (idFolder). El archivo no debe pesar mas de 5MB. Una carpeta no puede contener 2 veces mismo archivo multimedia";
	
	/**
	 * Variable FILE_CREATE_RES
	 */
	public static final String FILE_CREATE_RES = "El archivo se subio con exito";
	
	/**
	 * Variable FILE_FIND_SUMM
	 */
	public static final String FILE_FIND_SUMM = "Buscar archivo por id";
	
	/**
	 * Variable FILE_FIND_DESC
	 */
	public static final String FILE_FIND_DESC = "Busca el archivo por identificador unico, el cual mostrar los detalles de este mismo";
	
	/**
	 * Variable FILE_FIND_RES
	 */
	public static final String FILE_FIND_RES = "Exito al buscar archivo";
	
	/**
	 * Variable FILE_LIST_SUMM
	 */
	public static final String FILE_LIST_SUMM = "Listar archivos";
	
	/**
	 * Variable FILE_LIST_DESC
	 */
	public static final String FILE_LIST_DESC = "Lista los archivos por los diferentes parametros.";
	
	/**
	 * Variable FILE_LIST_RES
	 */
	public static final String FILE_LIST_RES = "Exito al buscar los archivos";
	
	/**
	 * Variable FILE_DELETE_SUMM
	 */
	public static final String FILE_DELETE_SUMM = "Eliminar archivo por id";
	
	/**
	 * Variable FILE_DELETE_DESC
	 */
	public static final String FILE_DELETE_DESC = "Elimina un archivo por identifcador unico. Una carpeta tambien se considera un archivo; la carpeta root no se puede eliminar";
	
	/**
	 * Variable FILE_DELETE_RES
	 */
	public static final String FILE_DELETE_RES = "Exito al eliminar archivo";
}//Fin de clase
