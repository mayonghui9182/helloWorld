drop table webquerylog ;
create table webquerylog  (
   ID                     NUMBER(20),
   queryDate              VARCHAR2(50),
   userId                 VARCHAR2(50),
   queryWords             varchar2(400),
   urlPlace               varchar2(50),
   clickOrder             varchar2(10),
   urlStr                 VARCHAR2(4000)
);
 create sequence SEQ_webquerylog minvalue 1 maxvalue 999999999999 start with 1 increment by 1 cache 20
