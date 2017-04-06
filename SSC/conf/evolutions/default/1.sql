# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table medicoentity (
  id                        bigint not null,
  nombre                    varchar(255),
  edad                      integer,
  especializacion           varchar(255),
  constraint pk_medicoentity primary key (id))
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

create sequence Medico;

create sequence Paciente;




# --- !Downs

drop table if exists medicoentity cascade;

drop table if exists pacienteentity cascade;

drop sequence if exists Medico;

drop sequence if exists Paciente;

