# SpringBoot + Google Drive
El proyecto tiene como objetivo crear una API para guardar archivos multimedia y guardar los archivos en el espacio de Drive, el cual esta esta desarrollado con el framework SpringBoot y la API de Google Drive.
Con esta API de puede:
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

Dentro del archivo debe contener el paramatro **client_email**, similiar al siguiente ejemplo:
>  `"client_email" : "xxxxx@sharp-terminal-xxxxx.iam.gserviceaccount.com"`


Con nuestra cuenta de Google, debemos ir a la aplicacion de Drive y crear una carpeta (cualquier nombre). Esta carpeta sera la raiz, en donde se alojaran todos los archivos. 

![Folder_root.png](https://github.com/IrvinACG/SpringBoot_GoogleDrive/blob/main/Folder_root.png "Carpeta raiz")
IMAGEN

Se deben obtener el **id** de la carpeta creada, para ello debemos obtenerlo a traves de la URL. Entramos a la carpeta creada y la URL tendremos algo como esto:
> `https://drive.google.com/drive/folders/xxxxxxx-id-folder-xxxxxxxxxx`
Copiamos el ultimo parametro de la URL, desdepues de **folders/**

El **id** de la carpeta nos servira, para poder configurar el proyecto correctamente.

![Id_folder_root.png](https://github.com/IrvinACG/SpringBoot_GoogleDrive/blob/main/Id_folder_root.png "Id carpeta")
IMAGEN

### Compartir carpeta con la cuenta @sharp-terminal-xxxxx.iam.gserviceaccount.com
Para que nuestra API funciones, es necesario compartir la carpeta raiz con nuestro usuario *@sharp-terminal-xxxxx.iam.gserviceaccount.com*, ya que este sera el usuario encargado de realizar las diferentes operaciones relacionadas con la API y no nuestra correo principal de Google.

![Share_folder.png](https://github.com/IrvinACG/SpringBoot_GoogleDrive/blob/main/Share_folder.png "Compartir carpeta")
IMAGEN
El rol al compartir la carpeta debe ser **Editor**



---
## Configurar proyecto SpringBoot
Copiar el archivo de credenciales **credentials.json** dentro del proyecto en la ruta:
> src/main/resources

### Configuramos el archivo **application.yml**
- En la propiedad **file-name** agregamos el nombre del archivo de las credenciales, el cual esta ubicado en la ruta *src/main/resources*
- En la propiedad **root-id**, agregamos el *id* de la carpeta que creamos en Google Drive


IMAGEN

#### Visualizar endpoints
Si todo esta configurado correctamente, podemos iniciar nuestra aplicacion y probar los endpoints con la ayuda Swagger, en la siguiente direccion:
> `http://localhost:{puerto}/swagger-ui/index.html`
