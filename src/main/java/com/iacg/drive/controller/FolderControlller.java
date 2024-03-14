package com.iacg.drive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iacg.drive.model.FileDto;
import com.iacg.drive.model.req.ReqCreateFolderDto;
import com.iacg.drive.model.res.ResponseDto;
import com.iacg.drive.model.res.ResponseGenericDto;
import com.iacg.drive.service.IFolderDriveService;
import com.iacg.drive.util.InfoApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestMethod;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase de Comtroller que contiene los metodos, para el funcionamiento
 * de la API Folder
 * 
 * @author IACG
 */
@Setter
@Tag(name = "Folders")
@Slf4j
@RestController
@RequestMapping("/api/v1/files/folders")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
public class FolderControlller {

	/**
	 * Variable de servicio folderService
	 */
	@Autowired
	private IFolderDriveService folderService;
	
	/**
	 * Endpoint que permite crear una carpeta
	 * @param reqFolder request de peticion
	 * @return ResponseEntity<?> respuesta
	 */
	@Operation(summary = InfoApi.FOLDER_CREATE_SUMM, 
			   description = InfoApi.FOLDER_CREATE_DESC, 
			   responses = @ApiResponse(responseCode = "201", description = InfoApi.FOLDER_CREATE_RES))
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseGenericDto<FileDto>> createFolder(@Validated @RequestBody(required = true) ReqCreateFolderDto reqFolder){
		log.info("Metodo: POST, Operacion: createFolder, datos: {}", reqFolder.toString());
		//Llamar a servicio
		FileDto fileDto = this.folderService.createFolder(reqFolder);
		//Crear respuesta
		ResponseGenericDto<FileDto> response = new ResponseGenericDto<>();
		response.setData(fileDto);
		response.setResponse(new ResponseDto(HttpStatus.CREATED.value(), InfoApi.FOLDER_CREATE_RES));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
}//Fin de clase
