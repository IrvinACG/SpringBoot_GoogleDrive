package com.iacg.drive.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.iacg.drive.model.FileDto;

/**
 * Interfaz que contine lo metodos necesarios para realizar operaciones,
 * relacionadas con archivos
 * 
 * @author IACG
 */
public interface IFilesDriveService {

	/**
	 * Metodo que sube un archivo, hacia una carpeta especifica
	 * @param file Archivo multimedia a subir
	 * @param idFolder Identificador unico de carpeta
	 * @return FileDto respuesta
	 */
	FileDto uploadFile(MultipartFile file, String idFolder);
	
	/**
	 * Metodo que permite buscar archivos por parametros
	 * @param name Nombre nombre de archivo con extension 
	 * @param idFolder Identificador unico de carpeta
	 * @param mimeType Tipo de archivo
	 * @param contains Palabra clave
	 * @return List<FileDto> respuesta
	 */
	List<FileDto> findFiles(String name, String idFolder, String mimeType, String contains);
	
	/**
	 * Metodo que permite buscar un archivo por id
	 * @param id Identificador unico de archivo
	 * @return FileDto respuesta
	 */
	FileDto findFile(String id);
	
	/**
	 * Metodo que elimina un archivo
	 * @param idFile Identificador unico de archivo
	 */
	void deleteFile(String idFile);
}
