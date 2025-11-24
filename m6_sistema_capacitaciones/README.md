# M7 – AE2 ABP (Ejercicio individual)  
## Sistema de Capacitaciones

Este repositorio contiene la versión **refactorizada y mejorada** del proyecto _Sistema de Capacitaciones_ desarrollada en el **Módulo 7 – AE2 ABP (Ejercicio individual)**.  

Sobre la base del proyecto de Módulo 6, en esta actividad se aplican buenas prácticas de arquitectura, pruebas automatizadas y patrones de diseño para mejorar la mantenibilidad, la legibilidad del código y la trazabilidad del proceso de desarrollo.

---

## 1. Objetivo del proyecto

Implementar un sistema web de gestión de capacitaciones internas para una organización, que permita:

- **Administrador (ADMIN)**  
  - Crear, editar, eliminar y listar cursos de capacitación.  
  - Asignar instructor, cupos y fechas.  
  - Consultar estadísticas de participación.

- **Empleado (EMPLEADO)**  
  - Ver el catálogo de cursos disponibles.  
  - Inscribirse en cursos (respetando cupos y fechas).  
  - Recibir retroalimentación clara del resultado de la inscripción.

Además, la versión de M7 enfatiza:

- Arquitectura **en capas** (controller, service, repository, model).  
- Uso de **patrones de diseño** (especialmente Strategy para notificaciones).  
- Incorporación de **pruebas unitarias**.  
- Manejo explícito de **errores y códigos de estado HTTP**.  
- Documentación y evidencias del **proceso ABP**.

---

## 2. Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3**
- Spring Web (MVC y REST)
- Spring Data JPA
- Spring Security
- Base de datos **H2** en memoria
- Thymeleaf (vistas web)
- Maven como gestor de dependencias y build
- JUnit 5 para pruebas
- Git / GitHub para control de versiones

---

## 3. Arquitectura del sistema

El proyecto sigue la estructura clásica de una aplicación Spring Boot en capas:

- `model/`  
  Clases de dominio:  
  - `Curso`
  - `Empleado`
  - `Instructor`
  - `Inscripcion`

- `repository/`  
  Interfaces que extienden `JpaRepository` para el acceso a datos:
  - `CursoRepository`
  - `EmpleadoRepository`
  - `InstructorRepository`
  - `InscripcionRepository`

- `service/`  
  Lógica de negocio y reglas de validación:
  - `CursoService`
  - `InscripcionService`
  - `EstadisticasCapacitacionesService`
  - Interfaces y estrategias de notificación:  
    - `NotificadorInscripcion`  
    - `NotificadorConsola`  
    - `NotificadorSilencioso`

- `controller/` (Web MVC)
  - `AdminCursoController`  
    - Gestión de cursos para rol ADMIN.
  - `EmpleadoCursoController`  
    - Listado de cursos e inscripción para rol EMPLEADO.
  - `HomeController`  
    - Página de inicio, redirecciones básicas.
  - `CustomErrorController`  
    - Manejo simplificado de páginas de error.

- `api/` (REST)
  - `CursoRestController`  
    - Endpoints REST para CRUD de cursos.
  - `InscripcionRestController`  
    - Endpoints para inscripciones vía API.
  - `InscripcionRequest`  
    - DTO de entrada para peticiones REST.

- `config/`
  - `SecurityConfig`  
    - Configuración de seguridad (roles, rutas protegidas, login, etc.).

- `src/main/resources/templates/`  
  Vistas Thymeleaf para administrador y empleado.

- `src/test/java/...`
  - `EstadisticasCapacitacionesServiceTest`  
    - Pruebas unitarias sobre la lógica de estadísticas.

---

## 4. Mejoras introducidas en M7

### 4.1. Diseño y arquitectura

1. **Separación clara de responsabilidades**
   - La lógica de negocio se centraliza en `CursoService`, `InscripcionService` y `EstadisticasCapacitacionesService`.
   - Los controladores (`AdminCursoController`, `EmpleadoCursoController`, `CursoRestController`, `InscripcionRestController`) se enfocan en:
     - Recibir y validar parámetros de entrada.
     - Delegar la lógica de negocio a los servicios.
     - Preparar modelos/vistas o respuestas REST apropiadas.

2. **Patrón Strategy para notificaciones**
   - Se define la interfaz:
     ```java
     public interface NotificadorInscripcion {
         void notificarInscripcionCreada(Inscripcion inscripcion);
     }
     ```
   - Implementaciones:
     - `NotificadorConsola`: muestra un mensaje en la consola cuando se crea una inscripción.
     - `NotificadorSilencioso`: implementación neutra que no realiza acciones visibles (útil para pruebas o entornos donde no se desea notificar).
   - `InscripcionService` recibe una instancia de `NotificadorInscripcion`, lo que permite cambiar la estrategia de notificación sin modificar la lógica de negocio.

3. **Manejo consistente de errores**
   - Uso de `ResponseStatusException` en servicios para:
     - `404 NOT FOUND` cuando no existe un curso, empleado o inscripción.
     - `400 BAD REQUEST` cuando la operación no se puede realizar (ejemplo: cupos agotados, fecha inválida, etc.).
   - Los controladores REST traducen estas excepciones a respuestas JSON con el código HTTP correspondiente.

### 4.2. Funcionalidad visible en la web

- Vistas de administrador:
  - Listado de cursos.
  - Formulario de creación / edición.
  - Eliminación de cursos.
  - Acceso a estadísticas de capacitaciones.

- Vistas de empleado:
  - Listado de cursos disponibles con detalle de cupos.
  - Acción de inscripción a un curso.
  - Mensajes claros de éxito o error tras la inscripción.

