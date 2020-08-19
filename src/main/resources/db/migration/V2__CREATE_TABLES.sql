create table SCHEMA_BRASILPREV.categoria (id_categoria bigint not null, categoria varchar(255), primary key (id_categoria));
create table SCHEMA_BRASILPREV.cliente (id_cliente bigint not null, bairro varchar(255), cep varchar(255), cidade varchar(255), email varchar(255), estado varchar(255), nome varchar(255), rua varchar(255), senha varchar(255), primary key (id_cliente));
create table SCHEMA_BRASILPREV.pedido_item (id_item bigint not null, produto varchar(255), quantidade integer, subtotal decimal(19,2), valor decimal(19,2), id_pedido bigint, id_produto bigint, primary key (id_item));
create table SCHEMA_BRASILPREV.pedido (id_pedido bigint not null, data varchar(255), sessao varchar(255), status varchar(255), id_cliente bigint, primary key (id_pedido));
create table SCHEMA_BRASILPREV.produto (id_produto bigint not null, descricao varchar(255), foto varchar(255), preco decimal(19,2), produto varchar(255), quantidade integer, id_categoria bigint, primary key (id_produto));
create sequence hibernate_sequence start with 1 increment by 1;
alter table SCHEMA_BRASILPREV.pedido_item add constraint FKrgh7l50r1judvc3ok9y8sfrv3 foreign key (id_pedido) references SCHEMA_BRASILPREV.pedido;
alter table SCHEMA_BRASILPREV.pedido_item add constraint FK83345b0ifop00o8g4ttonqm77 foreign key (id_produto) references SCHEMA_BRASILPREV.produto;
alter table SCHEMA_BRASILPREV.pedido add constraint FKdnomiluem4t3x66t6b9aher47 foreign key (id_cliente) references SCHEMA_BRASILPREV.cliente;
alter table SCHEMA_BRASILPREV.produto add constraint FKbq4e9crnlcsvm7ehu147wuavt foreign key (id_categoria) references SCHEMA_BRASILPREV.categoria;