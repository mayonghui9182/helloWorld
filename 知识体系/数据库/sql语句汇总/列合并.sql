create table tb(feild1 int,feild2 varchar(10))
insert into tb values(1 ,         'a') 
insert into tb values(1 ,         'b') 
insert into tb values(2 ,         'j') 
insert into tb values(2 ,         'g') 
insert into tb values(3 ,         'c') 
insert into tb values(3 ,         'k') 
go

select * from tb

alter function f_hb(@feild1 int) 
returns varchar(8000) 
as 
begin 
  declare @str varchar(8000) 
  set @str = '' 
  select @str = @str + ',' + cast(passport as varchar) from dbo.UserInfo where 用户ID = @feild1
  set @str = right(@str , len(@str) - 1) 
  return(@str) 
End 
go 

--调用自定义函数得到结果： 
select distinct feild1 ,dbo.f_hb(feild1) as feild2 from tb 

drop table tb 
drop function dbo.f_hb 
