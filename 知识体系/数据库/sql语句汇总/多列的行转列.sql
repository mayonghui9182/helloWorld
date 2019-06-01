create table t(name varchar(03),subject varchar(10),mark int)
insert into t
select 'A','語文',80 union all
select 'A','數學',80 union all
select 'A','外語',80 union all
select 'B','數學',80 union all
select 'B','外語',80 union all
select 'C','語文',78

--在使用动态变量的时候 不能使用动态的表
--答案一
declare @sql varchar(8000),@count int
select @count=count(distinct subject) from T
set @sql='select name'
select @sql=@sql+',sum(case when subject='''+subject+''' then mark else 0 end) as '+subject
from T
group by subject

select @sql=@sql+',sum(isnull(mark,0))/'+rtrim(@count)+' as [avg]  from t group by name'
exec(@sql)
/*
name 外語          語文          數學          avg         
---- ----------- ----------- ----------- ----------- 
A    80          80          80          80
B    80          0           80          53
C    0           78          0           26
*/
drop table t
--答案二
create table tb(name varchar(10),subject varchar(10),mark int)

insert into tb values('A',         '语文',      80)
insert into tb values('A',         '数学',      80)
insert into tb values('A',         '外语',      80 )
insert into tb values('B',         '数学',      80)
insert into tb values('B',         '外语',      80)
insert into tb values('C',         '语文',      78)

select name ,
  max(case subject when '语文' then mark else 0 end) '语文',
  max(case subject when '数学' then mark else 0 end) '数学',
  max(case subject when '外语' then mark else 0 end) '外语',
  sum(mark)/(select max(cnt) cnt from (select name , count(*) cnt from tb group by name) t) [avg]
from tb
group by name

declare @sql varchar(8000)
set @sql = 'select Name as ' + '姓名'
select @sql = @sql + ' , sum(case Subject when ''' + Subject + ''' then mark else 0 end) [' + Subject + ']'
from (select distinct Subject from tb) as a
set @sql = @sql + ' ,sum(mark)/(select max(cnt) cnt from (select name , count(*) cnt from tb group by name) t) [avg] from tb group by name'
exec(@sql) 

drop table tb



