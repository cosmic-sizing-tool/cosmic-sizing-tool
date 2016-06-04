# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table basic_user (
  username                      varchar(255),
  email                         varchar(255),
  password                      varchar(255),
  constraint uq_basic_user_username unique (username),
  constraint uq_basic_user_email unique (email)
);

create table certification (
  id_certification              bigserial not null,
  method                        varchar(255) not null,
  version                       varchar(255) not null,
  date                          varchar(255),
  user_id                       bigint,
  constraint pk_certification primary key (id_certification)
);

create table data_group (
  id                            bigserial not null,
  name                          varchar(255) not null,
  comment                       varchar(255) not null,
  process                       bigint not null,
  entry                         integer not null,
  exit                          integer not null,
  read                          integer not null,
  write                         integer not null,
  constraint pk_data_group primary key (id)
);

create table email (
  id                            bigserial not null,
  addresse                      varchar(255),
  main                          boolean,
  hidden                        boolean,
  deleted                       boolean,
  user_id                       bigint,
  constraint uq_email_addresse unique (addresse),
  constraint pk_email primary key (id)
);

create table organisation (
  id                            bigserial not null,
  name                          varchar(255),
  description                   varchar(255),
  url_orgnisation               varchar(255),
  url_image                     varchar(255),
  nom_contact                   varchar(255),
  tel_contact                   varchar(255),
  courriel_contact              varchar(255),
  adresse1                      varchar(255),
  adresse2                      varchar(255),
  pays                          varchar(255),
  etat                          varchar(255),
  ville                         varchar(255),
  id_admin                      bigint,
  constraint pk_organisation primary key (id)
);

create table organisation_user (
  organisation_id               bigint not null,
  user_id                       bigint not null,
  constraint pk_organisation_user primary key (organisation_id,user_id)
);

create table organization (
  time_stamp                    timestamp,
  unique_id                     uuid,
  name                          varchar(255)
);

create table pattern (
  id                            bigserial not null,
  name                          varchar(20) not null,
  description_courte            varchar(30) not null,
  description_longue            varchar(250) not null,
  constraint pk_pattern primary key (id)
);

create table pattern_data_group (
  id                            bigserial not null,
  name                          varchar(50) not null,
  mouvement                     varchar(4) not null,
  process                       bigint not null,
  constraint pk_pattern_data_group primary key (id)
);

create table pattern_process (
  id                            bigserial not null,
  name                          varchar(50) not null,
  pattern                       bigint not null,
  constraint pk_pattern_process primary key (id)
);

create table process (
  id                            bigserial not null,
  name                          varchar(255) not null,
  quality_rating                varchar(1) not null,
  layer                         bigint not null,
  f_add                         integer not null,
  f_modify                      integer not null,
  f_delete                      integer not null,
  f_unknown                     integer not null,
  f_template                    integer not null,
  constraint pk_process primary key (id)
);

