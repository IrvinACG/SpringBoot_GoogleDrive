package com.iacg.drive.exception;

import com.iacg.drive.exception.catalog.GeneralCatalog;

/**
 * Clase de expecion generica
 * 
 * @author IACG
 */
public class GeneralException extends CustomException{

	/**
	 * Constructor de clase
	 * @param catalog Catalogo de errores
	 * @param description Descripcion de error
	 */
	public GeneralException(GeneralCatalog catalog, String description) {
		super(catalog.getHtttpStatus(), catalog.getCode(), catalog.getMessage(), catalog.getLevelException().toString(), description);
	}
	
	/**
	 * Constructor de clase
	 * @param catalog Catalogo de errores
	 */
	public GeneralException(GeneralCatalog catalog) {
		super(catalog.getHtttpStatus(), catalog.getCode(), catalog.getMessage(), catalog.getLevelException().toString());
	}

	/**
	 * Variable serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}//Fin de clase
