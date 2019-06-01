declare @table_id int
set @table_id=object_id('dbo.[Log_Install(61.129.59.67.dubadg)]')
dbcc showcontig(@table_id)


--第二步:重构表索引
dbcc dbreindex('表名',pk_索引名,100)
--重做第一步，如发现扫描密度/Scan Density还是小于100%则重构表的所有索引
--杨铮：并不一定能达100%。
dbcc dbreindex('dbo.Last_Log_Machine_Bind','',100)
