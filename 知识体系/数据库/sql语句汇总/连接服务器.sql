EXEC sp_addlinkedsrvlogin 
'PC901', 
'false', 
NULL, 
--远程服务器的登陆用户名 
'sa', 
--远程服务器的登陆密码 
'12345670' 
go 

create view abc
as
select * from KAH_WANGSY.江苏重大疫病基础信息数据库.dbo.Agencies
union all
select * from [PC901\ITZHL].江苏重大疫病基础信息数据库.dbo.Agencies
go 

select * from abc

SELECT top 100 * FROM openquery(dubadg,'select * from DBPAYSYS.
account_base where lower(substr(ACCOUNT,0,4))<>''ksol'' and ACCOUNT_ID> 76942635')