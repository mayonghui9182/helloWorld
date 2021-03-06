EXEC sp_configure 'show advanced options', 1
RECONFIGURE
GO

EXEC sp_configure 'Ole Automation Procedures', 1
RECONFIGURE


create proc SendSmS @phone varchar(500),@content varchar(5000)
as
begin
declare @err int,@src varchar(255),@desc varchar(255)
declare @obj int,@urlstr nvarchar(4000),@function varchar(4)
set @urlstr='http://218.206.94.138/passportserv/default.aspx?command=DubaTongji&phone='+@phone+'&content='+@content
-- 创建 xmlhttp 对象
print @urlstr
exec @err=sp_oacreate 'MICROSOFT.XMLHTTP',@obj out
if @err<>0 goto lberr

-- open 
exec @err=sp_oamethod @obj,'open',null, 'POST', @urlstr, 0
if @err<>0 goto lberr

-- send
exec @err=sp_oamethod @obj,'send',null,''
if @err<>0 goto lberr

-- 释放 xmlhttp 对象
exec @err=sp_oadestroy @obj
return

lberr:
	exec sp_oageterrorinfo 0,@src out,@desc out
	select cast(@err as varbinary(4)) as 错误号
		,@src as 错误源,@desc as 错误描述
end