create table project (
  project_id                    varchar(255) not null,
  id                            bigint,
  contact_person                varchar(255),
  organisation                  varchar(255),
  country                       varchar(255),
  email                         varchar(255),
  date_submitted                timestamp,
  role_person_submitted_project integer,
  role_person_other             varchar(255),
  type_software_project         integer,
  software_type_other           varchar(255),
  project_describes_domain      integer,
  project_reusable              integer,
  nb_sprint                     integer,
  length_sprint                 integer,
  story_points                  varchar(255),
  team_process_improvement      boolean,
  process_standards             integer,
  prcs_stndrs_other             varchar(255),
  programming_language          boolean,
  operating_system              boolean,
  integrated_dev_environment    boolean,
  debugging                     boolean,
  database                      boolean,
  object_server                 boolean,
  html_web_server               boolean,
  email_message_server          boolean,
  primary_technology_other      varchar(255),
  environment_software_developed_other integer,
  envrnmnt_sftre_dvloped_other  varchar(255),
  same_implementation_platform  boolean,
  primary_implementation_platform integer,
  prim_impl_platform_other      varchar(255),
  mobile_device_embedded        integer,
  mbl_dvc_embd_other            varchar(255),
  development_country           varchar(255),
  implemented_country           varchar(255),
  people_plan                   integer,
  people_specify                integer,
  people_design                 integer,
  people_build                  integer,
  people_test                   integer,
  people_impl                   integer,
  effort_plan                   integer,
  effort_specify                integer,
  effort_design                 integer,
  effort_build                  integer,
  effort_test                   integer,
  effort_impl                   integer,
  people_summary                integer,
  effort_summary                integer,
  industry_software             integer,
  industry_software_other       varchar(255),
  customer_plan                 integer,
  customer_specify              integer,
  customer_design               integer,
  customer_build                integer,
  customer_test                 integer,
  customer_impl                 integer,
  user_plan                     integer,
  user_specify                  integer,
  user_design                   integer,
  user_build                    integer,
  user_test                     integer,
  user_impl                     integer,
  customer_summary              integer,
  user_summary                  integer,
  procedure_development_team    integer,
  prcdr_dvlpmnt_tem_other       varchar(255),
  has_all_the_work_done         boolean,
  prvqst                        integer,
  prev_question_other           varchar(255),
  rtqltwork                     integer,
  assign_above_quality          varchar(255),
  bsnis_app                     integer,
  reltmeapp                     integer,
  matintessapp                  integer,
  infrsoft                      integer,
  minor_component               boolean,
  date_software_operation       timestamp,
  total_project_elapsed_duration integer,
  time_total_inactivity         integer,
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
  constraint pk_project primary key (project_id)
);

create table team_member (
  id                            bigserial not null,
  name                          varchar(255) not null,
  email                         varchar(255) not null,
  password                      varchar(255) not null,
  constraint pk_team_member primary key (id)
);

create table timer (
  timer_id                      bigserial not null,
  organization_id               bigint,
  project_id                    bigint,
  start_time                    timestamp not null,
  end_time                      timestamp,
  constraint pk_timer primary key (timer_id)
);

create table "user" (
  id                            bigserial not null,
  name                          varchar(255),
  password                      varchar(255),
  alias                         varchar(255),
  deleted                       boolean,
  disponible                    boolean,
  email                         varchar(255),
  created_at                    timestamp,
  url                           varchar(255),
  company                       varchar(255),
  location                      varchar(255),
  constraint uq_user_alias unique (alias),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id)
);

alter table certification add constraint fk_certification_user_id foreign key (user_id) references "user" (id) on delete restrict on update restrict;
create index ix_certification_user_id on certification (user_id);

alter table email add constraint fk_email_user_id foreign key (user_id) references "user" (id) on delete restrict on update restrict;
create index ix_email_user_id on email (user_id);

alter table organisation_user add constraint fk_organisation_user_organisation foreign key (organisation_id) references organisation (id) on delete restrict on update restrict;
create index ix_organisation_user_organisation on organisation_user (organisation_id);

alter table organisation_user add constraint fk_organisation_user_user foreign key (user_id) references "user" (id) on delete restrict on update restrict;
create index ix_organisation_user_user on organisation_user (user_id);


# --- !Downs

alter table if exists certification drop constraint if exists fk_certification_user_id;
drop index if exists ix_certification_user_id;

alter table if exists email drop constraint if exists fk_email_user_id;
drop index if exists ix_email_user_id;

alter table if exists organisation_user drop constraint if exists fk_organisation_user_organisation;
drop index if exists ix_organisation_user_organisation;

alter table if exists organisation_user drop constraint if exists fk_organisation_user_user;
drop index if exists ix_organisation_user_user;

drop table if exists basic_user cascade;

drop table if exists certification cascade;

drop table if exists data_group cascade;

drop table if exists email cascade;

drop table if exists organisation cascade;

drop table if exists organisation_user cascade;

drop table if exists organization cascade;

drop table if exists pattern cascade;

drop table if exists pattern_data_group cascade;

drop table if exists pattern_process cascade;

drop table if exists process cascade;

drop table if exists project cascade;

drop table if exists team_member cascade;

drop table if exists timer cascade;

drop table if exists "user" cascade;
