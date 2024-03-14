package com.iacg.drive.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase modelo de excepcion del @ControllerAdvice que contiene los detalles
 * 
 * @author IACG
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultError {

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
	
	/**
	 * Variable moreInfo
	 */
	private final String url;
	
}//Fin de clase
