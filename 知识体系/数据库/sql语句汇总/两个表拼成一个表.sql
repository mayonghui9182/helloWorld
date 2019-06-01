declare @t table(a int,b varchar(10),c int)
insert into @t
select 100,'x',32
union all select 100,'y',31
union all select 100,'z',42
union all select 201,'x',33
union all select 201,'y',43
union all select 201,'q',23
union all select 201,'w',23
union all select 333,'x',43
union all select 333,'q',21
union all select 324,'w',12
union all select 324,'z',35

declare @b table(a int,b varchar(10),c int)
insert into @b
select 100,'x',3
union all select 100,'y',12
union all select 100,'z',32
union all select 201,'x',1
union all select 201,'w',10
union all select 333,'x',43
union all select 333,'q',10


select t.a,t.b,(t.c-ISNULL(s.c,0))as cha ,d= case t.a when 100 then 'A-20070820-0001'
		when 201 then 'A-20070820-0001'
		when 333 then 'A-20070820-0003'
		when 324 then 'A-20070820-0004' end  from @t t left join @b s on t.a=s.a and t.b=s.b
group by t.a,t.b,t.c,s.c


 c            d
100     x    29           A-20070820-0001    
100     y    19           A-20070820-0001
100     z    10           A-20070820-0001
201     x    32           A-20070820-0002
201     y    43           A-20070820-0002
201     q    23           A-20070820-0002
201     w    13           A-20070820-0002
333     x    43           A-20070820-0003
333     q    11           A-20070820-0003
324     w    12           A-20070820-0004
324     z    35 

select len('£²£²£²£²')
select len('1111')


declare @s varchar(10)
set @s='2£²'

select substring(@s,1,3)
select datalength(substring(@s,1,3)) equal



where datalength(substring(p_id,9,2))=2 and 107-(substring(p_id,9,2)<17


declare @t varchar(20)
set @t='123456789012345678'
if (select  107-substring(@t,9,2))<17 
print 'a'

 
else print 'b'
end if