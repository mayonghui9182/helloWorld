SQL 视图创建索引2008-05-29 10:32create view dbo.View_Table 
with schemabinding 
as 
select ................ 
Go  

----with schemabinding   是必须的，否则会出现该视图未绑定到架构

--索引视图受限于以下的附加限制： 

--索引的创建者必须拥有表。所有表、视图和索引必须在同一数据库中创建。 


--定义索引视图的 SELECT 语句不得包含视图、行集函数、行内函数或派生表。同一物理表在该语句中只能出现一次。 


--在任何联接表中，均不允许进行 OUTER JOIN 操作。 


--搜索条件中不允许使用子查询或者 CONTAINS 或 FREETEXT 谓词。 


--如果视图定义包含 GROUP BY 子句，则视图的 SELECT 列表中必须包含所有分组依据列及 COUNT_BIG(*) 表达式。此外，CREATE UNIQUE CLUSTERED INDEX 子句中必须只包含这些列。
 
