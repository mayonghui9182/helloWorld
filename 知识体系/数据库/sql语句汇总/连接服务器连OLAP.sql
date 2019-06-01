EXEC sp_addlinkedserver 
       @server='OLAP',
       @srvproduct='', 
       @provider='MSOLAP',
       @datasrc='219.232.254.21', -- This is the name of the Analysis Services server.
       @catalog='SMSBank' -- This is the Analysis Services database against which you query.


Select * From OpenQuery(OLAP, '
SELECT NON EMPTY { { { [Dim产品].[产品层次].[产品名称].&[PC保险], [Dim产品].[产品层次].[产品名称].&[毒霸套装], [Dim产品].[产品层次].[产品名称].&[在线杀毒] } * { [Measures].[定制量] } } } ON COLUMNS ,

NON EMPTY { [Dim日期].[年-季度-月-日期层次].[季度].&[2009年第4季度]&[2009年] } ON ROWS  

FROM [SMSBank]')

Select * From OpenRowset('MSOLAP', 'Data Source=.;Initial Catalog=SMSBank;', 'SELECT NON EMPTY { { { [Dim产品].[产品层次].[产品名称].&[PC保险], [Dim产品].[产品层次].[产品名称].&[毒霸套装], [Dim产品].[产品层次].[产品名称].&[在线杀毒] } * { [Measures].[定制量] } } } ON COLUMNS ,

NON EMPTY { [Dim日期].[年-季度-月-日期层次].[季度].&[2009年第4季度]&[2009年] } ON ROWS  

FROM [SMSBank]')