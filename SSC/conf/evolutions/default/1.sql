# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table emergenciaentity (
  id                        bigint not null,
  constraint pk_emergenciaentity primary key (id))
;

create table consejoentity (
  id                        bigint not null,
  dieta                     varchar(255),
  rutina                    varchar(255),
  medicamento               varchar(255),
  cita                      varchar(255),
  hash                      bigint,
  constraint pk_consejoentity primary key (id))
;

create table historialentity (
  id                        bigint not null,
  diagnostico               varchar(255),
  tratamiento               varchar(255),
  examen                    varchar(255),
  fecha                     varchar(255),
  constraint pk_historialentity primary key (id))
;

create table medicoentity (
  id                        bigint not null,
  nombre                    varchar(255),
  edad                      integer,
  especializacion           varchar(255),
  constraint pk_medicoentity primary key (id))
;

create table notificacionentity (
  id                        bigint not null,
  presion_sanguinea         float,
  frecuencia_cardiaca       float,
  nivel_de_estres           float,
  constraint pk_notificacionentity primary key (id))
;

create table operarioentity (
  id                        bigint not null,
  constraint pk_operarioentity primary key (id))
;

create table pacienteentity (
  id                        bigint not null,
  nombre                    varchar(255),
  edad                      integer,

   constraint fk_pacienteentity foreign key (sensorentity, id),
  constraint pk_pacienteentity primary key (id))
;

create table sensorentity (
  id                        bigint not null,
  nombre                    varchar(255),
  tipo                      integer,
  valor                     float,
  constraint pk_sensorentity primary key (id))
;

create sequence emergenciaentity_seq;

create sequence consejoentity_seq;

create sequence historialentity_seq;

create sequence medicoentity_seq;

create sequence notificacionentity_seq;

create sequence operarioentity_seq;

create sequence pacienteentity_seq;

create sequence sensorentity_seq;




# --- !Downs

drop table if exists emergenciaentity cascade;

drop table if exists consejoentity cascade;

drop table if exists historialentity cascade;

drop table if exists medicoentity cascade;

drop table if exists notificacionentity cascade;

drop table if exists operarioentity cascade;

drop table if exists pacienteentity cascade;

drop table if exists sensorentity cascade;

drop sequence if exists emergenciaentity_seq;

drop sequence if exists consejoentity_seq;

drop sequence if exists historialentity_seq;

drop sequence if exists medicoentity_seq;

drop sequence if exists notificacionentity_seq;

drop sequence if exists operarioentity_seq;

drop sequence if exists pacienteentity_seq;

drop sequence if exists sensorentity_seq;

