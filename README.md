# 📚 Biblioteca Comunitaria Lectores.uy - Tarea 1

Proyecto desarrollado como parte de la **Tarea 1** del curso *Programación de Aplicaciones* de la **Universidad de la República - DGETP**.

## 🗓️ Información Administrativa

- **Inicio:** 11 de Agosto  
- **Entrega final:** 7 de Setiembre, hasta las 23:59hs

---

## 🎯 Objetivos de Aprendizaje

- Aprender las construcciones básicas del lenguaje **Java**
- Crear una interfaz gráfica con **Swing**
- Implementar persistencia de datos con **Hibernate**
- Utilizar herramientas avanzadas como **IDEs** y **SCM**

---

## 🧩 Descripción del Sistema

La Biblioteca Comunitaria está digitalizando su sistema de préstamos. Este MVP incluye:

### 👥 Usuarios
- **Lectores**: pueden solicitar préstamos. Tienen nombre, email, dirección, fecha de registro, estado (`ACTIVO` o `SUSPENDIDO`) y zona.
- **Bibliotecarios**: gestionan materiales y préstamos. Identificados por número de empleado.

### 📦 Materiales
- **Libros**: título y cantidad de páginas
- **Artículos Especiales**: descripción, peso y dimensiones

### 🔄 Préstamos
- Asocian un lector con un material y un bibliotecario
- Estados: `PENDIENTE`, `EN CURSO`, `DEVUELTO`
- Fechas de solicitud y devolución

---

## 🖥️ Requerimientos de GUI

- Menú de navegación con casos de uso
- Uso de `InternalFrames` dentro del `JFrame` principal
- Botones de Aceptar/Cancelar
- ComboBox para datos precargados
- Diálogos para errores/excepciones

---

## 🗃️ Persistencia

Todos los datos deben persistirse utilizando **Hibernate**, siguiendo las prácticas del curso.

---

## 📖 Historias de Usuario (Rol: Administrador)

### 1. Gestión de Usuarios
- Registrar lectores y bibliotecarios
- Modificar estado de lectores
- Cambiar zona de lectores

### 2. Gestión de Materiales
- Registrar libros y artículos especiales
- Consultar donaciones (por fecha opcional)

### 3. Gestión de Préstamos
- Crear préstamos
- Actualizar estado (`EN CURSO`, `DEVUELTO`)
- Consultar préstamos activos (opcional)

### 4. Control y Seguimiento (Opcional)
- Historial por bibliotecario
- Reportes por zona
- Identificar materiales con muchos préstamos pendientes
