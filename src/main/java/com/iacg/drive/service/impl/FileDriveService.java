package com.iacg.drive.service.impl;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.iacg.drive.exception.GeneralException;
import com.iacg.drive.exception.catalog.GeneralCatalog;
import com.iacg.drive.mapper.FileMapper;
import com.iacg.drive.model.FileDto;
import com.iacg.drive.service.IFilesDriveService;
import com.iacg.drive.util.MsgError;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Servicio que contine lo metodos necesarios para realizar operaciones,
 * relacionadas con archivos
 * 
 * @author IACG
 */
@Setter
@Slf4j
@Service
public class FileDriveService extends GenericFileService implements IFilesDriveService{
	
	/**
	 * Variable fileMapper
	 */
	@Autowired
	private FileMapper fileMapper;
	
	/**
	 * Metodo que sube un archivo, hacia una carpeta especifica
	 * @param file Archivo multimedia a subir
	 * @param idFolder Identificador unico de carpeta
	 * @return FileDto respuesta
	 */
	@Override
	public FileDto uploadFile(MultipartFile file, String idFolder) {
		log.info("Servicio: FileDriveService, Operacion: uploadFile, inicia...");
		FileDto fileDto = null;
		//Se agrega lo metadatos del archivo
		File fileMedatada = new File();
		fileMedatada.setName(file.getOriginalFilename());
		String idFolderFinal = super.queryFileUtils.verifyIdFolderIsRoot(idFolder);
		fileMedatada.setParents(Collections.singletonList(idFolderFinal));
		
		//Validar, si ya existe archivo con nombre  igual el mismo espacio
		boolean existeFile = super.existFileWithNameAndMimeType(file.getOriginalFilename(), idFolderFinal);
		if(existeFile) {
			throw new GeneralException(GeneralCatalog.GRAL005, MsgError.FILE_EXIST);
		}
		
		InputStreamContent inStreamCont = null;
		try {
			inStreamCont = new InputStreamContent(file.getContentType(), new ByteArrayInputStream(file.getBytes()));
		} catch (IOException e) {
			log.error("Error al procesar archivo, error: {}",e.getMessage());
			throw new GeneralException(GeneralCatalog.GRAL004, MsgError.PROCESS_FILE);
		}

		//Llama a servicio
		File fileRes = super.createFile(fileMedatada, inStreamCont);
		//Mapea respuesta
		fileDto = fileMapper.fileToFileDto(fileRes);			
		log.info("Servicio: FileDriveService, Operacion: uploadFile, termina...");
		return fileDto;
	}
	
	/**
	 * Metodo que permite buscar un archivo por id
	 * @param id Identificador unico de archivo
	 * @return FileDto respuesta
	 */
	@Override
	public FileDto findFile(String id) {
		log.info("Servicio: FileDriveService, Operacion: findFile, inicia...");
		FileDto fileDto = null;
		//Llama a servicio
		File file = super.findFileById(id);
		//Mapea a dto
		fileDto = fileMapper.fileToFileDto(file);
		log.info("Servicio: FileDriveService, Operacion: findFile, termina...");
		return fileDto;
	}
	
	/**
	 * Metodo que permite buscar archivos por parametros
	 * @param name Nombre nombre de archivo con extension 
	 * @param idFolder Identificador unico de carpeta
	 * @param mimeType Tipo de archivo
	 * @param contains Palabra clave
	 * @return List<FileDto> respuesta
	 */
	@Override
	public List<FileDto> findFiles(String name, String idFolder, String mimeType, String contains) {
		log.info("Servicio: FileDriveService, Operacion: findFiles, inicia...");
		List<FileDto> listFilesDto = null;
		
		//Llamar a servicio
		FileList fileList = super.listFiles(name, idFolder, mimeType, contains);
		//Verifica que exista archivos
		List<File> listFile = fileList.getFiles();
		if(listFile.isEmpty()) {
			throw new GeneralException(GeneralCatalog.GRAL006, MsgError.NOT_FOUND_FILES);
		}
		//Mapea file a Dto
		listFilesDto = new ArrayList<>();
		for(File file : listFile) {
			FileDto dto = fileMapper.fileToFileDto(file);
			listFilesDto.add(dto);
		}
		log.info("Servicio: FileDriveService, Operacion: findFiles, termina...");
		return listFilesDto;
	}
	
	/**
	 * Metodo que elimina un archivo
	 * @param idFile Identificador unico de archivo
	 */
	@Override
	public void deleteFile(String idFile) {
		log.info("Servicio: FileDriveService, Operacion: deleteFile, inicia...");
		super.deleteFile(idFile);
		log.info("Servicio: FileDriveService, Operacion: deleteFile, termina...");
	}
}//Fin de clase