### 4.3. Pruebas unitarias

Se incorpora la clase de pruebas:

- `EstadisticasCapacitacionesServiceTest`:
  - Verifica el cálculo de métricas (ejemplo: porcentaje de ocupación de cupos).
  - Simula cursos e inscripciones para comprobar que la lógica de `EstadisticasCapacitacionesService` es correcta.
  - Aporta evidencia de calidad y comprensión de la capa de servicio.

### 4.4. Calidad del código

- Uso de **inyección de dependencias** por constructor.
- Eliminación de código duplicado y responsabilidades solapadas.
- Métodos con nombres descriptivos y nivel de abstracción coherente.
- Validaciones en la capa de servicio en lugar de en los controladores.

---

## 5. Cómo compilar y ejecutar el proyecto

### 5.1. Requisitos previos

- **Java 17** instalado y configurado en el `PATH`.
- **Maven 3.x** instalado.
- Puerto **8080** disponible.

### 5.2. Compilación y ejecución

En la carpeta raíz del proyecto (`m6_sistema_capacitaciones/`):

```bash
mvn clean package
mvn spring-boot:run
```

La aplicación quedará disponible en:

- `http://localhost:8080`

### 5.3. Usuarios y roles (ejemplo)

El proyecto define usuarios en la configuración de seguridad (o en la base de datos, según la versión).  
Ejemplo típico:

- Usuario ADMIN:
  - Usuario: `admin`
  - Clave: `admin`
  - Rol: `ADMIN`

- Usuario EMPLEADO:
  - Usuario: `empleado`
  - Clave: `empleado`
  - Rol: `EMPLEADO`

*(Ajustar según los datos efectivamente configurados en `SecurityConfig` / `DataLoader`.)*

---

## 6. Cómo probar la aplicación

### 6.1. Navegación web

1. Ir a `http://localhost:8080`.
2. Iniciar sesión según el rol:
   - **ADMIN**:
     - Administrar cursos en `/admin/cursos`.
   - **EMPLEADO**:
     - Ver cursos e inscribirse en `/empleado/cursos`.

3. Verificar:
   - Creación / edición / eliminación de cursos (ADMIN).
   - Inscripción en un curso (EMPLEADO).
   - Actualización de cupos y mensajes de confirmación o error.
   - Consulta de estadísticas (ADMIN).

### 6.2. Endpoints REST (Postman / curl)

Ejemplos de pruebas típicas:

- **GET** lista de cursos:
  ```http
  GET /api/cursos
  ```

- **GET** curso por ID:
  ```http
  GET /api/cursos/{id}
  ```
  - Devuelve **404** si el curso no existe.

- **POST** creación de curso:
  ```http
  POST /api/cursos
  Content-Type: application/json

  {
    "nombre": "Curso de Java Avanzado",
    "descripcion": "Nivel intermedio/avanzado",
    "cupos": 20,
    "fechaInicio": "2025-01-15",
    "fechaFin": "2025-01-30"
  }
  ```
  - Devuelve **400** si los datos no son válidos.

- **POST** inscripción vía API:
  ```http
  POST /api/inscripciones
  Content-Type: application/json

  {
    "empleadoId": 1,
    "cursoId": 1
  }
  ```

  - Devuelve **201** si la inscripción es exitosa.
  - Devuelve **400** o **404** si hay errores (empleado/curso inexistente, sin cupos, etc.).

---

## 7. Estructura del repositorio y entregables

Dentro del ZIP del proyecto se incluyen los siguientes elementos relevantes para la tarea de M7:

- Código fuente completo del sistema:
  - `src/main/java/...`
  - `src/main/resources/...`
- Pruebas unitarias:
  - `src/test/java/.../EstadisticasCapacitacionesServiceTest.java`
- Recursos estáticos y plantillas Thymeleaf.
- Configuración de seguridad y base de datos:
  - `SecurityConfig.java`
  - `application.properties`
- Carpeta `capturas/` con:
  - Tablero de gestión del proyecto (Jira/Trello/GitHub Projects).
  - Diagrama de clases.
  - Diagrama de flujo / navegación.
  - Capturas de vistas web (ADMIN / EMPLEADO).
  - Ejemplos de peticiones/respuestas REST, incluyendo errores 400 y 404.
- Documentación adicional:
  - Este `README.md` para la tarea de **M7 – AE2 ABP**.
  - Informe en formato Org-mode:
    - `M7_AE2_ABP-Ejercicio_individual.org`  

---

## 8. Relación con la rúbrica de M7 – AE2 ABP

Este proyecto busca evidenciar los siguientes aspectos evaluados en la rúbrica técnica:

- **Calidad del diseño**  
  - Arquitectura en capas, desacoplamiento mediante interfaces y patrón Strategy para notificaciones.

- **Correcta implementación de la lógica de negocio**  
  - Servicios con validaciones y manejo centralizado de reglas de negocio.

- **Uso de pruebas unitarias**  
  - `EstadisticasCapacitacionesServiceTest` cubre casos relevantes del cálculo de métricas.

- **Manejo de errores y respuestas HTTP**  
  - `ResponseStatusException` y códigos 400/404 en la API REST.

- **Documentación y evidencias**  
  - `README.md` estructurado.  
  - Carpeta `capturas/` con tablero, diagramas, vistas y pruebas.  
  - Informe `M7_AE2_ABP-Ejercicio_individual.pdf` con reflexión personal sobre el proceso ABP.

---

## 9. Autoría

- **Autor:** Pablo Varas Salamanca
- **Curso / Módulo:** M7 – AE2 ABP (Ejercicio individual)  
- **Proyecto base:** M6 – Sistema de Capacitaciones (refactorizado y mejorado en M7)
