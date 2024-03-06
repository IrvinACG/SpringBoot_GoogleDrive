package com.iacg.drive.service.impl;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.iacg.drive.exception.GeneralException;
import com.iacg.drive.exception.catalog.GeneralCatalog;
import com.iacg.drive.service.IGenericFileService;
import com.iacg.drive.util.ParameterDrive;
import com.iacg.drive.util.QueryFileUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio que contiene operacion genericas sobre archivos
 * 
 * @author IACG
 */
@Slf4j
@Service
public class GenericFileService implements IGenericFileService{

	/**
	 * Variable clientDrive
	 */
	@Autowired
	public Drive clientDrive;
	
	/**
	 * Variable queryFileUtils
	 */
	@Autowired
	public QueryFileUtils queryFileUtils;
	
	/**
	 * Metodo que permite crear un archivo
	 * @param fileMetadata Archivo con metadatos
	 * @param inStreamCont Contenido de archivo
	 * @return File respuesta
	 */
	@Override
	public File createFile(File fileMetadata, InputStreamContent inStreamCont) {
		File fileRes = null;
		
		try {
			
			if(Objects.nonNull(inStreamCont)) {
				fileRes = clientDrive.files()
									.create(fileMetadata, inStreamCont)
									.setFields(ParameterDrive.FIELDS_CREATE_FILE)
									.execute();
			} else {
				fileRes = clientDrive.files()
						.create(fileMetadata)
						.setFields(ParameterDrive.FIELDS_CREATE_FILE)
						.execute();
			}
			
		} catch (GoogleJsonResponseException e) {
			log.error("Error al crear archivo, error: {}", e.getDetails().getMessage());
			throw new GeneralException(GeneralCatalog.GRAL004, e.getDetails().getMessage());
		} catch (IOException e) {
			log.error("Error generico al crear archivo, error: {}", e.getMessage());
			throw new GeneralException(GeneralCatalog.GRAL004, e.getMessage());
		}
		
		return fileRes;
	}
	
	/**
	 * Metodo que permite realizar una busqueda de archivos por parametros
	 * @param name Nombre de archivo
	 * @param idFolder Identificador unico de carpeta
	 * @param mimeType Tipo de archivo
	 * @param contains Palabra clave
	 * @return FileList Respuesta
	 */
	@Override
	public FileList listFiles(String name, String idFolder, String mimeType, String contains) {
		FileList result = null;
		
		String query = queryFileUtils.queryListFile(name, idFolder, mimeType, contains);
		log.info("Query listar archivos: {}",query);
		
		try {
			result = clientDrive.files()
								.list()
								.setQ(query)
								.setSpaces(ParameterDrive.SPACES_DRIVE)
								.setFields(ParameterDrive.FIELDS_LIST_FILES)
								 .execute();
		} catch (GoogleJsonResponseException e) {
			log.error("Error al buscar archivos, error: {}", e.getDetails().getMessage());
			throw new GeneralException(GeneralCatalog.GRAL006, e.getDetails().getMessage());
		} catch (IOException e) {
			log.error("Error generico al buscar archivos, error: {}", e.getMessage());
			throw new GeneralException(GeneralCatalog.GRAL004, e.getMessage());
		}
		
		return result;
	}

	/**
	 * Metodo que permite eliminar un archivo por su id
	 * @param id Identificador unico de archivo
	 */
	@Override
	public void deleteFile(String id) {
		try {
			//Verifica, si se intenta eliminar carpeta root
			if(queryFileUtils.isFolderRoot(id)) {
				throw new GeneralException(GeneralCatalog.GRAL004, "No se puede eliminar carpeta root");
			}
			clientDrive.files().delete(id).execute();
		} catch (GoogleJsonResponseException e) {
			log.error("Error al eliminar archivo, error: {}", e.getDetails().getMessage());
			throw new GeneralException(GeneralCatalog.GRAL004, e.getDetails().getMessage());
		} catch (IOException e) {
			log.error("Error generico al eliminar archivo, error: {}", e.getMessage());
			throw new GeneralException(GeneralCatalog.GRAL004, e.getMessage());
		}
	}

	/**
	 * Metodo que permite realizar una busqueda de archivo por id
	 * @param id Identificador unico de archivo
	 * @return File Respuesta
	 */
	@Override
	public File findFileById(String id) {
		File file = null;
		try {
			file = clientDrive.files()
							  .get(id)
							  .setFields(ParameterDrive.FIELDS_FIND_FILE)
							  .execute();
		} catch (GoogleJsonResponseException e) {
			log.error("Error al consultar archivo, error: {}", e.getDetails().getMessage());
			throw new GeneralException(GeneralCatalog.GRAL006, e.getDetails().getMessage());
		} catch (IOException e) {
			log.error("Error generico al consultar archivo, error: {}", e.getMessage());
			throw new GeneralException(GeneralCatalog.GRAL004, e.getMessage());
		}
		return file;
	}

	/**
	 * Metodo que permite verificar si existe un archivo con el nombre (el cual incluye la extension del archivo), dentro
	 * de la misma carpeta
	 * @param name Nombre de archivo
	 * @param idFolder Identificador unico de carpeta
	 * @return boolean Respuesta
	 */
	@Override
	public boolean existFileWithNameAndMimeType(String name, String idFolder) {
		FileList fileList = this.listFiles(name, idFolder, null, null);
		//Regresa true, si existe archivo
		return !fileList.getFiles().isEmpty();
	}

}//Fin de clase
