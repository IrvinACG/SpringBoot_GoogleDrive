package com.iacg.drive.model.res;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO de Respuesta generica
 * @param <T> Objeto personalizado
 * 
 * @author IACG
 */
@Setter
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseGenericDto<T extends Serializable> implements Serializable{

	/**
	 * Variable serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable data
	 */
	private T data;
	
	/**
	 * Variable list
	 */
	private List<T> listData;
	
	/**
	 * Variable response
	 */
	private ResponseDto response;
	
	/**
	 * Constructor de clase
	 * @param data Datos
	 * @param response Response
	 */
	public ResponseGenericDto(T data, ResponseDto response){
		this.data = data;
		this.response = response;
	}	
	
	/**
	 * Constructor de clase
	 * @param list Lista de datos
	 * @param response Response
	 */
	public ResponseGenericDto(List<T> list, ResponseDto response){
		this.listData = list;
		this.response = response;
	}
	
	/**
	 * Constructor de clase
	 * @param response Response
	 */
	public ResponseGenericDto(ResponseDto response){
		this.response = response;
	}
	
}//Fin de clase
