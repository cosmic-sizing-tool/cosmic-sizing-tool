# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table certification (
  id_certification          integer primary key AUTOINCREMENT,
  method                    varchar(255) not null,
  version                   varchar(255) not null,
  date                      varchar(255),
  user_id                   integer)
;

create table email (
  id                        integer primary key AUTOINCREMENT,
  addresse                  varchar(255),
  main                      integer(1),
  hidden                    integer(1),
  deleted                   integer(1),
  user_id                   integer,
  constraint uq_email_addresse unique (addresse))
;

create table organisation (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255),
  id_admin                  integer)
;

create table organization (
  time_stamp                timestamp,
  unique_id                 varchar(40),
  name                      varchar(255))
;

create table team_member (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255) not null,
  email                     varchar(255) not null,
  password                  varchar(255) not null)
;

create table user (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255),
  password                  varchar(255),
  alias                     varchar(255),
  deleted                   integer(1),
  disponible                integer(1),
  email                     varchar(255),
  created_at                timestamp,
  url                       varchar(255),
  company                   varchar(255),
  location                  varchar(255),
  constraint uq_user_alias unique (alias),
  constraint uq_user_email unique (email))
;


create table organisation_user (
  organisation_id                integer not null,
  user_id                        integer not null,
  constraint pk_organisation_user primary key (organisation_id, user_id))
;
alter table certification add constraint fk_certification_user_1 foreign key (user_id) references user (id);
create index ix_certification_user_1 on certification (user_id);
alter table email add constraint fk_email_user_2 foreign key (user_id) references user (id);
create index ix_email_user_2 on email (user_id);



alter table organisation_user add constraint fk_organisation_user_organisa_01 foreign key (organisation_id) references organisation (id);

alter table organisation_user add constraint fk_organisation_user_user_02 foreign key (user_id) references user (id);

# --- !Downs

PRAGMA foreign_keys = OFF;

drop table certification;

drop table email;

drop table organisation;

drop table organisation_user;

drop table organization;

drop table team_member;

drop table user;

PRAGMA foreign_keys = ON;

