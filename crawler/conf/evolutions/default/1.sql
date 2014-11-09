# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product_details (
  id                        bigint auto_increment not null,
  store                     varchar(255),
  websites                  varchar(255),
  attribute_set             varchar(255),
  type                      varchar(255),
  category_ids              varchar(255),
  sku                       varchar(255),
  has_options               tinyint(1) default 0,
  name                      varchar(255),
  url_key                   varchar(255),
  country_of_manufacture    varchar(255),
  msrp_enabled              varchar(255),
  msrp_display_actual_price_type varchar(255),
  meta_title                varchar(255),
  meta_description          varchar(255),
  image                     varchar(255),
  small_image               varchar(255),
  thumbnail                 varchar(255),
  rotator_image             varchar(255),
  custom_design             varchar(255),
  page_layout               varchar(255),
  options_container         varchar(255),
  gift_message_available    varchar(255),
  image_label               varchar(255),
  small_image_label         varchar(255),
  thumbnail_label           varchar(255),
  url_path                  varchar(255),
  weight                    double,
  price                     double,
  special_price             double,
  msrp                      varchar(255),
  status                    varchar(255),
  visibility                varchar(255),
  featured                  varchar(255),
  tax_class_id              varchar(255),
  is_recurring              varchar(255),
  description               varchar(255),
  short_description         varchar(255),
  meta_keyword              varchar(255),
  custom_layout_update      varchar(255),
  news_from_date            varchar(255),
  news_to_date              varchar(255),
  special_from_date         varchar(255),
  special_to_date           varchar(255),
  custom_design_from        varchar(255),
  custom_design_to          varchar(255),
  qty                       varchar(255),
  min_qty                   varchar(255),
  use_config_min_qty        varchar(255),
  is_qty_decimal            varchar(255),
  backorders                varchar(255),
  use_config_backorders     varchar(255),
  min_sale_qty              varchar(255),
  use_config_min_sale_qty   varchar(255),
  max_sale_qty              varchar(255),
  use_config_max_sale_qty   varchar(255),
  is_in_stock               varchar(255),
  low_stock_date            varchar(255),
  notify_stock_qty          varchar(255),
  use_config_notify_stock_qty varchar(255),
  manage_stock              varchar(255),
  use_config_manage_stock   varchar(255),
  stock_status_changed_auto varchar(255),
  use_config_qty_increments varchar(255),
  qty_increments            varchar(255),
  use_config_enable_qty_inc varchar(255),
  enable_qty_increments     varchar(255),
  is_decimal_divided        varchar(255),
  stock_status_changed_automatically varchar(255),
  use_config_enable_qty_increments varchar(255),
  product_name              varchar(255),
  store_id                  varchar(255),
  product_type_id           varchar(255),
  product_status_changed    varchar(255),
  product_changed_websites  varchar(255),
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

