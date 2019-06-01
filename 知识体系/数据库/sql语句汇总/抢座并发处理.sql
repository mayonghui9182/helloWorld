--@id Ö÷¼ü
--@Num Ô¶³ÌĞ­ÖúºÅ
CREATE PROCEDURE GetRemoteNum
as
	begin tran
	declare @id int,@Num int
	SELECT top 1 @id=idx,@Num=num FROM ºÅÂë¿â where State=0
	update ºÅÂë¿â with(rowlock) set State=1 where idx=@id
	--WAITFOR DELAY '0:00:10';
	select @Num as Num
	commit tran




