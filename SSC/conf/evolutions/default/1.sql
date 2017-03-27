# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ciudad (
  nombre                    varchar(255))
;

create table experiencia_vendedor (
  id                        bigserial not null,
  nombre_empesa             varchar(255),
  cargo                     varchar(255),
  descripcion               varchar(255),
  ano                       integer,
  constraint pk_experiencia_vendedor primary key (id))
;

create table medicoentity (
  id                        bigint not null,
  nombre                    varchar(255),
  edad                      integer,
  especializacion           varchar(255),
  constraint pk_medicoentity primary key (id))
;

create table mueble (
  referencia                bigserial not null,
  nombre                    varchar(255),
  descripcion               varchar(255),
  tipo                      integer,
  precio                    float,
  imagen                    varchar(255),
  cantidad                  integer,
  seleccion                 boolean,
  constraint ck_mueble_tipo check (tipo in (0,1)),
  constraint pk_mueble primary key (referencia))
;

create table pacienteentity (
  id                        bigint not null,
  nombre                    varchar(255),
  apellido                  varchar(255),
  edad                      integer,
  sintomas                  varchar(255),
  entidad_medica            varchar(255),
  estado                    varchar(255),
  presion_sanguinea         float,
  frecuencia_cardiaca       float,
  nivel_estres              integer,
  constraint pk_pacienteentity primary key (id))
;

create table pais (
  nombre                    varchar(255))
;

create table registro_venta (
  fecha_venta               timestamp,
  cantidad                  integer,
  ciudad                    varchar(255))
;

create table usuario (
  documento                 bigserial not null,
  login                     varchar(255),
  contraseña                varchar(255),
  tipo_usuario              integer,
  nombre_completo           varchar(255),
  tipo_documento            integer,
  telefono_local            bigint,
  telefono_celular          bigint,
  direccion                 varchar(255),
  profesion                 integer,
  correo                    varchar(255),
  seleccion                 boolean,
  constraint ck_usuario_tipo_usuario check (tipo_usuario in (0,1)),
  constraint ck_usuario_tipo_documento check (tipo_documento in (0,1)),
  constraint ck_usuario_profesion check (profesion in (0,1,2,3,4,5,6,7)),
  constraint pk_usuario primary key (documento))
;

create table vendedor (
  id                        bigserial not null,
  nombres                   varchar(255),
  apellidos                 varchar(255),
  salario                   float,
  comision_ventas           float,
  perfil                    varchar(255),
  foto                      varchar(255),
  constraint pk_vendedor primary key (id))
;

create sequence Medico;

create sequence Paciente;




# --- !Downs

drop table if exists ciudad cascade;

drop table if exists experiencia_vendedor cascade;

drop table if exists medicoentity cascade;

drop table if exists mueble cascade;

drop table if exists pacienteentity cascade;

drop table if exists pais cascade;

drop table if exists registro_venta cascade;

drop table if exists usuario cascade;

drop table if exists vendedor cascade;

drop sequence if exists Medico;

drop sequence if exists Paciente;

