package com.iacg.drive.service;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

/**
 * Interfaz que contiene operacion genericas sobre archivos
 * 
 * @author IACG
 */
public interface IGenericFileService {

	/**
	 * Metodo que permite crear un archivo
	 * @param fileMetadata Archivo con metadatos
	 * @param inStreamCont Contenido de archivo
	 * @return File respuesta
	 */
	File createFile(File fileMetadata, InputStreamContent inStreamCont);
	
	/**
	 * Metodo que permite realizar una busqueda de archivos por parametros
	 * @param name Nombre de archivo
	 * @param idFolder Identificador unico de carpeta
	 * @param mimeType Tipo de archivo
	 * @param contains Palabra clave
	 * @return FileList Respuesta
	 */
	FileList listFiles(String name, String idFolder, String mimeType, String contains);
	
	/**
	 * Metodo que permite realizar una busqueda de archivo por id
	 * @param id Identificador unico de archivo
	 * @return File Respuesta
	 */
	File findFileById(String id);
	
	/**
	 * Metodo que permite eliminar un archivo por su id
	 * @param id Identificador unico de archivo
	 */
	void deleteFile(String id);
	
	/**
	 * Metodo que permite verificar si existe un archivo con el nombre (el cual incluye la extension del archivo), dentro
	 * de la misma carpeta
	 * @param name Nombre de archivo
	 * @param idFolder Identificador unico de carpeta
	 * @return boolean Respuesta
	 */
	boolean existFileWithNameAndMimeType(String name, String idFolder);
	
}//Fin de clase
