--查看表的索引碎片
DECLARE @db_id SMALLINT;
DECLARE @object_id INT;

SET @db_id = DB_ID(N'zuoTest');
SET @object_id = OBJECT_ID(N'zuoTest.dbo.Last_Account_Bind_verify_Info');

IF @db_id IS NULL
BEGIN;
    PRINT N'Invalid database';
END;
ELSE IF @object_id IS NULL
BEGIN;
    PRINT N'Invalid object';
END;
ELSE
BEGIN;
    SELECT database_id as 数据库ID,
	object_id as [索引表/视图ID],
	index_id as 索引ID,
	partition_number as 分区号,
	index_type_desc as 索引类型,
	avg_fragmentation_in_percent as 索引逻辑碎片,
	fragment_count as 叶级别碎片数,
	avg_fragment_size_in_pages as 碎片的平均页数,
	avg_page_space_used_in_percent as 页使用存储空间平均百分比,
	record_count as 总记录数
	FROM sys.dm_db_index_physical_stats(@db_id, @object_id, NULL, NULL , 'LIMITED');
END;
GO

--强制使用索引
select top 100 * from PartitionPaySys.dbo.FactPayRecord with (index=IX_active_time) 
where Active_Time<'2009-02-02' and active_time>'2009-02-01'

select top 1000 * from alluserwithallfields a with(index=ip) left join iptable b with(index=IX_IP) on 
a.install_ip>i_start_ip and install_ip<i_end_ip and install_time<='2008-1-1' 

