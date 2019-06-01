--使用BCP工具到出数据，信任连接
EXEC master..xp_cmdshell "BCP BJSJK.dbo.DataSynLog out c:\currency1.txt -T -w"

--  使用SQL查询的数据导处  F从第10行开始   L 到第13行结束
EXEC master..xp_cmdshell 'BCP "SELECT TOP 20 * FROM AdventureWorks.sales.currency" queryout c:\currency2.txt -F 10 -L 13 -c -T'

EXEC master..xp_cmdshell 'BCP AdventureWorks.sales.currency format nul -f c:\currency_format2.fmt -x -c -T' 

-- 以特定的字符做为列分割，和行分割
EXEC master..xp_cmdshell "BCP AdventureWorks.sales.currency out c:\currency1.txt -c -T -t , -r ; "

--导入
EXEC master..xp_cmdshell 'BCP BJSJK.dbo.DataSynLog in c:\currency1.txt -w -T' 

/*
-f format_file
format_file表示格式文件名。这个选项依赖于上述的动作，如果使用的是in或out，format_file表示已经存在的格式文件，如果使用的是format则表示是要生成的格式文件。 

-x 
这个选项要和-f format_file配合使用，以便生成xml格式的格式文件。

-F first_row 
指定从被导出表的哪一行导出，或从被导入文件的哪一行导入。

-L last_row 
指定被导出表要导到哪一行结束，或从被导入文件导数据时，导到哪一行结束。

-c 
使用char类型做为存储类型，没有前缀且以"\t"做为字段分割符，以"\n"做为行分割符。 

-w
和-c类似，只是当使用Unicode字符集拷贝数据时使用，且以nchar做为存储类型。

-t field_term 
指定字符分割符，默认是"\t"。

-r row_term 
指定行分割符，默认是"\n"。

-S server_name[ \instance_name] 
指定要连接的SQL Server服务器的实例，如果未指定此选项，BCP连接本机的SQL Server默认实例。如果要连接某台机器上的默认实例，只需要指定机器名即可。

-U login_id 
指定连接SQL Sever的用户名。

-P password 
指定连接SQL Server的用户名密码。 

-T
指定BCP使用信任连接登录SQL Server。如果未指定-T，必须指定-U和-P。 

-k
指定空列使用null值插入，而不是这列的默认值。


*/

bcp "select * from PartitionPaySys.dbo.AssDubaUserInfo where account_id>=60000000 and account_id<70000000" queryout E:\sug\FTP_AssDubaUserInfo\dayDuBaUserInfo5.txt -c -T

bcp "select * from PartitionPaySys.dbo.AssDubaUserInfo where account_id>=70000000 and account_id<70000000" queryout E:\sug\FTP_AssDubaUserInfo\dayDuBaUserInfo6.txt -c -T

BCP CustomerServiceCenter.dbo.CSCustomersInfo in D:\zwm\ftp\dayDuBaUserInfo2.txt -c -T

bcp "select account_id,passport,expiretime,maxenddate from UserInfo.dbo.CSCustomersInfo" queryout c:\1k.txt -c -T -F 1 -L100000 -b 5000 & bcp "select account_id,passport,expiretime,maxenddate from UserInfo.dbo.CSCustomersInfo" queryout c:\2k.txt -c -T -F 100000 -L 200000 -b 5000
