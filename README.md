# Servidor Web con Soporte para Múltiples Solicitudes No Concurrentes

En este taller, exploraremos el funcionamiento de un servidor web capaz de manejar múltiples solicitudes de forma secuencial (no concurrente). El servidor leerá archivos desde el disco local y responderá con cualquier archivo solicitado, incluyendo:

Páginas HTML
Archivos JavaScript
Hojas de estilo CSS
Imágenes
Este ejercicio permitirá comprender cómo un servidor procesa peticiones y sirve contenido estático de manera eficiente.

## Comenzando 🚀

Las siguientes instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.

### Tecnologías usadas ⚙️

* [Maven](https://maven.apache.org/) 📦: Gestor de dependencias y automatización de construcción para Java.
* [JavaScript](https://nodejs.org/) 🌐: Lenguaje de programación para interactividad en la web.
* [Java](https://www.java.com/es/) ☕: Lenguaje de programación robusto para backend y aplicaciones empresariales.

```
Give examples
```

### Instalación 📦

Realice los siguientes pasos para clonar el proyecto en su máquina local.

```
git clone https://github.com/Pau993/Taller01.git
cd AREP/
git checkout Taller01
```

### Ejecutando la aplicación ⚙️

Para ejecutar la aplicación, ejecute el siguiente comando:

```
mvn clean compile
mvn exec:java '-Dexec.mainClass=edu.eci.arep.App'
```

El anterior comando limpiará las contrucciones previas, compilará y empaquetará el código en un jar y luego ejecutará la aplicación.

Diríjase a su navegador de preferencia y vaya a la siguiente dirección: http://localhost:35000/ para ver la aplicación en funcionamiento.

## Ejecutando las pruebas ⚙️

Para ejecutar las pruebas, ejecute el siguiente comando:

```
mvn test
```


## Descripción de la aplicación 📖

La aplicación web diseñado como una plataforma visualmente atractiva y funcional, ideal para explorar y gestionar diversos archivos. Su objetivo es proporcionar una interfaz intuitiva y moderna que permita a los usuarios interactuar con elementos como JavaScript, CSS, HTML e imágenes de manera rápida y sencilla. La aplicación combina un diseño elegante con animaciones suaves y una experiencia de usuario optimizada.

## Características principales: ⚙️

1. Interfaz moderna y responsiva:

* Un diseño minimalista con un esquema de colores que incluye degradados de tonos morados, creando una experiencia visual sofisticada.
* Totalmente adaptable a diferentes dispositivos gracias a su diseño responsivo.
  
2. Gestión de archivos: ⚙️

* Incluye botones interactivos que permiten abrir y visualizar archivos clave como:
* Archivos JavaScript (script.js).
* Hojas de estilo CSS (estilos.css).
* Documentos HTML (index.html).
* Imágenes (Chill.jpg).

## Muestra de la aplicación 🧩

![image](https://github.com/user-attachments/assets/85381b19-1d0d-492a-8a35-380d17db9219)


## Autores ✒️

* **Paula Natalia Paez Vega* - *Initial work* - [PurpleBooth](https://github.com/Paulinguis993)

## Licencia 📄

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Agradecimientos 🎁

Agradecimientos al profeso Daniel Benavides por brindarme sus conocimientos.
