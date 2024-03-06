package com.iacg.drive.exception;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase Modelo de excepcion, contiene los parametros detallados
 * 
 * @author IACG
 */
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomException extends RuntimeException{

	/**
	 * Constructor de clase
	 * @param httpStatus Estado Http
	 * @param code Codigo
	 * @param message Mensaje
	 * @param level Nivel de error
	 * @param description Descripcion de error
	 */
	public CustomException(HttpStatus httpStatus, String code, String message, String level, String description) {
		this.status = httpStatus.value();
		this.code = code;
		this.message = message;
		this.level = level;
		this.description = description;
	}
	
	/**
	 * Constructor de clase
	 * @param httpStatus Estado Http
	 * @param code Codigo
	 * @param message Mensaje
	 * @param level Nivel de error
	 */
	public CustomException(HttpStatus httpStatus, String code, String message, String level) {
		this.status = httpStatus.value();
		this.code = code;
		this.message = message;
		this.level = level;
		this.description = null;
	}
	
	/**
	 * Variable serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable httpStatus
	 */
	private final int status;
	
	/**
	 * Variable code
	 */
	private final String code;
	
	/**
	 * Variable message
	 */
	private final String message;
	
	/**
	 * Variable level
	 */
	private final String level;
	
	/**
	 * Variable description
	 */
	private final String description;
}//Fin de clase
