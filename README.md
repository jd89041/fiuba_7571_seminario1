  # TP Seminario 1

### Requerimientos
* Java 8
* Grails 3.3.8
* MySQL Server 5.7

### Configuración
1) Crear usuario de MySQL. Se deberá usar el usuario root en su defecto. (Opcional)
2) Crear una DB en MySQL (**nombreDB** en adelante)
3) Crear las siguientes variables de entorno:

| Variable | Descripción |
| --- | --- |
|**soporte_db_url**|Define la url a la DB utilizada por el sistema. Utilizar el nombre de la db creada en el paso 2. Ejemplo: //localhost:3306/**nombreDB** define la base de datos llamada **nombreDB** localizada en localhost, puerto 3306. **Nota**: El puerto 3306 es el utilizado por defecto por MySQL)|
|**soporte_db_usr**|Define el usuario de la DB que se va a utilizar para trabajar con los documentos del sistema. **Nota**: Debe tener permisos de escritura|
|**soporte_db_pwd**|Define el password del usuario definido en la variable de entorno **soport_db_url**|
|**soporte_email**|Define el email que se utilizará para enviar los mails desde el sistema|
|**soporte_email_pwd**|Define el password del email definido en la variable de entorno **soporte_email**|
