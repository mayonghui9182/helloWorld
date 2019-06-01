
--临时表：#temp 

--内存表=表变量：@temp 唯一不足的地方就是过程结束，表变量就释放了 

--sql server 里面没有常驻内存的表变量，就是说 没有真正意义上的内存表 

declare  @DataSynLog table (idx uniqueidentifier,
	tabelname nvarchar(50),
	tabel_idx nvarchar(50),
	updata_time smalldatetime,
	isdelete int,
	level int,
	Code int,
	dowork int)  

insert into @DataSynLog select * from datasynlog

select * from @DataSynLog