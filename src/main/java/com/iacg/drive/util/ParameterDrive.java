package com.iacg.drive.util;

/**
 * Clase que carga las variables de configuracion
 * 
 * @author IACG
 */
public class ParameterDrive {
	
	/**
	 * Constructor privado que evita la instancia
	 */
	private ParameterDrive() {}
	
	/**
	 * Variable ROOT
	 */
	public static final String ROOT = "root";
	
	/**
	 * Variable SPACES_DRIVE
	 */
	public static final String SPACES_DRIVE = "drive";
	
	/**
	 * Variable MIMETYPE_FOLDER
	 */
	public static final String MIMETYPE_FOLDER = "application/vnd.google-apps.folder";

	/**
	 * Variable FIELDS_FIND_FOLDER, campos incluir en una respuesta
	 */
	public static final String FIELDS_LIST_FILES = "nextPageToken, files(*)";
	
	/**
	 * Variable FIELDS_FIND_FILE, campos a incluir al buscar archivo
	 */
	public static final String FIELDS_FIND_FILE = "id, name, mimeType, fileExtension, size, createdTime, parents";
	
	/**
	 * Variable FIELDS_CREATE_FILE, campos a incluir al crear archivo
	 */
	public static final String FIELDS_CREATE_FILE = "id, name, mimeType, fileExtension, size, createdTime, parents";
	
	/**
	 * Variable THUMBNAIL_URL_IMAGE, url de miniatura de archivo
	 */
	public static final String THUMBNAIL_URL = "https://drive.google.com/thumbnail?sz=w1920&id=";

}//Fin de clase
