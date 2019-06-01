--当前SQL连接数

--select connectnum=count(distinct net_address)-1 from master..sysprocesses


SELECT hostname,[program_name] FROM 
[Master].[dbo].[SYSPROCESSES] WHERE [DBID] 
IN 
(
  SELECT 
   [DBID]
  FROM 
   [Master].[dbo].[SYSDATABASES] 
  WHERE 
   NAME='ShanXiDB'
)
