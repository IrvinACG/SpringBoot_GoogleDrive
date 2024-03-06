package com.iacg.drive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iacg.drive.model.FileDto;
import com.iacg.drive.model.res.ResponseDto;
import com.iacg.drive.model.res.ResponseGenericDto;
import com.iacg.drive.service.IFilesDriveService;
import com.iacg.drive.util.InfoApi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase de Comtroller que contiene los metodos, para el funcionamiento
 * de la API Folder
 * 
 * @author IACG
 */
@Tag(name = "Files")
@Slf4j
@RestController
@RequestMapping("/api/v1/files")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})

public class FileController {
	
	/**
	 * Variable filesService
	 */
	@Autowired
	private IFilesDriveService filesService;
	
	/**
	 * Endpoint que permite subir archivos
	 * @param file Archivo multimedia
	 * @param idFolder Identificador unico de carpeta
	 * @return ResponseEntity<ResponseGenericDto<FileDto>> Respuesta
	 */
	@Operation(summary = InfoApi.FILE_CREATE_SUMM, 
			   description = InfoApi.FILE_CREATE_DESC,
			   responses = @ApiResponse(responseCode = "201", description = InfoApi.FILE_CREATE_RES))
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseGenericDto<FileDto>> uploadFile(@Parameter(description = "Archivo multimedia") 
																 	@RequestParam(name = "file", required = true) MultipartFile file,
																 @Parameter(description = "Identificador unico de carpeta. El valor puede ser 'root'") 
																 	@RequestParam(name = "idFolder", required = true) String idFolder){
		log.info("Metodo: POST, Operacion: uploadFile, datos: {}, {}", file.getOriginalFilename(), idFolder);
		//Llama al servicio
		FileDto fileDto = this.filesService.uploadFile(file, idFolder);
		//Crear respuesta
		ResponseGenericDto<FileDto> response = new ResponseGenericDto<>();
		response.setData(fileDto);
		response.setResponse(new ResponseDto(HttpStatus.CREATED.value(), InfoApi.FILE_CREATE_RES));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
	/**
	 * Endpoint que permite buscar un archivo por id
	 * @param id Identificador unico de archivo
	 * @return ResponseEntity<ResponseGenericDto<FileDto>> Respuesta
	 */
	@Operation(summary = InfoApi.FILE_FIND_SUMM, 
			   description = InfoApi.FILE_FIND_DESC,
			   responses = @ApiResponse(responseCode = "200", description = InfoApi.FILE_FIND_RES))
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseGenericDto<FileDto>> findFile(@Parameter(description = "Identificador unico de archivo") 
																	@PathVariable(name = "id", required = true) String id){
		log.info("Metodo: GET, Operacion: findFile, datos: id:{}",id);
		//Llamar a servicio
		FileDto fileDto = this.filesService.findFile(id);
		//Crear respuesta
		ResponseGenericDto<FileDto> response = new ResponseGenericDto<>();
		response.setData(fileDto);
		response.setResponse(new ResponseDto(HttpStatus.OK.value(), InfoApi.FILE_FIND_RES));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/**
	 * Endpoint que permite buscar archivos por diferentes parametros
	 * @param name Nombre completo de archivo con extension
	 * @param idFolder Identificador unico de carpeta
	 * @param mimeType Tipo de archivo
	 * @param contains Palabra clave
	 * @return ResponseEntity<ResponseGenericDto<FileDto>> Respuesta
	 */
	@Operation(summary = InfoApi.FILE_LIST_SUMM, 
			   description = InfoApi.FILE_LIST_DESC,
			   responses = @ApiResponse(responseCode = "200", description = InfoApi.FILE_LIST_RES))
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseGenericDto<FileDto>> listFiles(@Parameter(description = "Nombre completo de archivo con extension") 
									   		@RequestParam(name = "name", required = false) String name,
									   @Parameter(description = "Identificador unico de carpeta. El valor puede ser 'root'") 
											@RequestParam(name = "idFolder", required = false) String idFolder,
									   @Parameter(description = "Tipo de archivo") 
											@RequestParam(name = "mimeType", required = false) String mimeType,
									   @Parameter(description = "Palabra clave") 
											@RequestParam(name = "contains", required = false) String contains){
		log.info("Metodo: GET, Operacion: listFiles, datos: name:{}, idFolder:{}, mimeType:{}, contains:{}",name ,idFolder, mimeType, contains);
		//Llamar a servicio
		List<FileDto> listFiles = this.filesService.findFiles(name, idFolder, mimeType, contains);
		//Crear respuesta
		ResponseGenericDto<FileDto> response = new ResponseGenericDto<>();
		response.setListData(listFiles);
		response.setResponse(new ResponseDto(HttpStatus.OK.value(), InfoApi.FILE_LIST_RES));
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	/**
	 * Endpoint que permite eliminar un archivo
	 * @param idFile identificador de archivo
	 * @return ResponseEntity<ResponseGenericDto<FileDto>> Respuesta
	 */
	@Operation(summary = InfoApi.FILE_DELETE_SUMM, 
			   description = InfoApi.FILE_DELETE_DESC,
			   responses = @ApiResponse(responseCode = "201", description = InfoApi.FILE_DELETE_RES))
	@DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseGenericDto<FileDto>> deleteFile(@Parameter(description = "Identificador unico archivo") 
											@PathVariable(name = "id", required = true) String id){
		log.info("Metodo: DELETE, Operacion: deleteFile, datos: id:{}",id);
		//Llamar al servicio
		filesService.deleteFile(id);
		//Crear respuesta
		ResponseGenericDto<FileDto> response = new ResponseGenericDto<>();
		response.setResponse(new ResponseDto(HttpStatus.CREATED.value(), InfoApi.FILE_DELETE_RES));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}//Fin de clase
