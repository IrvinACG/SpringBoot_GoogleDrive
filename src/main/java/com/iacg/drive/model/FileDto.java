package com.iacg.drive.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase DTO que contiene los campos del archivo subido
 * 
 * @author IACG
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileDto implements Serializable{
	
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
	 * Variable mimeType
	 */
	private String mimeType;
	
	/**
	 * Variable fileExtension
	 */
	private String fileExtension;
	
	/**
	 * Variable parentFolder
	 */
	private FolderDto parentFolder;
	
	/**
	 * Variable size
	 */
	private long size;
	
	/**
	 * Variable creatAt
	 */
	private String creatAt;
	
	/**
	 * Variable thumbnailUrl
	 */
	private String thumbnailUrl;

}//Fin de clase
