# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table data_group (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255) not null,
  comment                   varchar(255) not null,
  process                   integer not null,
  entry                     integer not null,
  exit                      integer not null,
  read                      integer not null,
  write                     integer not null)
;

create table organisation (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255),
  description               varchar(255),
  url_orgnisation           varchar(255),
  url_image                 varchar(255),
  nom_contact               varchar(255),
  tel_contact               varchar(255),
  courriel_contact          varchar(255),
  adresse1                  varchar(255),
  adresse2                  varchar(255),
  pays                      varchar(255),
  etat                      varchar(255),
  ville                     varchar(255),
  id_admin                  integer)
;

create table pattern (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(20) not null,
  description_courte        varchar(30) not null,
  description_longue        varchar(250) not null)
;

create table pattern_data_group (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255) not null,
  comment                   varchar(255) not null,
  pattern_process           integer not null,
  entry                     integer not null,
  exit                      integer not null,
  read                      integer not null,
  write                     integer not null)
;

create table pattern_process (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255) not null,
  quality_rating            varchar(1) not null,
  process                   integer not null,
  f_add                     integer not null,
  f_modify                  integer not null,
  f_delete                  integer not null,
  f_unknown                 integer not null,
  f_template                integer not null)
;

create table process (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255) not null,
  quality_rating            varchar(1) not null,
  layer                     integer not null,
  f_add                     integer not null,
  f_modify                  integer not null,
  f_delete                  integer not null,
  f_unknown                 integer not null,
  f_template                integer not null)
;

create table team_member (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255) not null,
  email                     varchar(255) not null,
  password                  varchar(255) not null)
;

create table user (
  id                        integer primary key AUTOINCREMENT,
  alias                     varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  deleted                   integer(1),
  constraint uq_user_email unique (email))
;


create table organisation_user (
  organisation_id                integer not null,
  user_id                        integer not null,
  constraint pk_organisation_user primary key (organisation_id, user_id))
;



alter table organisation_user add constraint fk_organisation_user_organisa_01 foreign key (organisation_id) references organisation (id);

alter table organisation_user add constraint fk_organisation_user_user_02 foreign key (user_id) references user (id);

# --- !Downs

PRAGMA foreign_keys = OFF;

drop table data_group;

drop table organisation;

drop table organisation_user;

drop table pattern;

drop table pattern_data_group;

drop table pattern_process;

drop table process;

drop table team_member;

drop table user;

PRAGMA foreign_keys = ON;
