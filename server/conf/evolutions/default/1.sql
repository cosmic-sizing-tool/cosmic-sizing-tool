# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table organisation (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_organisation primary key (id))
;

create table team_member (
  id                        bigint not null,
  alias                     varchar(255),
  name                      varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  deleted                   boolean,
  constraint pk_team_member primary key (id))
;


create table organisation_team_member (
  organisation_id                bigint not null,
  team_member_id                 bigint not null,
  constraint pk_organisation_team_member primary key (organisation_id, team_member_id))
;
create sequence organisation_seq;

create sequence team_member_seq;




alter table organisation_team_member add constraint fk_organisation_team_member_o_01 foreign key (organisation_id) references organisation (id) on delete restrict on update restrict;

alter table organisation_team_member add constraint fk_organisation_team_member_t_02 foreign key (team_member_id) references team_member (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists organisation;

drop table if exists organisation_team_member;

drop table if exists team_member;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists organisation_seq;

drop sequence if exists team_member_seq;

