
CREATE TABLE [dbo].[Ta](
	[idx] [int] IDENTITY(1,1) NOT NULL,
	[da] [datetime] NULL,
	[account] [int] NULL,


insert into ta(da,account)values('2009-01-03',2)

select *
from ta a
where exists 
(select 1 from ta b where a.account=b.account and a.da<b.da)

select * from ta

结果
1	2009-01-01 00:00:00.000	1
2	2009-01-02 00:00:00.000	1
4	2009-01-01 00:00:00.000	2


数据
1	2009-01-01 00:00:00.000	1
2	2009-01-02 00:00:00.000	1
3	2009-01-03 00:00:00.000	1
4	2009-01-01 00:00:00.000	2
5	2009-01-03 00:00:00.000	2