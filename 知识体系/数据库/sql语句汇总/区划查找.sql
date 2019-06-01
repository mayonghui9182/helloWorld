create function fun_getpath(
	@int_id int
) returns varchar(256) as begin
	declare @var_fullpath	varchar(256)
	declare @int_type	int

	select @var_fullpath=RegionalName,@int_id=ParRegionalID,@int_type=Distinction from dbo.Regional where regionalid=@int_id
	while @int_type>1  begin
		select @var_fullpath= RegionalName + ',' + @var_fullpath ,@int_id=ParRegionalID,@int_type=Distinction from Regional where regionalid=@int_id
	end		
	return isnull(@var_fullpath,'')
end