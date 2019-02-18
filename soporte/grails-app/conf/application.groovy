// variables de entorno
VARIABLE_ENTORNO_EMAIL = "soporte_email"
VARIABLE_ENTORNO_EMAIL_PWD = "soporte_email_pwd"
VARIABLE_ENTORNO_DB_USR = "soporte_db_usr"
VARIABLE_ENTORNO_DB_PWD = "soporte_db_pwd"
VARIABLE_ENTORNO_DB_URL = "soporte_db_url"

def validarVariablesDeEntorno() {
    validarVariableDeEntorno(VARIABLE_ENTORNO_EMAIL)
    validarVariableDeEntorno(VARIABLE_ENTORNO_EMAIL_PWD)
    validarVariableDeEntorno(VARIABLE_ENTORNO_DB_USR)
    validarVariableDeEntorno(VARIABLE_ENTORNO_DB_PWD)
    validarVariableDeEntorno(VARIABLE_ENTORNO_DB_URL)
}

def validarVariableDeEntorno(nombre) {
    if (!obtenerVariableDeEntorno(nombre))
        throw new Exception("Falta definir variable de entorno: ${nombre}")
}

def obtenerVariableDeEntorno(nombre) {
    System.getenv().get(nombre)
}

validarVariablesDeEntorno()

// email plugin config
// yahoo
def email = obtenerVariableDeEntorno(VARIABLE_ENTORNO_EMAIL)
grails {
    mail {
        host = "smtp.mail.yahoo.com"
        port = 587
        username = email
        password = obtenerVariableDeEntorno(VARIABLE_ENTORNO_EMAIL_PWD)
        props = [
                    "mail.debug": "true",
                    "mail.smtp.auth":"true",
                    "mail.smtp.starttls.enable":"true"
                ]
    }
}
grails.mail.default.from = email
