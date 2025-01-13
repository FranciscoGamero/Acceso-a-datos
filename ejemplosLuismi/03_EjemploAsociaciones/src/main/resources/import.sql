insert into categoria (id, nombre_categoria)
values (0, 'Sin Categoria');
insert into categoria (id, nombre_categoria)
values (nextval('categoria_seq'), 'Categoria 1');
insert into categoria (id, nombre_categoria)
values (nextval('categoria_seq'), 'Categoria 2');
insert into productos (descripcion,nombre,precio, id, categoria_id)
values ('Lorem ipsum dolor sit amet','Botellín fresquito', 1.0, nextval('productos_seq'), currval('categoria_seq'));