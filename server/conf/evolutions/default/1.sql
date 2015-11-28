# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table organisation (
  id                        bigint not null,
  name                      varchar(255),
  id_admin                  bigint,
  constraint pk_organisation primary key (id))
;

create table user (
  id                        bigint not null,
  alias                     varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  deleted                   boolean,
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id))
;


create table organisation_user (
  organisation_id                bigint not null,
  user_id                        bigint not null,
  constraint pk_organisation_user primary key (organisation_id, user_id))
;
create sequence organisation_seq;

create sequence user_seq;




alter table organisation_user add constraint fk_organisation_user_organisa_01 foreign key (organisation_id) references organisation (id) on delete restrict on update restrict;

alter table organisation_user add constraint fk_organisation_user_user_02 foreign key (user_id) references user (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists organisation;

drop table if exists organisation_user;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists organisation_seq;

drop sequence if exists user_seq;

