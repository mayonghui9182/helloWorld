create function fun_getpathName(
	@int_id int
) returns varchar(256) as begin
	declare @var_fullpath	varchar(256)
	declare @int_type	int

	select @var_fullpath=name,@int_id=ParCode,@int_type=Distinction from dbo.Region where code=@int_id
	while @int_type>1  begin
		select @var_fullpath= name + ',' + @var_fullpath ,@int_id=Parcode,@int_type=Distinction from Region where code=@int_id
	end		
	return isnull(@var_fullpath,'')
end

create function fun_getpath(
	@int_id int
) returns varchar(256) as begin
	declare @var_fullpath	varchar(256)
	declare @int_type	int

	select @var_fullpath=CONVERT(varchar,code),@int_id=ParCode,@int_type=Distinction from dbo.Region where code=@int_id
	while @int_type>1  begin
		select @var_fullpath= CONVERT(varchar,code) + ',' + @var_fullpath ,@int_id=Parcode,@int_type=Distinction from Region where code=@int_id
	end		
	return isnull(@var_fullpath,'')
end

select dbo.fun_getpathName(code) from dbo.Region

select dbo.fun_getpath(code) from dbo.Region

insert into dbo.Regional select idx,code,name,parcode,Distinction,isDelete,getdate() as updateTime,dbo.fun_getpathName(code) as Fullname,
dbo.fun_getpath(code) as Fullpath from dbo.Region

select * from dbo.Regional