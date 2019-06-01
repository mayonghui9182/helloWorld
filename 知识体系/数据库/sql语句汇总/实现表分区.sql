ALTER DATABASE test ADD FILEGROUP [Num20W]
GO
ALTER DATABASE test
ADD FILE
(NAME = N'Num20W',
FILENAME = N'C:\data\Num20W.ndf',
SIZE = 5MB,
MAXSIZE = 100MB,
FILEGROWTH = 5MB)
TO FILEGROUP [Num20W]
GO

ALTER DATABASE test ADD FILEGROUP [Num40W]
GO
ALTER DATABASE test
ADD FILE
(NAME = N'Num40W',
FILENAME = N'C:\data\Num40W.ndf',
SIZE = 5MB,
MAXSIZE = 100MB,
FILEGROWTH = 5MB)
TO FILEGROUP [Num40W]
GO

ALTER DATABASE test ADD FILEGROUP [Num60W]
GO
ALTER DATABASE test
ADD FILE
(NAME = N'Num60W',
FILENAME = N'C:\data\Num60W.ndf',
SIZE = 5MB,
MAXSIZE = 100MB,
FILEGROWTH = 5MB)
TO FILEGROUP [Num60W]
GO

CREATE PARTITION FUNCTION [Data Partition Range](int)
AS RANGE LEFT FOR VALUES (200000,400000)
GO

CREATE PARTITION SCHEME [Data Partition Scheme]
AS PARTITION [Data Partition Range]
TO ([Num20W], [Num40W], [Num60W]);
GO

CREATE TABLE [dbo].[Table_1](
	[idx] [int] NOT NULL,
	[num] [int] NULL)
 ON [Data Partition Scheme](idx)
GO

declare @count int
set @count =1
while @count <=600000
begin
insert into dbo.Table_1 select @count,@count
set @count=@count+1
end


CREATE UNIQUE CLUSTERED INDEX Table_IXC
ON Table_1(num) on [PRIMARY]
GO

--ALTER TABLE Table_2 
--SWITCH PARTITION 1 TO Table_1 PARTITION 1
--GO

select * from sys.partitions where object_name(object_id)='Table_1'
