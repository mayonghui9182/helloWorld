declare @Temp table
( 
mydate smalldatetime 
) 
insert into @Temp 
select convert(datetime,'2009-01-01',121) 
declare @i int
set @i=1 
while @i <(select datediff(day,convert(smalldatetime,'2009-01-01',121),convert(smalldatetime,'2009-12-31',121))+1) 
begin 
insert into @Temp(mydate) values (dateadd(day,@i,convert(smalldatetime,'2009-01-01',121))) 
set @i=@i+1 
end 
select mydate,datepart(weekday,mydate) from @Temp 

select top 10 * from dbo.DimDate order by datetime desc

create proc dbo.CreateDate @d datetime,@e datetime as
while @d<@e
begin
	insert into dimdate select @d,convert(char(10),@d,121),convert(varchar(4),datepart(yyyy,@d))+'年第'+convert(varchar(4),DATEPART(ww,@d))+'周',
convert(varchar(4),datepart(yyyy,@d))+'年'+convert(varchar(4),DATEPART(mm,@d))+'月',
convert(varchar(4),datepart(yyyy,@d))+'年第'+convert(varchar(4),DATEPART(qq,@d))+'季度',convert(varchar(4),DATEPART(yyyy,@d))+'年'
	set @d=@d+1
end
	

delete dimdate where Datetime<'2008-01-01'
declare @d datetime
set @d='2009-04-25'
select @d,convert(char(10),@d,121),convert(varchar(4),datepart(yyyy,@d))+'年第'+convert(varchar(4),DATEPART(ww,@d))+'周',
convert(varchar(4),datepart(yyyy,@d))+'年'+convert(varchar(4),DATEPART(mm,@d))+'月',
convert(varchar(4),datepart(yyyy,@d))+'年第'+convert(varchar(4),DATEPART(qq,@d))+'季度',convert(varchar(4),DATEPART(yyyy,@d))+'年'

exec dbo.CreateDate '2004-01-01','2010-12-31'
