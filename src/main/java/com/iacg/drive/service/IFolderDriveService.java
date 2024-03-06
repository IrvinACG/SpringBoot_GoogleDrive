package com.iacg.drive.service;

import com.iacg.drive.model.FileDto;
import com.iacg.drive.model.req.ReqCreateFolderDto;

/**
 * Interfaz que contine lo metodos necesarios para realizar operaciones,
 * relacionadas con carpetas
 * 
 * @author IACG
 */
public interface IFolderDriveService {
	
	/**
	 * Metodo que crea una carpeta
	 * @param reqFolder Request contiene informacion de carpeta
	 * @return String FileDto Respuesta
	 */
	FileDto createFolder(ReqCreateFolderDto reqFolder);
	
}//Fin de clase
