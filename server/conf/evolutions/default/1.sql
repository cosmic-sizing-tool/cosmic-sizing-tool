# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table organisation (
  id                        bigint not null,
  name                      varchar(255),
  id_admin                  bigint,
  constraint pk_organisation primary key (id))
;

create table project (
  id                        bigint not null,
  contact_person            varchar(255),
  organisation              varchar(255),
  country                   varchar(255),
  email                     varchar(255),
  project_id                varchar(255),
  date_submitted            timestamp,
  role_person_submitted_project integer,
  type_software_project     integer,
  project_describes_domain  integer,
  project_reusable          integer,
  number_of_sprints         integer,
  length_sprint             integer,
  story_points              varchar(255),
  team_process_improvement  boolean,
  process_standards         integer,
  programming_language      boolean,
  operating_system          boolean,
  integrated_dev_environment boolean,
  debugging                 boolean,
  database                  boolean,
  object_server             boolean,
  html_web_server           boolean,
  email_message_server      boolean,
  primary_technology_other  varchar(255),
  environment_software_developed_other integer,
  same_implementation_platform boolean,
  primary_implementation_platform integer,
  mobile_device_embedded    integer,
  development_country       varchar(255),
  implemented_country       varchar(255),
  people_plan               integer,
  people_specify            integer,
  people_design             integer,
  people_build              integer,
  people_test               integer,
  people_impl               integer,
  effort_plan               integer,
  effort_specify            integer,
  effort_design             integer,
  effort_build              integer,
  effort_test               integer,
  effort_impl               integer,
  people_summary            integer,
  effort_summary            integer,
  industry_software         integer,
  customer_plan             integer,
  customer_specify          integer,
  customer_design           integer,
  customer_build            integer,
  customer_test             integer,
  customer_impl             integer,
  user_plan                 integer,
  user_specify              integer,
  user_design               integer,
  user_build                integer,
  user_test                 integer,
  user_impl                 integer,
  customer_summary          integer,
  user_summary              integer,
  procedure_development_team integer,
  has_all_the_work_done     boolean,
  prvqst                    integer,
  rtqltwork                 integer,
  assign_above_quality      varchar(255),
  bsnis_app                 integer,
  reltmeapp                 integer,
  matintessapp              integer,
  infrsoft                  integer,
  minor_component           boolean,
  date_software_operation   timestamp,
  total_project_elapsed_duration integer,
  time_total_inactivity     integer,
  constraint ck_project_role_person_submitted_project check (role_person_submitted_project in (0,1,2,3,4,5,6,7)),
  constraint ck_project_type_software_project check (type_software_project in (0,1,2)),
  constraint ck_project_project_describes_domain check (project_describes_domain in (0,1,2,3)),
  constraint ck_project_project_reusable check (project_reusable in (0,1)),
  constraint ck_project_process_standards check (process_standards in (0,1,2,3,4)),
  constraint ck_project_environment_software_developed_other check (environment_software_developed_other in (0,1,2,3)),
  constraint ck_project_primary_implementation_platform check (primary_implementation_platform in (0,1,2,3,4)),
  constraint ck_project_mobile_device_embedded check (mobile_device_embedded in (0,1,2,3,4,5,6)),
  constraint ck_project_industry_software check (industry_software in (0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27)),
  constraint ck_project_procedure_development_team check (procedure_development_team in (0,1,2,3)),
  constraint ck_project_prvqst check (prvqst in (0,1,2)),
  constraint ck_project_rtqltwork check (rtqltwork in (0,1,2,3)),
  constraint ck_project_bsnis_app check (bsnis_app in (0,1,2,3,4,5,6,7,8,9,10,11,12,13,14)),
  constraint ck_project_reltmeapp check (reltmeapp in (0,1,2,3,4,5,6)),
  constraint ck_project_matintessapp check (matintessapp in (0,1,2,3,4,5)),
  constraint ck_project_infrsoft check (infrsoft in (0,1,2,3,4,5)),
  constraint pk_project primary key (id))
;

create table team_member (
  id                        bigint not null,
  name                      varchar(255) not null,
  email                     varchar(255) not null,
  password                  varchar(255) not null,
  constraint pk_team_member primary key (id))
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

create sequence project_seq;

create sequence team_member_seq;

create sequence user_seq;




alter table organisation_user add constraint fk_organisation_user_organisa_01 foreign key (organisation_id) references organisation (id) on delete restrict on update restrict;

alter table organisation_user add constraint fk_organisation_user_user_02 foreign key (user_id) references user (id) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists organisation;

drop table if exists organisation_user;

drop table if exists project;

drop table if exists team_member;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists organisation_seq;

drop sequence if exists project_seq;

drop sequence if exists team_member_seq;

drop sequence if exists user_seq;

