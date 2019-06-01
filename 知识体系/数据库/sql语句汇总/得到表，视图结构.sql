--表结构
declare @table_name as varchar(max)
set @table_name = 'DDimFlowOHMessage' 
select sys.columns.name, sys.types.name, sys.columns.max_length, case sys.columns.is_nullable when 1 then '是' else '否' end is_nullable, 
case (select count(*) from sys.identity_columns where sys.identity_columns.object_id = sys.columns.object_id and sys.columns.column_id = sys.identity_columns.column_id) when 1 then '是' else '否' end as is_identity ,
(select value from sys.extended_properties where sys.extended_properties.major_id = sys.columns.object_id and sys.extended_properties.minor_id = sys.columns.column_id) as description
from sys.columns, sys.tables, sys.types where sys.columns.object_id = sys.tables.object_id and sys.columns.system_type_id=sys.types.system_type_id and sys.tables.name=@table_name order by sys.columns.column_id


--视图结构
declare @table_name as varchar(max)
set @table_name = 'DimFlowOHMessage' 
select sys.columns.name, sys.types.name, sys.columns.max_length, case sys.columns.is_nullable when 1 then '是' else '否' end is_nullable, 
case (select count(*) from sys.identity_columns where sys.identity_columns.object_id = sys.columns.object_id and sys.columns.column_id = sys.identity_columns.column_id) when 1 then '是' else '否' end as is_identity ,
(select value from sys.extended_properties where sys.extended_properties.major_id = sys.columns.object_id and sys.extended_properties.minor_id = sys.columns.column_id) as description
from sys.columns, sys.views, sys.types where sys.columns.object_id = sys.views.object_id and sys.columns.system_type_id=sys.types.system_type_id and sys.views.name=@table_name and sys.types.name<>'sysname'
order by sys.columns.column_id
