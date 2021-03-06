
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER FUNCTION [dbo].[bigint2ipstr] (@ip bigint)  
RETURNS varchar(50) AS
BEGIN 
	declare @res varchar(50)
	declare @flag int
	if (@ip >= 4294967296)
	begin
		set @flag = @ip
	end 
	set @res=CAST((@ip/16777216) as varchar(50))+'.'+
		CAST(((@ip/65536)%256) as varchar(50))+'.'+
		CAST(((@ip/256)%256) as varchar(50))+'.'+
		CAST((@ip%256) as varchar(50))
	return @res
END
GO

SET ANSI_NULLS OFF
GO
SET QUOTED_IDENTIFIER OFF
GO

