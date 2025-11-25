# Portafolio técnico de Pablo Varas

Este repositorio reúne proyectos desarrollados durante el módulo, junto con enlaces a una demo publicada en la nube, al código fuente y al portafolio visual en Behance.  
Su objetivo es servir como carta de presentación técnica y concentrar en un solo lugar el sistema desarrollado, el proceso de creación, los retos encontrados y las mejoras realizadas a partir de la retroalimentación.

---

## 1. Sobre mí

Soy estudiante del área de desarrollo de software, con especial interés en:

- Aplicaciones web con **Java y Spring Boot**.
- Desarrollo de APIs REST y consumo de servicios.
- Buenas prácticas de desarrollo: control de versiones con Git, documentación y pruebas básicas.

Este portafolio se irá ampliando a medida que incorpore nuevos proyectos y evidencia de aprendizaje.

---

## 2. Portafolio en GitHub y estructura del repositorio

Este repositorio en GitHub funciona como **portafolio de productos** del módulo. La estructura principal es:

- `m6_sistema_capacitaciones/`  
  Proyecto completo del **Sistema de Capacitaciones** (Spring Boot + Thymeleaf).

- `capturas/`  
  Carpeta con capturas de pantalla utilizadas como evidencia del hosting y de la demo en producción.

- `m6_sistema_capacitaciones.zip`  
  Versión comprimida del proyecto para su descarga como entregable.

- `README.md`  
  Este documento de presentación general del portafolio.

Los proyectos están organizados en carpetas claras y cada proyecto cuenta con su propio README específico (cuando corresponde), siguiendo buenas prácticas de documentación.

En el futuro se podrán agregar nuevas carpetas con más proyectos o ejercicios relevantes.

---

## 3. Proyecto destacado: Sistema de Capacitaciones

Proyecto desarrollado con **Spring Boot 3**, **Spring Data JPA**, **Thymeleaf** y base de datos H2/MariaDB para gestionar cursos de capacitación, empleados, instructores e inscripciones.

### 3.1 Funcionalidades principales

- Gestión de cursos (crear, editar, listar, eliminar).
- Gestión de empleados e instructores.
- Inscripción de empleados en cursos disponibles.
- Módulo web con vistas Thymeleaf.
- Control de acceso por roles (**ADMIN** y **EMPLEADO**).

### 3.2 Código fuente

El código fuente completo del proyecto se encuentra en:

- **Código fuente (GitHub):**  
  https://github.com/pabvarass/Portafolio/tree/main/m6_sistema_capacitaciones

Dentro de esa carpeta se incluye:

- Código fuente organizado en capas (`controller`, `service`, `repository`, `model`, `api`, `config`…).
- Archivo `pom.xml` con las dependencias del proyecto.
- `src/main/resources/application.properties` con la configuración de la aplicación.
- Carpeta `capturas/` con evidencias del funcionamiento.
- Un `README.md` específico del proyecto con instrucciones de uso local (compilación, ejecución y pruebas).

### 3.3 Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA / Hibernate**
- **Thymeleaf**
- **H2 / MariaDB**
- **Maven**
- **Railway** como plataforma de despliegue en la nube.

### 3.4 Proceso de creación, retos y soluciones

El Sistema de Capacitaciones se desarrolló de forma incremental a lo largo del módulo:

1. **Diseño de entidades y relaciones:**  
   - Definición de las clases `Curso`, `Empleado`, `Instructor` e `Inscripcion`.  
   - Reto: modelar correctamente las relaciones (por ejemplo, las inscripciones de un empleado a varios cursos).  
   - Solución: uso de anotaciones JPA (`@ManyToOne`, `@OneToMany`) y revisión de la estructura de tablas en la base de datos.

2. **Separación por capas (MVC + Service):**  
   - Implementación de controladores web, servicios y repositorios JPA.  
   - Reto: mantener una buena organización del código y evitar lógica de negocio en los controladores.  
   - Solución: mover la lógica a la capa `service` y utilizar DTOs/métodos específicos para cada caso de uso.

3. **Seguridad con Spring Security:**  
   - Implementación de autenticación y autorización con roles ADMIN y EMPLEADO.  
   - Reto: proteger las rutas correctas y configurar el login/logout de manera adecuada.  
   - Solución: creación de una clase de configuración de seguridad, definición de rutas protegidas y pruebas manuales de acceso con distintos usuarios.

