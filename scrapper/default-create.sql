create table scrapped_data (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  image                     varchar(255),
  link                      varchar(255),
  regular_price             varchar(255),
  special_price             varchar(255),
  site                      varchar(255),
  constraint pk_scrapped_data primary key (id))
;



