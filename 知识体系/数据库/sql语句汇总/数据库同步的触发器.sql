--数据库同步的触发器
CREATE TRIGGER  T_INSERT_test1
   ON test_1
for INSERT
AS 
BEGIN
	insert into DB2.dbo.DB2Table select a.id,a.name,a.age,a.address from db1.dbo.test_1 as a INNER JOIN Inserted as i on a.id=i.id
END

CREATE TRIGGER  T_INSERT_test2
   ON test_1
for delete
AS 
BEGIN
	delete a from DB2.dbo.DB2Table as a INNER JOIN deleted as i on a.id=i.id 
END

CREATE TRIGGER  T_INSERT_test3
   ON test_1
for update
AS 
BEGIN
	delete a from DB2.dbo.DB2Table as a INNER JOIN deleted as i on a.id=i.id 
	insert into DB2.dbo.DB2Table select a.id,a.name,a.age,a.address from db1.dbo.test_1 as a INNER JOIN deleted as i on a.id=i.id
END