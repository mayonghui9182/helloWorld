CREATE FUNCTION f_Convert( 
	@str NVARCHAR(4000), --要转换的字符串 
	@flag bit           --转换标志,0转换成半角,1转换成全角 
)RETURNS nvarchar(4000) 
AS 
	BEGIN 
	DECLARE @pat nvarchar(8),@step int,@i int,@spc   int 
	IF @flag=0
		SELECT @pat=N'%[！-～]%',@step=-65248, 
		@str=REPLACE(@str,N'　',N' ') 
	ELSE	
		SELECT @pat=N'%[!-~]%',@step=65248, 
		@str=REPLACE(@str,N'　',N'　') 
	SET @i=PATINDEX(@pat COLLATE LATIN1_GENERAL_BIN,@str) 
	WHILE @i>0 
		SELECT	@str=REPLACE(@str,SUBSTRING(@str,@i,1),NCHAR(UNICODE(SUBSTRING(@str,@i,1))+@step))
		,@i=PATINDEX(@pat COLLATE LATIN1_GENERAL_BIN,@str) 
	RETURN(@str) 
END 
GO