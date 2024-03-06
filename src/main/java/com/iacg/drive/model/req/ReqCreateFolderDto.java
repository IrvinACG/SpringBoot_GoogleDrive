package com.iacg.drive.model.req;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase Request para carpeta
 * 
 * @author IACG
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqCreateFolderDto implements Serializable{

	/**
	 * Variable serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable name
	 */
	@NotBlank(message = "The name is required.")
	private String name;
	
	/**
	 * Variable idFolder
	 */
	private String idFolder;
	
}//Fin de clase
