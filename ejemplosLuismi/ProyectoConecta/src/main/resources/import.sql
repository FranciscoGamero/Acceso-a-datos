INSERT INTO empresa (id, cif, direccion, coordenadas, nombre, borrado)VALUES (NEXTVAL('empresa_seq'), 'A12345678', 'Calle Falsa 123', '37.3826,-5.9963', 'EmpresaTech', false);
INSERT INTO trabajador (id, empresa_id, nombre, apellidos, email, telefono, puesto, area, borrado) VALUES (NEXTVAL('trabajador_seq'), CURRVAL('empresa_seq') , 'Ana', 'García', 'ana.garcia@techcorp.com', '987654321', 'Manager', 'Recursos Humanos', false);

INSERT INTO empresa (id, cif, direccion, coordenadas, nombre, borrado) VALUES (NEXTVAL('empresa_seq'), 'B87654321', 'Avenida Siempreviva 742', '37.3891,-5.9845', 'EmpresaInnovacion', false);
INSERT INTO trabajador (id, empresa_id, nombre, apellidos, email, telefono, puesto, area, borrado) VALUES (NEXTVAL('trabajador_seq'), CURRVAL('empresa_seq'), 'Juan', 'Pérez', 'juan.perez@innocorp.com', '123456789', 'Desarrollador', 'IT', false);
INSERT INTO trabajador (id, empresa_id, nombre, apellidos, email, telefono, puesto, area, borrado) VALUES (NEXTVAL('trabajador_seq'), CURRVAL('empresa_seq'), 'Pedro', 'Vila', 'pedro.vila@innocorp.com', '987654321', 'Manager', 'Recursos Humanos', false);

INSERT INTO usuario (id, username, password, role, borrado) VALUES (NEXTVAL('usuario_seq'), 'juanp', 'password1', 'ROLE_PROFESOR', false);
INSERT INTO profesor (id, nombre, apellidos, email, telefono, usuario_id, borrado) VALUES (NEXTVAL('profesor_seq'), 'Juan', 'Pérez', 'juan.perez@ejemplo.com', '123456789', CURRVAL('usuario_seq'), false);

INSERT INTO usuario (id, username, password, role,borrado) VALUES (NEXTVAL('usuario_seq'), 'anag', 'password2', 'ROLE_PROFESOR', true);
INSERT INTO profesor (id, nombre, apellidos, email, telefono, usuario_id, borrado) VALUES (NEXTVAL('profesor_seq'), 'Ana', 'García', 'ana.garcia@ejemplo.com', '987654321', CURRVAL('usuario_seq'), true);



INSERT INTO contacto (id_autogenerado, trabajador_id, profesor_id, fecha, canal, resumen, borrado) VALUES (NEXTVAL('contacto_seq'), 1, 1, '2025-01-01 10:00:00', 'email', 'Primera reunión', false);
INSERT INTO contacto (id_autogenerado, trabajador_id, profesor_id, fecha, canal, resumen, borrado) VALUES (NEXTVAL('contacto_seq'), 51, 1, '2025-01-02 11:30:00', 'teléfono', 'Consulta técnica', false);


INSERT INTO familia_profesional (id, nombre, borrado) VALUES (NEXTVAL('familia_profesional_seq'), 'Informática y Comunicaciones', false);
INSERT INTO familia_profesional (id, nombre, borrado) VALUES (NEXTVAL('familia_profesional_seq'), 'Administración y Gestión', false);
INSERT INTO familia_profesional (id, nombre, borrado) VALUES (NEXTVAL('familia_profesional_seq'), 'Sanidad', true);
INSERT INTO familia_profesional (id, nombre, borrado) VALUES (NEXTVAL('familia_profesional_seq'), 'Electricidad y Electrónica', false);
INSERT INTO familia_profesional (id, nombre, borrado) VALUES (NEXTVAL('familia_profesional_seq'), 'Hostelería y Turismo', true);

INSERT INTO titulo (id, nombre, duracion, grado, borrado,familia_profesional_id) VALUES (NEXTVAL('titulo_seq'), 'Ingeniería en Sistemas', 5, 'Licenciatura', false, 1);
INSERT INTO titulo (id, nombre, duracion, grado, borrado,familia_profesional_id) VALUES (NEXTVAL('titulo_seq'), 'Desarrollo de Software', 3, 'Tecnicatura', false, 1);
INSERT INTO titulo (id, nombre, duracion, grado, borrado,familia_profesional_id) VALUES (NEXTVAL('titulo_seq'), 'Ciencias de la Computación', 4, 'Grado', true, 51);
INSERT INTO titulo (id, nombre, duracion, grado, borrado,familia_profesional_id) VALUES (NEXTVAL('titulo_seq'), 'Administración de Empresas', 4, 'Licenciatura', false, 101);
INSERT INTO titulo (id, nombre, duracion, grado, borrado,familia_profesional_id) VALUES (NEXTVAL('titulo_seq'), 'Marketing Digital', 2, 'Tecnicatura', true, 101);

INSERT INTO curso (id, nombre, horas_empresa, borrado, titulo_id) VALUES (NEXTVAL('curso_seq'), 'Java Básico', 40, false, 1);
INSERT INTO curso (id, nombre, horas_empresa, borrado, titulo_id) VALUES (NEXTVAL('curso_seq'), 'Spring Boot Avanzado', 60, true, 51);


INSERT INTO convocatoria (id, curso_escolar, nombre, borrado) VALUES (NEXTVAL('convocatoria_seq'), '2023/2024', 'Convocatoria ordinaria', false);
INSERT INTO convocatoria (id, curso_escolar, nombre, borrado) VALUES (NEXTVAL('convocatoria_seq'), '2023/2024', 'Convocatoria extraordinaria', false);

INSERT INTO demanda (id, cantidad_alumnos, requisitos, borrado, empresa_id, curso_id, convocatoria_id) VALUES (NEXTVAL('demanda_seq'), 5, 'Conocimientos básicos en Java', false, 1, 1, 1);
INSERT INTO demanda (id, cantidad_alumnos, requisitos, borrado, empresa_id, curso_id, convocatoria_id) VALUES (NEXTVAL('demanda_seq'), 3, 'Experiencia previa con Spring Boot', false, 51, 51, 51);
INSERT INTO demanda (id, cantidad_alumnos, requisitos, borrado, empresa_id, curso_id, convocatoria_id) VALUES (NEXTVAL('demanda_seq'), 8, 'Capacidad para trabajar en equipo', false, 1, 1, 51);


INSERT INTO curso_profesores (cursos_id, profesores_id) VALUES (1, 1);
INSERT INTO curso_profesores (cursos_id, profesores_id) VALUES (51, 1);


INSERT INTO empresa_lista_familias (lista_empresas_id, lista_familias_id) VALUES (1, 1);
INSERT INTO empresa_lista_familias (lista_empresas_id, lista_familias_id) VALUES (1, 101);
INSERT INTO empresa_lista_familias (lista_empresas_id, lista_familias_id) VALUES (51, 1);

