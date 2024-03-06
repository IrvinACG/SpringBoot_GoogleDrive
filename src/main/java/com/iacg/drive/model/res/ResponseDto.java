package com.iacg.drive.model.res;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO respuesta del estado de la peticion
 * 
 * @author IACG
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto implements Serializable{
	
	/**
	 * Variable serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable status
	 */
	int status;
	
	/**
	 * Variable message
	 */
	private String message;

}//Fin de clase
