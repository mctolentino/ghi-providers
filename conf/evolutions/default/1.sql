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
  email_correspondence_flag varchar(255),
  smoker_flag               varchar(255),
  verification_details_id   integer,
  log_id                    integer,
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

create table search_log (
  id                        integer not null,
  member_id                 bigint,
  search_date               timestamp,
  constraint pk_search_log primary key (id))
;

create table security_question (
  id                        bigint not null,
  member_id                 bigint,
  question                  varchar(255),
  answer                    varchar(255),
  constraint pk_security_question primary key (id))
;

create table user (
  username                  varchar(255) not null,
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (username))
;

create table verification_details (
  id                        integer not null,
  member_id                 bigint,
  verified_flag             varchar(255),
  verified_date             timestamp,
  watchlist_flag            varchar(255),
  watchlist_date            timestamp,
  constraint pk_verification_details primary key (id))
;

create sequence member_seq;

create sequence person_seq;

create sequence policy_member_seq;

create sequence question_seq;

create sequence search_log_seq;

create sequence security_question_seq;

create sequence user_seq;

create sequence verification_details_seq;

alter table member add constraint fk_member_person_1 foreign key (person_id) references person (id) on delete restrict on update restrict;
create index ix_member_person_1 on member (person_id);
alter table member add constraint fk_member_verificationDetails_2 foreign key (verification_details_id) references verification_details (id) on delete restrict on update restrict;
create index ix_member_verificationDetails_2 on member (verification_details_id);
alter table member add constraint fk_member_log_3 foreign key (log_id) references search_log (id) on delete restrict on update restrict;
create index ix_member_log_3 on member (log_id);
alter table search_log add constraint fk_search_log_member_4 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_search_log_member_4 on search_log (member_id);
alter table security_question add constraint fk_security_question_member_5 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_security_question_member_5 on security_question (member_id);
alter table verification_details add constraint fk_verification_details_member_6 foreign key (member_id) references member (id) on delete restrict on update restrict;
create index ix_verification_details_member_6 on verification_details (member_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists member;

drop table if exists person;

drop table if exists policy_member;

drop table if exists question;

drop table if exists search_log;

drop table if exists security_question;

drop table if exists user;

drop table if exists verification_details;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists member_seq;

drop sequence if exists person_seq;

drop sequence if exists policy_member_seq;

drop sequence if exists question_seq;

drop sequence if exists search_log_seq;

drop sequence if exists security_question_seq;

drop sequence if exists user_seq;

drop sequence if exists verification_details_seq;

