# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table team_member (
  id                        integer primary key AUTOINCREMENT,
  name                      varchar(255) not null,
  email                     varchar(255) not null,
  password                  varchar(255) not null)
;




# --- !Downs

PRAGMA foreign_keys = OFF;

drop table team_member;

PRAGMA foreign_keys = ON;

