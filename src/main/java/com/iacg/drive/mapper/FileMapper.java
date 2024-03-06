package com.iacg.drive.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.api.services.drive.model.File;
import com.iacg.drive.model.FileDto;
import com.iacg.drive.model.FolderDto;
import com.iacg.drive.util.ParameterDrive;
import com.iacg.drive.util.QueryFileUtils;

/**
 * Clase que permite mapear una clase File a Dto
 * 
 * @author IACG
 */
@Component
public class FileMapper {
	
	/**
	 * Variable parameterDrive
	 */
	@Autowired
	private QueryFileUtils queryFileUtils;

	/**
	 * Metodo que mapea un File de tipo Drive a un FileDto
	 * @param file Frchivo de tipo Drive
	 * @return FileDto respuesta
	 */
	public FileDto fileToFileDto(File file) {
		FileDto fileDto = new FileDto();
		fileDto.setId(file.getId());
		fileDto.setName(file.getName());
		fileDto.setMimeType(file.getMimeType());
		fileDto.setFileExtension(file.getFileExtension());
		//Verifica que exista carpeta padre
		List<String> parents = Optional.ofNullable(file.getParents()).orElse(null);
		if(Objects.nonNull(parents)) {
			String idFolder = parents.get(0);
			fileDto.setParentFolder(new FolderDto(idFolder, null, queryFileUtils.isFolderRoot(idFolder), null));
		}
		fileDto.setSize(Optional.ofNullable(file.getSize()).orElse(0L));
		fileDto.setCreatAt(file.getCreatedTime().toString());
		if(Objects.nonNull(fileDto.getFileExtension())) {
			fileDto.setThumbnailUrl(ParameterDrive.THUMBNAIL_URL + file.getId());
		}
		return fileDto;
	}	
}//Fin de clase
