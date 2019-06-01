USE AdventureWorksDW
--为Dimtime表增加中文名称列
ALTER TABLE Dimtime ADD ChineseMonthName NVARCHAR(50)
GO
--对应于英文的月名称，改为中文的月名称
With ModifyTime(English,Chinese) AS
(
SELECT 'January',N'一月'
UNION
SELECT 'February',N'二月'
UNION
SELECT 'March',N'三月'
UNION
SELECT 'April',N'四月'
UNION
SELECT 'May',N'五月'
UNION
SELECT 'June',N'六月'
UNION
SELECT 'July',N'七月'
UNION
SELECT 'August',N'八月'
UNION
SELECT 'September',N'九月'
UNION
SELECT 'October',N'十月'
UNION
SELECT 'November',N'十一月'
UNION
SELECT 'December',N'十二月'
)
--
--
UPDATE DimTime SET ChineseMonthName=Chinese
FROM DimTime e JOIN ModifyTime t ON e.EnglishMonthName=t.English
GO
