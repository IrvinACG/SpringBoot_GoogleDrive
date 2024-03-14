# SpringBoot + Google Drive
El proyecto tiene como objetivo crear una API para guardar archivos multimedia en el espacio de Drive. Esta aplicacion esta desarrollado con el framework SpringBoot y la API de Google Drive.

Con esta API se puede:
- Crear archivos / carpetas
- Listar archivos
- Eliminar archivos

NOTA: las carpetas se consideran archivos.

--- 
## Configurar ambiente Google Drive

Para usar correctamente esta API, es necesario tener configuarado el ambiente de Google Drive, todo esto se puede realizar con la ayuda [Quickstart de Google Developers.](https://developers.google.com/drive/api/quickstart/java?hl=es)
* Habilitar la API Google Drive
* Configurar el cliente de OAuth
* Crear credenciales


Al crear las credenciales, debemos descargar un archivo.
> credentials.json

Nota: El archivo, puede tener otro nombre.

El archivo debe contener el paramatro **client_email**, similiar al siguiente ejemplo:
>  `"client_email" : "xxxxx@sharp-terminal-xxxxx.iam.gserviceaccount.com"`


Con la cuenta de Google, ir a la aplicacion de Drive y crear una carpeta (cualquier nombre). Esta carpeta sera la raiz, en donde se alojaran todos los archivos. 

![Folder_root.png](https://github.com/IrvinACG/SpringBoot_GoogleDrive/blob/main/Folder_root.png "Carpeta raiz")


Se debe obtener el **id** de la carpeta creada, para ello debemos obtenerlo a traves de la URL. Entrar a la carpeta creada, la URL sera algo como esto:
> `https://drive.google.com/drive/folders/xxxxxxx-id-folder-xxxxxxxxxx`

Copiamos el ultimo parametro de la URL, depues de **folders/**
El **id** de la carpeta servira, para poder configurar el proyecto correctamente.

![Id_folder_root.png](https://github.com/IrvinACG/SpringBoot_GoogleDrive/blob/main/Id_folder_root.png "Id carpeta")


### Compartir carpeta con la cuenta @sharp-terminal-xxxxx.iam.gserviceaccount.com
Para que la API funcione, es necesario compartir la carpeta raiz con nuestro usuario *@sharp-terminal-xxxxx.iam.gserviceaccount.com*, ya que este sera el usuario encargado de realizar las diferentes operaciones relacionadas con la API y no el correo principal de Google.

![Share_folder.png](https://github.com/IrvinACG/SpringBoot_GoogleDrive/blob/main/Share_folder.png "Compartir carpeta")

El rol debe ser **Editor**, al compartir la carpeta.


---
## Configurar proyecto SpringBoot
Copiar el archivo de credenciales **credentials.json** dentro del proyecto, en la ruta:
> src/main/resources

### Configurar el archivo **application.yml**
- En la propiedad **file-name** agregar el nombre del archivo de credenciales, el cual esta ubicado en la ruta *src/main/resources*
- En la propiedad **root-id**, agregar el *id* de la carpeta que se creo en Google Drive


![Configuration_springboot.png](https://github.com/IrvinACG/SpringBoot_GoogleDrive/blob/main/Configuration_springboot.png "Configuracion springboot")

#### Visualizar endpoints
Si todo esta configurado correctamente, podemos iniciar la aplicacion y probar los endpoints con la ayuda Swagger, en la siguiente direccion:
> `http://localhost:{puerto}/swagger-ui/index.html`


