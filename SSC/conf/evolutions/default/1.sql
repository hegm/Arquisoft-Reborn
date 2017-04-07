# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table consejo (
  id                        bigint not null,
  dieta                     varchar(255),
  rutina                    varchar(255),
  medicamento               varchar(255),
  cita                      varchar(255),
  constraint pk_consejo primary key (id))
;

create table historial (
  id                        bigint not null,
  diagnostico               varchar(255),
  tratamiento               varchar(255),
  examen                    varchar(255),
  fecha                     varchar(255),
  constraint pk_historial primary key (id))
;

create table medicoentity (
  id                        bigint not null,
  nombre                    varchar(255),
  edad                      integer,
  especializacion           varchar(255),
  constraint pk_medicoentity primary key (id))
;

create table notificacion (
  id                        bigint not null,
  presion_sanguinea         float,
  frecuencia_cardiaca       float,
  nivel_de_estres           float,
  constraint pk_notificacion primary key (id))
;

create table operario (
  id                        bigint not null,
  constraint pk_operario primary key (id))
;

create table pacienteentity (
  id                        bigint not null,
  nombre                    varchar(255),
  edad                      integer,
  constraint pk_pacienteentity primary key (id))
;

create table sensorentity (
  id                        bigint not null,
  nombre                    varchar(255),
  tipo                      integer,
  valor                     float,
  constraint pk_sensorentity primary key (id))
;

create sequence Consejo;

create sequence historial;

create sequence Medico;

create sequence Notificacion;

create sequence OperarioEmergencia;

create sequence Paciente;

create sequence Sensor;




# --- !Downs

drop table if exists consejo cascade;

drop table if exists historial cascade;

drop table if exists medicoentity cascade;

drop table if exists notificacion cascade;

drop table if exists operario cascade;

drop table if exists pacienteentity cascade;

drop table if exists sensorentity cascade;

drop sequence if exists Consejo;

drop sequence if exists historial;

drop sequence if exists Medico;

drop sequence if exists Notificacion;

drop sequence if exists OperarioEmergencia;

drop sequence if exists Paciente;

drop sequence if exists Sensor;

