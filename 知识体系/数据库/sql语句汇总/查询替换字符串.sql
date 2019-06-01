create table tbl(t varchar(4000))
insert into tbl
select 
'<TAble>
<tr><td><a herf="http://localhost/a.html#8">连接</a></td></tr>
<tr><td><a herf="http://192.168.0.4/a.html#8">连接</a></td></tr>
<tr><td><a herf="http:/192.168.0.3/a.html#8">连接</a></td></tr>
<tr><td><a herf="http://192.168.0.11/a.html#8">连接</a></td></tr>
</table>'

declare @s int,@j int
set @j=0
select @s=(len(t)-len(replace(t,'http','')))/4 from tbl
while @j<@s
begin
update tbl set t= replace(t,substring(t,charindex('http:/',t),charindex('/',t,charindex('http:/',t)+10)-charindex('http:/',t)+1),'') from tbl
set @j=@j+1
end

select * from tbl
drop table tbl