4. **Despliegue en Railway:**  
   - Reto: ajustar la configuración para que la aplicación funcione correctamente en un entorno cloud (perfil de base de datos, puerto, variables de entorno).  
   - Solución: configuración específica en `application.properties`, uso de variables de entorno y pruebas del despliegue hasta lograr una versión estable.

---

## 4. Demo en la nube (servicio cloud)

De acuerdo a las instrucciones de la actividad, el proyecto se publica en un servicio de hosting gratuito en la nube.

- **Servicio cloud utilizado:** Railway  
- **Demo en producción (Railway):**  
  https://portafolio-production-ee33.up.railway.app

Desde esa URL se puede acceder a la aplicación web del Sistema de Capacitaciones desplegada en la nube y comprobar su funcionamiento en línea.

---

## 5. Evidencias visuales de hosting y demo en producción

A continuación se presentan capturas de pantalla como respaldo del hosting seleccionado y del funcionamiento de la demo en producción.

### 5.1 Servicio desplegado en Railway

Captura del servicio configurado y en ejecución en Railway:

![Servicio desplegado en Railway](capturas/railway.png)

### 5.2 Pantalla de inicio de sesión en producción

Captura del formulario de inicio de sesión del Sistema de Capacitaciones en el entorno de producción (Railway):

![Pantalla de login en producción](capturas/login_railway.png)

---

## 5.3 Portafolio en Behance

Presentación del proyecto UX/UI basado en el Sistema de Capacitaciones:

- Enlace al proyecto:  
  [Sistema de Capacitaciones - Plataforma Web](https://www.behance.net/gallery/239250149/Sistema-de-Capacitaciones-Plataforma-Web)

El proyecto en Behance contiene:

- Descripción del problema y objetivos.
- Wireframes y flujo de navegación.
- Diseño de interfaz y decisiones de UX/UI.
- Reflexión sobre los aprendizajes obtenidos durante el módulo.

---

## 6. Video de presentación técnica

Como parte del portafolio se incorporará un video técnico (3–5 minutos) donde se explica el Sistema de Capacitaciones, su arquitectura y las tecnologías utilizadas.

En el video se cubrirán los siguientes puntos (según lo solicitado en la actividad):

- Breve **introducción** sobre el proyecto.
- La **tecnología utilizada** (stack Java / Spring Boot / Railway).
- **Demostración de funcionalidades clave** del producto (gestión de cursos, inscripción de empleados, login por roles, etc.).
- Comentario de algunos **desafíos** enfrentados durante el desarrollo y cómo se solucionaron.

**Enlace:** se agregará aquí el vínculo cuando el video esté publicado en YouTube.

---

## 7. Retroalimentación recibida y mejoras realizadas

Como parte de la evaluación del módulo, el portafolio y el Sistema de Capacitaciones fueron revisados por compañeros/as y docentes, quienes entregaron comentarios sobre:

- Claridad de la navegación y organización de las pantallas.
- Presentación visual del portafolio (GitHub y Behance).
- Descripción de las funcionalidades en el README y en el video.

A partir de esa retroalimentación se realizaron las siguientes mejoras:

- Ajuste y ampliación de la descripción del proyecto en este README, incluyendo el proceso de creación y los retos enfrentados.
- Incorporación de capturas adicionales del despliegue en Railway y de la pantalla de login en producción.
- Corrección y mejora de algunos textos en la interfaz y en la documentación para hacerla más clara y profesional.

Esta sección deja constancia de la **aplicación de la retroalimentación** para depurar y mejorar la presentación y la funcionalidad del producto final.

---

## 8. Cómo clonar este repositorio

Para clonar el portafolio en tu equipo:

```bash
git clone https://github.com/pabvarass/Portafolio.git
cd Portafolio
```

Para ejecutar el proyecto principal (`m6_sistema_capacitaciones`) en local:

1. Importar el proyecto como **Maven Project** en tu IDE (STS, IntelliJ, etc.).
2. Revisar el archivo `src/main/resources/application.properties` y ajustar la configuración de la base de datos si es necesario.
3. Ejecutar:

   ```bash
   mvn spring-boot:run
   ```

4. Abrir el navegador en: `http://localhost:8080/`.

---

