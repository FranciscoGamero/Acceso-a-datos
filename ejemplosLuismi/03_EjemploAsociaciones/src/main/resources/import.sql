insert into productos (descripcion,nombre,precio, id)
values ('Lorem ipsum dolor sit amet','Botellín fresquito', 1.0, nextval('productos_seq'));

insert into categoria (id, nombre_categoria) values (nextval('categoria_seq'), 'Categoria1');
insert into categoria (id, nombre_categoria) values (nextval('categoria_seq'), 'Categoria2');