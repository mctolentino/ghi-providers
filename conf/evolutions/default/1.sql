# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table member (
  id                        bigint not null,
  CREUSER                   bigint,
  CREDATE                   timestamp,
  UPDUSER                   bigint,
  UPDDATE                   timestamp,
  person_id                 bigint,
  payee_id                  bigint,
  customer_account_id       bigint,
  provider_id               bigint,
  email_correspondence_flag boolean,
  smoker_flag               boolean,
  constraint pk_member primary key (id))
;

create table person (
  id                        bigint not null,
  CREUSER                   bigint,
  CREDATE                   timestamp,
  UPDUSER                   bigint,
  UPDDATE                   timestamp,
  gender_code               varchar(255),
  marital_status_id         bigint,
  title_id                  bigint,
  surname                   varchar(255),
  first_name                varchar(255),
  middle_name               varchar(255),
  preferred_name            varchar(255),
  dob                       timestamp,
  phone_home                varchar(255),
  phone_work                varchar(255),
  phone_mobile              varchar(255),
  phone_fax                 varchar(255),
  fax                       varchar(255),
  email                     varchar(255),
  email_confirm_flag        varchar(255),
  occupation_code           varchar(255),
  position_title            varchar(255),
  deceased_date             timestamp,
  nhi                       varchar(255),
  active_flag               varchar(255),
  national_id               varchar(255),
  constraint pk_person primary key (id))
;

create table policy_member (
  id                        bigint not null,
  CREUSER                   bigint,
  CREDATE                   timestamp,
  UPDUSER                   bigint,
  UPDDATE                   timestamp,
  relationship_type_code    varchar(255),
  premium_type_code         varchar(255),
  start_date                timestamp,
  end_date                  timestamp,
  age_applied               bigint,
  suspension_flag           boolean,
  constraint pk_policy_member primary key (id))
;

create table question (
  id                        bigint not null,
  question                  varchar(255),
  constraint pk_question primary key (id))
;

create table security_question (
  member_id                 bigint,
  question                  varchar(255),
  answer                    varchar(255))
;

create sequence member_seq;

create sequence person_seq;

create sequence policy_member_seq;

create sequence question_seq;

alter table member add constraint fk_member_person_1 foreign key (person_id) references person (id) on delete restrict on update restrict;
create index ix_member_person_1 on member (person_id);
alter table security_question add constraint fk_security_question_member_2 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_security_question_member_2 on security_question (member_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists member;

drop table if exists person;

drop table if exists policy_member;

drop table if exists question;

drop table if exists security_question;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists member_seq;

drop sequence if exists person_seq;

drop sequence if exists policy_member_seq;

drop sequence if exists question_seq;

