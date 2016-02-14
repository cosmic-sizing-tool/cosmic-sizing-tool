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

<<<<<<< HEAD

# --- !Downs

PRAGMA foreign_keys = OFF;
=======
SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists team_member;
>>>>>>> 44197802c4f0fa9c5f821a0dccaa15346a6652dc

drop table team_member;

<<<<<<< HEAD
PRAGMA foreign_keys = ON;

=======
drop sequence if exists team_member_seq;
>>>>>>> 44197802c4f0fa9c5f821a0dccaa15346a6652dc
