package com.iacg.drive.exception.catalog;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ENUM que contiene el catalogo de errores comunes
 * 
 * @author IACG
 */
@Getter
@AllArgsConstructor
public enum GeneralCatalog {
	
	/**
	 * Error generico de servidor.
	 */
	GRAL001("GeneralException001","GRAL.001","Error generico de servidor",HttpStatus.INTERNAL_SERVER_ERROR,LevelException.ERROR),
	/**
	 * URI parameters no valido.
	 */
	GRAL002("GeneralException002","GRAL.002","Error al enviar parametros",HttpStatus.BAD_REQUEST,LevelException.ERROR),
	/**
	 * URI parameters no valido.
	 */
	GRAL003("GeneralException003","GRAL.003","URI parameters no valido",HttpStatus.BAD_REQUEST,LevelException.ERROR),
	/**
	 * Conflicto al realizar la operación.
	 */
	GRAL004("GeneralException004","GRAL.004","Conflicto al realizar la operación",HttpStatus.CONFLICT,LevelException.ERROR),
	/**
	 * Consulta duplicada.
	 */
	GRAL005("GeneralException005","GRAL.005","Consulta duplicada",HttpStatus.BAD_REQUEST,LevelException.WARN),
	/**
	 * Objeto no encontrado.
	 */
	GRAL006("GeneralException006","GRAL.006","Objeto no encontrado",HttpStatus.NOT_FOUND,LevelException.WARN),
	/**
	 * Metodo HTTP no soportado para este endpoint
	 */
	GRAL007("GeneralExcepcion007","GRAL.007","Metodo HTTP no soportado para este endpoint",HttpStatus.BAD_REQUEST,LevelException.WARN),
	/**
	 * El archivo es demasiado grande, supero el limite maximo
	 */
	GRAL008("GeneralExcepcion008","GRAL.008", "El archivo es demasiado grande, supero el limite maximo", HttpStatus.CONFLICT,LevelException.ERROR);
	
	/**
	 * Variable type
	 */
	private final String type;
	
	/**
	 * Variable code
	 */
	private final String code;
	
	/**
	 * Variable message
	 */
	private final String message;
	
	/**
	 * Variable htttpStatus
	 */
	private final HttpStatus htttpStatus;
	
	/**
	 * Variable levelException
	 */
	private final LevelException levelException;

}//Fin de clase