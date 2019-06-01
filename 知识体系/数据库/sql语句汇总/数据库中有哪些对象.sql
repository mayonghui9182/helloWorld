--数据库中有哪些对象
select name,xtype from sysobjects where type = 'U' or type='V' order by xtype,name

--由于系统表sysobjects保存的都是数据库对象,其中type表示各种对象的类型，具体包括: 
--U = 用户表 
--S = 系统表 
--C = CHECK 约束 
--D = 默认值或 DEFAULT 约束 
--F = FOREIGN KEY 约束 
--L = 日志 
--FN = 标量函数 
--IF = 内嵌表函数 
--P = 存储过程 
--PK = PRIMARY KEY 约束（类型是 K） 
--RF = 复制筛选存储过程 
--TF = 表函数 
--TR = 触发器 
--UQ = UNIQUE 约束（类型是 K） 
--V = 视图 
--X = 扩展存储过程及相关的对象信息。 
