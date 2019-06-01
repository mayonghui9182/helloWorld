use master
IF  EXISTS (SELECT name FROM sys.databases WHERE name = N'Data Partition DB3')
DROP DATABASE [Data Partition DB3]
GO
CREATE DATABASE [Data Partition DB3]
ON PRIMARY
(NAME='Data Partition DB Primary FG3',
FILENAME=
'C:\Data2\Primary\Data Partition DB Primary FG3.mdf',
SIZE=5,
MAXSIZE=500,
FILEGROWTH=1 ),
FILEGROUP [Data Partition DB3 FG1]
(NAME = 'Data Partition DB3 FG1',
FILENAME =
'C:\Data2\FG1\Data Partition DB3 FG1.ndf',
SIZE = 5MB,
MAXSIZE=500,
FILEGROWTH=1 ),
FILEGROUP [Data Partition DB3 FG2]
(NAME = 'Data Partition DB3 FG2',
FILENAME =
'C:\Data2\FG2\Data Partition DB3 FG2.ndf',
SIZE = 5MB,
MAXSIZE=500,
FILEGROWTH=1 ),
FILEGROUP [Data Partition DB3 FG3]
(NAME = 'Data Partition DB3 FG3',
FILENAME =
'C:\Data2\FG3\Data Partition DB3 FG3.ndf',
SIZE = 5MB,
MAXSIZE=500,
FILEGROWTH=1 ),
FILEGROUP [Data Partition DB3 FG4]
(NAME = 'Data Partition DB3 FG4',
FILENAME =
'C:\Data2\FG4\Data Partition DB3 FG4.ndf',
SIZE = 5MB,
MAXSIZE=500,
FILEGROWTH=1 )

--然后建立一个数据表：
USE [Data Partition DB3]
go
CREATE TABLE MyTable
(ID INT NOT NULL,
Date DATETIME,
Cost money ) on [primary]

--并建立一个聚集索引
USE [Data Partition DB3]
go
CREATE UNIQUE CLUSTERED INDEX MyTable_IXC
ON MyTable(ID) on [PRIMARY]

--接下来往表里增加数据
USE [Data Partition DB3]
go
declare @count int
set @count =-25
while @count <=100
begin
insert into MyTable select @count,getdate(),100.00
set @count=@count+1
end
set @count =101
while @count <=200
begin
insert into MyTable select @count,getdate(),200.00
set @count=@count+1
end
set @count =201
while @count <=300
begin
insert into MyTable select @count,getdate(),300.00
set @count=@count+1
end
set @count =301
while @count <=400
begin
insert into MyTable select @count,getdate(),400.00
set @count=@count+1
end
set @count =401
while @count <=800
begin
insert into MyTable select @count,getdate(),500.00
set @count=@count+1
end

--此时查询一下，可以看到数据都在一个表里

select * from sys.partitions where object_name(object_id)='MyTable'
 
--我们再建立表分区函数

use [Data Partition DB3]
GO
CREATE PARTITION FUNCTION [Data Partition Range](int)
AS RANGE LEFT FOR VALUES (100,200,300)

--这里表明分区的原则是四个分区，从负数到100，101-200，201-300，大于300
--当然，如果用right for values的话，就是从负数到99，100到199，200-299，和大于300
 
--最后，把表分区函数应用到文件组里

USE [Data Partition DB3]
go
CREATE PARTITION SCHEME [Data Partition Scheme]
AS PARTITION [Data Partition Range]
TO ([Data Partition DB3 FG1], [Data Partition DB3 FG2], [Data Partition DB3 FG3],[Data Partition DB3 FG4]);

--把原来建立好的表，移动到这个表分区里
Drop index MyTable_IXC on MyTable with (Move To [Data Partition Scheme] (ID) )
 
--最后看一看

select * from sys.partitions where object_name(object_id)='MyTable'
 
--可以看到，原来的表的数据被正确分拆到四个文件组里去了，实现了表分区

select * from dbo.MyTable

--检查某个值在哪个分区中，用分区函数
select $partition.[Data Partition Range](800)

http://www.cndw.com/tech/data/2006051268831.asp?jdfwkey=42585
