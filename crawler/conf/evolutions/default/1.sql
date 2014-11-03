# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product_details (
  id                        bigint auto_increment not null,
  product_name              varchar(255),
  description               varchar(255),
  short_description         varchar(255),
  meta_description          varchar(255),
  meta_keywords             varchar(255),
  sku                       varchar(255),
  weight                    double,
  status                    varchar(255),
  visibility                varchar(255),
  search                    varchar(255),
  price                     double,
  quantity                  double,
  tax_class                 varchar(255),
  categories                varchar(255),
  meta_title                varchar(255),
  images                    varchar(255),
  constraint pk_product_details primary key (id))
;

create table scrapped_data (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  image                     varchar(255),
  link                      varchar(255),
  regular_price             varchar(255),
  special_price             varchar(255),
  offers                    varchar(255),
  date                      datetime,
  site                      varchar(255),
  key_value                 varchar(255),
  status                    tinyint(1) default 0,
  product_details_id        bigint,
  constraint pk_scrapped_data primary key (id))
;

alter table scrapped_data add constraint fk_scrapped_data_productDetails_1 foreign key (product_details_id) references product_details (id) on delete restrict on update restrict;
create index ix_scrapped_data_productDetails_1 on scrapped_data (product_details_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table product_details;

drop table scrapped_data;

SET FOREIGN_KEY_CHECKS=1;

