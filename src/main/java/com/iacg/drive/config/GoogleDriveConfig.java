package com.iacg.drive.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de configuracion de la API de Google Drive
 * 
 * @author IACG
 */
@Slf4j
@Configuration
public class GoogleDriveConfig{
	
	/**
	 * Variable fileCredential
	 */
	@Value("${google.drive.credentials.file-name}")
	private String fileCredential;
	
	/**
	 * Variable appName
	 */
	@Value("${google.drive.app-name}")
	private String appName;
	
	/**
	 * Bean que crea un cliente autorizado de la API Google Drive
	 * @return Drive cliente de Drive
	 */
	@Bean
	Drive createClient() {
		HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(this.configCredentials());
		// Build a new authorized API client service.
		Drive clientDrive = new Drive.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance(), requestInitializer)
									 .setApplicationName(this.appName)
									 .build();
		return clientDrive;
	}
	
	/**
	 * Bean que crea las configuraciones de autorizacion de API Google
	 * @return GoogleCredentials configfuracion de credenciales
	 */
	@Bean
	GoogleCredentials configCredentials() {
		
		GoogleCredentials credentials = null;
		try {
			//Cargan credenciales desde archivo
			credentials = GoogleCredentials.fromStream(new FileInputStream(this.fileCredentialConfig()))
										   .createScoped(Arrays.asList(DriveScopes.DRIVE_FILE, DriveScopes.DRIVE_APPDATA, DriveScopes.DRIVE));
		} catch (FileNotFoundException e) {
			log.error("No se encontro el archivo de configuracion, error: {}",e.getMessage());
		} catch (IOException e) {
			log.error("Error al cargar archivo de configuracion, error: {}",e.getMessage());
		}
		return credentials;
	}
	
	/**
	 * Bean que carga el archivo de configuracion
	 * @return File arcbivo
	 */
	@Bean
	File fileCredentialConfig() {
		File fileCredential = null;
		try {
			fileCredential = new ClassPathResource(this.fileCredential).getFile();
		} catch (IOException e) {
			log.error("No se pudo encontrar el archivo de configuracion, error: {}",e.getMessage());
		}
		return fileCredential;
	}
	
}//Fin de clase
