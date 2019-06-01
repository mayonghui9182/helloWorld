SELECT 
    [Name] AS 报表名称, 
    [Path] AS 报表路径,
	[Parameter] as 报表参数,
    CASE 
        [Type] 
        WHEN 2 THEN N'报表' 
        WHEN 6 THEN N'报表模型' 
    END AS 类型,
    CAST(
        CAST([content] AS VARBINARY(max)) AS XML
        ) AS 报表定义
FROM ReportServer.dbo.[Catalog]
WHERE 
    [Type] IN (2,6)
ORDER BY 
    [Path], [Type], [Name]

select * FROM ReportServer.dbo.[Catalog]
