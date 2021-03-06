SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO


CREATE  PROCEDURE MyInsertSP
(
		@strXML varchar(1000) 
)
AS
	Declare @intPointer int
	exec sp_xml_preparedocument @intPointer output, @strXML
	
	Insert into publishers
	Select 	* from OpenXml(@intPointer,'/root/publisher',2)
	With 	(pub_id char(4), pub_name varchar(40), city varchar(20), state char(2), country varchar(20))

	exec sp_xml_removedocument @intPointer

RETURN 



GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

