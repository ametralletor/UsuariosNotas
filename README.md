# UsuariosNotas
API REST Usuarios y Notas Aday Sánchez y Juan Felipe

##  Nombre del Proyecto:

*Sistema de personajes de videojuego*

**AUTORES**
- Aday Sanchez
- Juan Felipe Perez

## Descripcion

Proyecto que combina la UT6 y UT7 que simula un servicio de login donde el usuario 
puede escribir notas y almacenarlas gracias la API REST de Spring Boot

## Objetivos

- Crear un sistema de Login con todo lo que ello implica, validacion de email,
hasheo de contraseña, creacion de usuario y su validacion, para que dichos usuarios
puedan crear notas que solo ellos veran 
- Controlar las Request de la API mediante postman para que los usuarios y el administrador
accedar a distintas funciones del programa
- Crear una estructura de Base de datos con sus relaciones, en este caso usuario con nota
para en situaciones donde el usuario sea eliminado contraproducentemente todas sus notas lo seran

## Competencias Desarrolladas
- Git, Github
- Polimorfismo
- Interfaces
- Herencias
- API REST
- Control de Excepciones Personalizados
- Validaciones
- Conexion con una Base de Datos
- Solicitudes de Postman
- Trabajo en Equipo
- Estructuracion y maquetacion de buenas practicas en un proyecto

## Descripcion
El Proyecto Usa distintas dependencias de SpringBoot (JPA, MySQL, Security, Etc) Para crear
la estructuracion de una base de datos para una pagina web, contamos con dos entidades principales
[Usuario] y [Nota], nota depende directamente de Usuario @OneToMany ya que en primer lugar dichas notas no existiran
si no existe ningun usuario asociado, mientras que la entidad fuerte Usuario @ManyToOne es el contacto directo
del usuario para interactuar con el programa, al ser una entidad fuerte si se elimina actuara una eliminacion en cascada
donde todas las notas huerfanas seran eliminadas en respuesta

Todo nuestro Proyecto esta conectado entre si, ya que requiere un repositorios, servicios y controladores
para su debido funcionamiento, junto con otras caracteristicas como [SecurityConfig] encargado de Hashear las contraseñas
de los usuarios para mayor seguridad y evitar atentar con su informacion personal

Este Proyecto es la cuspide de lo aprendido en todo el año escolar al mezclar todas las competencias
para maquetar este sistema de notas

## Recomendaciones de Ejecucion

Si deseas ejecutar el proyecto necesitaras las Extensiones de SpringBoot en Visual Studio Code, o usar
directamente IntelliJ IDEA para menor complejidad

Tener en cuenta que la base de datos esta en el puerto 3306 y su nombre es usuarioNotas_db, en caso de querer cambiar alguno
de estos parametrosm acceder a la carpeta de resources y cambiar el application.properties

## MENSAJE FINAL

Por Ultimo queriamos agradecerle a Usted Josue, por Acompañarnos en este proceso de aprendizaje y ayudarnos en todo
lo que necesitabamos en este año escolar, mas de la mitad de las cosas hubieran sido mas complicadas de comprender
si su ayuda y sin todas las oportunidades que nos dios en este año, Muchisimas gracias

Por nuestra parte, esperamos verlo el año que viene si es posible

ATTE: Aday Sanchez y Juan Felipe Perez

