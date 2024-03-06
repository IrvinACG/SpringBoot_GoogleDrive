package com.iacg.drive.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase que repesenta la informacion del folder
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FolderDto implements Serializable{
	
	/**
	 * Variable serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable id
	 */
	private String id;
	
	/**
	 * Variable name
	 */
	private String name;
	
	/**
	 * Variable root
	 */
	private boolean root;
	
	/**
	 * Variable parentFolder
	 */
	private FolderDto parentFolder = null;
	
}//Fin de clase
