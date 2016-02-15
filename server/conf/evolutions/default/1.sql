# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table organisation (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255),
  id_admin                  integer)
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

drop table organisation;

drop table organisation_user;

drop table team_member;

drop table user;

PRAGMA foreign_keys = ON;

