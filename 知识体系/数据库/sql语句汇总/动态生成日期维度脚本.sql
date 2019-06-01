CREATE TABLE [dbo].[dim_date](
	[idx] [uniqueidentifier] NOT NULL,
	[date_id] [datetime] NULL,
	[dayNum] [int] NULL,
	[monthNum] [int] NULL,
	[weekNum] [int] NULL,
	[quarterNum] [int] NULL,
	[yearNum] [int] NULL,
 CONSTRAINT [PK_dim_date] PRIMARY KEY CLUSTERED 
(
	[idx] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
go

CREATE PROCEDURE getDimDate
	@begin_date varchar(10),
	@end_date varchar(10)
as
begin
	declare @dayNum int,@yearNum int,@monthNum int,@weekNum int,@weekdayNum int,@quarterNum int,@i int,@tempdate datetime
	set @i=0
	while @i<=(select datediff(day,convert(smalldatetime,@begin_date,101),convert(smalldatetime,@end_date,101)))
	begin
		set @tempdate=dateadd(day,@i,convert(smalldatetime,@begin_date,101))

		set @monthNum=datepart(month,@tempdate)

		set @weekNum=datepart(week,@tempdate)

		--set @weekdayNum=datepart(weekday,@tempdate)

		set @quarterNum=datepart(quarter,@tempdate)

		set @yearNum=datepart(year,@tempdate)
		
		set @dayNum=datepart(day,@tempdate)
		
		insert into dbo.dim_DATE(idx,date_id,dayNum,monthNum,weekNum,quarterNum,yearNum)values
		(newid(),@tempdate,@dayNum,@monthNum,@weekNum,@quarterNum,@yearNum)
		set @i=@i+1
	end	
end
go
exec dbo.getDimDate '2008-01-01','2028-12-31'
go

select * from dbo.dim_date

--delete dim_DATE

declare @i int,@temp datetime
set @i=0
while @i<30
begin
	set @temp=dateadd(day,@i,getdate())
	insert into dbo.Table_1(datenum,num)values(getdate(),@i)
	set @i=@i+1
end
go