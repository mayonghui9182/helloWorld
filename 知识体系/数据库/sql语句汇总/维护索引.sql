SQL Server 2005:

　　--1. 联机重新组织：

　　ALTER INDEX [index_name] ON [table_name]

　　REORGANIZE;

　　--2. 联机重建：

　　ALTER INDEX [index_name] ON [table_name]

　　REBUILD WITH (FILLFACTOR = 85, SORT_IN_TEMPDB = OFF,

　　STATISTICS_NORECOMPUTE = ON,ONLINE = ON);

　　
	--脱机维护
　　--SQL Server 2005：

	ALTER INDEX [indexname] ON [table_name] REBUILD;

　　CREATE INDEX WITH DROP_EXISTING


