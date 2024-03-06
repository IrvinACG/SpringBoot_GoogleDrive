package com.iacg.drive.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.drive.model.File;
import com.iacg.drive.exception.GeneralException;
import com.iacg.drive.exception.catalog.GeneralCatalog;
import com.iacg.drive.mapper.FileMapper;
import com.iacg.drive.model.FileDto;
import com.iacg.drive.model.req.ReqCreateFolderDto;
import com.iacg.drive.service.IFolderDriveService;
import com.iacg.drive.util.MsgError;
import com.iacg.drive.util.ParameterDrive;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio que contine lo metodos necesarios para realizar operaciones,
 * relacionadas con Folder
 * 
 * @author IACG
 */
@Slf4j
@Service
public class FolderDriveService extends GenericFileService implements IFolderDriveService {

	/**
	 * Variable fileMapper
	 */
	@Autowired
	private FileMapper fileMapper;
	
	/**
	 * Metodo que crea una carpeta
	 * 
	 * @param reqFolder Request contiene informacion de carpeta
	 * @return FileDto Respuesta
	 */
	@Override
	public FileDto createFolder(ReqCreateFolderDto reqFolder) {
		log.info("Servicio: FolderDriveService, Operacion: createFolder, inicia...");
		FileDto fileDto = null;
		
		//Validar si carpeta es root
		String idFolderFinal = this.queryFileUtils.verifyIdFolderIsRoot(reqFolder.getIdFolder());
		reqFolder.setIdFolder(idFolderFinal);
		
		//Validar, si ya existe nombre carpeta con el mismo nombre
		boolean existeFolder = super.existFileWithNameAndMimeType(reqFolder.getName(), idFolderFinal);
		if(existeFolder) {
			throw new GeneralException(GeneralCatalog.GRAL005, MsgError.FOLDER_EXIST);
		}
		
		// Metadata de archivo
		File fileMetadata = new File();
		fileMetadata.setName(reqFolder.getName());
		fileMetadata.setParents(Collections.singletonList(reqFolder.getIdFolder()));
		fileMetadata.setMimeType(ParameterDrive.MIMETYPE_FOLDER);

		// Llama a servicio
		File file = super.createFile(fileMetadata, null);
		//Mapea respuesta
		fileDto = fileMapper.fileToFileDto(file);
		log.info("Servicio: FolderDriveService, Operacion: createFolder, termina...");
		return fileDto;
	}

}// Fin de clase
