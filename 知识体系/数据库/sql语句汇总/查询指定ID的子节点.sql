--测试数据 
CREATE TABLE tb(ID char(3),PID char(3),Name nvarchar(10)) 
INSERT tb SELECT '001',NULL ,'山东省' 
UNION ALL SELECT '002','001','烟台市' 
UNION ALL SELECT '004','002','招远市' 
UNION ALL SELECT '003','001','青岛市' 
UNION ALL SELECT '005',NULL ,'四会市' 
UNION ALL SELECT '006','005','清远市' 
UNION ALL SELECT '007','006','小分市' 
GO 

--查询指定节点及其所有子节点的函数 
CREATE FUNCTION f_Cid(@ID char(3)) 
RETURNS @t_Level TABLE(ID char(3),Level int) 
AS 
BEGIN 
    DECLARE @Level int 
    SET @Level=1 
    INSERT @t_Level SELECT @ID,@Level 
    WHILE @@ROWCOUNT>0 
    BEGIN 
        SET @Level=@Level+1 
        INSERT @t_Level SELECT a.ID,@Level 
        FROM tb a,@t_Level b 
        WHERE a.PID=b.ID 
            AND b.Level=@Level-1 
    END 
    RETURN 
END 
GO 

--调用函数查询002及其所有子节点 
SELECT a.* 
FROM tb a,f_Cid('002') b 
WHERE a.ID=b.ID 
/*--结果 
ID  PID  Name      
------ ------- ---------- 
002  001  烟台市 
004  002  招远市 
--*/ 
