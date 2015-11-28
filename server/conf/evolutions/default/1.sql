# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table team_member (
  id                        bigint not null,
  name                      varchar(255) not null,
  constraint pk_team_member primary key (id))
;

create sequence team_member_seq;


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists team_member;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists team_member_seq;