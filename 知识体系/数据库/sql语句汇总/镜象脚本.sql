
　　--【赛迪网-IT技术报道】这篇论坛文章(赛迪网技术社区)主要介绍了SQL Server 2005数据库镜像的配置脚本，详细内容请大家参考下文： 

　　--SQL Server 2005数据库镜像配置脚本: 

　　示例如下： 

　　--在MIR-A上，创建数据库镜像端点 

　　create endpoint DB_MirroringEP 

　　AS tcp (listener_port = 5022) 

　　for database_Mirroring (role = partner,encryption=supported); 

　　go 

　　--在MIR-B上，创建数据库镜像端点，用于伙伴通讯 

　　CREATE ENDPOINT Db_MirroringEP 

　　AS TCP (LISTENER_PORT = 5022) 

　　FOR DATABASE_MIRRORING (ROLE = PARTNER, ENCRYPTION = SUPPORTED); 

　　GO 

　　ALTER ENDPOINT Db_MirroringEP STATE = STARTED 

　　GO 

　　--在MIR-W上，创建数据库镜像端点，用于见证通讯 

　　CREATE ENDPOINT Db_MirroringEP 

　　AS TCP (LISTENER_PORT = 5022) 

　　FOR DATABASE_MIRRORING (ROLE = WITNESS, ENCRYPTION = SUPPORTED); 

　　GO 

　　ALTER ENDPOINT Db_MirroringEP STATE = STARTED 

　　GO 

　　--在MIR-A，MIR-B，MIR-W上，检查端点配置 

　　SELECT * FROM sys.database_mirroring_endpoints 

　　GO 

　　--在MIR-A，MIR-B，MIR-W上，配置数据库镜像安全性，somodesql.com为自己的域名 

　　use master 

　　go 

　　grant connect on endpoint::"DB_MirroringEP" to "SOMODESQL\sqladmin" 

　　go 

　　--在MIR-A上，对AdventureWorks数据库做完全备份 

　　BACKUP DATABASE AdventureWorks TO DISK = 'C:\AdventureWorks.bak' 

　　GO 

　　--在MIR-B上恢复AdventureWorks数据库。 

　　--通过安全方法，将 C:\AdventureWorks.bak 复制到 MIR-B。 

　　--在 MIR-B 的镜像服务器实例上还原数据库： 

　　RESTORE DATABASE AdventureWorks 

　　FROM DISK = 'C:\AdventureWorks.bak' 

　　WITH NORECOVERY 

　　GO 

　　--启动数据库镜像，注意顺序，需要在首先在镜像服务器上配置伙伴 

　　--在MIR-B上，指定伙伴端点,somodesql.com为自己的域名 

　　alter database AdventureWorks 

　　set partner = N'TCP://MIR-A.somodesql.com:5022' 

　　GO 

　　--在MIR-A上，指定伙伴端点 

　　alter database AdventureWorks 

　　set partner = N'TCP://MIR-B.somodesql.com:5022' 

　　GO 

　　--在MIR-A上，指定见证服务器端点 

　　ALTER DATABASE AdventureWorks 

　　SET WITNESS = N'TCP://MIR-W.somodesql.com:5022' 

　　GO 

　　--配置数据库镜像事务安全级别 

　　ALTER DATABASE AdventureWorks SET SAFETY FULL 

　　GO 

　　--=================查看数据库镜像的配置状态================= 

　　-- 1.)通过Management studio 对象资源管理器，查看主体数据库、镜像数据库状态 

　　-- 2.)通过Management studio 对象资源管理器中的数据库属性查看状态 

　　-- 3.)通过系统目录视图查看数据库镜像配置情况 

　　use master 

　　go 

　　SELECT * FROM sys.database_mirroring_endpoints 

　　SELECT * FROM sys.database_mirroring WHERE database_id = 

　　(SELECT database_id FROM sys.databases WHERE name = 'AdventureWorks') 

	

	//////////////////////////////////////////////////////////////////////////////////////////////
	--1 在镜像服务器上,创建数据库快照
	create database db_temp
	on (name=mfw_data,filename='C:\temp_file.ss')
	as snapshot of mfw

	--2 访问镜像快照据库
	use db_temp
	select * from SeedMachine

	--3删除不要用的快照据库
	Use master
	Go
	Drop database db_temp
	go
	--*********************************************************************
	--使镜像数据库强制服务
	ALTER DATABASE <database_name> SET PARTNER FORCE_SERVICE_ALLOW_DATA_LOSS
	--手动故障转移
	ALTER DATABASE <database_name> SET PARTNER FAILOVER
	--*********************************************************************
	--设置数据库为完全备份
	RESTORE DATABASE mfw WITH RECOVERY
	--*********************************************************************
	--启动trace方法
	--例：启动trace 1400
	--方法1、
	dbcc traceon (1400) --局部
	dbcc traceoff (1400)
	dbcc traceon (1400, -1) --全局
	dbcc traceoff (1400, -1)
	--方法2、
	--在服务器设置启动参数，在;后追加-T 1400
	dbcc tracestatus --查看trace

	--修改超时查询超时（修改仅限于高安全模式）
	ALTER DATABASE <database> SET PARTNER TIMEOUT <integer> 
	select mirroring_connection_timeout from sys.database_mirroring