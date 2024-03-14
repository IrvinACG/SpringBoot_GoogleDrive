package com.iacg.drive.util;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Clase de utilidad que contiene las query para consultas de archivos
 * 
 * @author IACG
 */
@Component
public class QueryFileUtils {

	/**
	 * Variable rootFolder
	 */
	@Value("${google.drive.folder.root-id}")
	public String rootFolder;
	
	/**
	 * Variable and
	 */
	private static final String AND = " and ";
	
	/**
	 * Metodo que verifica si la carpeta es root
	 * @param idFolder Identificador unico de carpeta
	 * @return boolean Respuesta
	 */
	public boolean isFolderRoot(String idFolder) {
		return rootFolder.equalsIgnoreCase(idFolder);
	}
	
	/**
	 * Metodo que genera una query de busqueda de acuerdo a los parametros
	 * @param name Nombre completo de archivo con extension
	 * @param idFolder Identificador de carpeta
	 * @param mimeType Tipo de archivo
	 * @param contains Palabra clave
	 * @return String Respuesta
	 */
	public String queryListFile(String name, String idFolder, String mimeType, String contains) {
		StringBuilder sb = new StringBuilder();
		//Identificador de carpeta
		if(Objects.nonNull(idFolder)) {
			if(idFolder.equalsIgnoreCase(ParameterDrive.ROOT)) { //Carpeta es root
				sb.append("'" + this.rootFolder + "'");
			}else {
				sb.append("'" + idFolder + "'");
			}
			sb.append(" in parents");
		}
		//Nombre
		if(Objects.nonNull(name)) {
			if(!sb.toString().equals("")) {
				sb.append(AND);
			}
			sb.append("name = '" + name + "'");
		}
		//MimeType
		if(Objects.nonNull(mimeType)) {
			if(!sb.toString().equals("")) {
				sb.append(AND);
			}
			sb.append("mimeType = '" + mimeType + "'");
		}
		//Contains
		if(Objects.nonNull(contains)) {
			if(!sb.toString().equals("")) {
				sb.append(AND);
			}
			sb.append("fullText contains '" + contains + "'");
		}
		
		return sb.toString();
	}
	
	/**
	 * Metodo que verifica si el Id de la carpeta es root, de lo contrario regresa Id original. Si contiene la palabra 'root' o el objeto es 
	 * nulo, regresara el id-root de la carpeta
	 * @param idOriginal Identificador de carpeta original
	 * @return String Respuesta
	 */
	public String verifyIdFolderIsRoot(String idOriginal) {			
		if(Objects.nonNull(idOriginal)) {
			//Carpeta ROOT, obtener ID
			if(idOriginal.equalsIgnoreCase(ParameterDrive.ROOT)) {
				return this.rootFolder;
			}
			return idOriginal; //Regresa ID original
		}
		//Carpeta root
		return this.rootFolder;
	}
	
}//Fin de clase