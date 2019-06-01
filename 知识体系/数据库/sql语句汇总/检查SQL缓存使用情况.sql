SELECT usecounts, cacheobjtype, objtype,size_in_bytes, plan_handle
FROM sys.dm_exec_cached_plans

SELECT  usecounts,objtype,p.size_in_bytes,[sql].[text]
FROM sys.dm_exec_cached_plans p
OUTER APPLY sys.dm_exec_sql_text (p.plan_handle) sql
ORDER BY usecounts desc

dbcc freeproccache


DECLARE @IntVariable int;
DECLARE @SQLString nvarchar(500);
DECLARE @ParmDefinition nvarchar(500);
DECLARE @max_title varchar(30);
SET @IntVariable = 179;
SET @SQLString = N'SELECT @max_titleOUT = max(Title) 
   FROM AdventureWorks.HumanResources.Employee
   WHERE ManagerID = @level';
SET @ParmDefinition = N'@level tinyint, @max_titleOUT varchar(30) OUTPUT';
EXECUTE sp_executesql @SQLString, @ParmDefinition, @level = 125, @max_titleOUT=@max_title OUTPUT;
SELECT @max_title;

select * from dbo.Region where code>22588455



