exec msdb..sysmail_add_profile_sp @profile_name = 'dba_profile',
@description  = 'dba mail profile',@profile_id   = null


exec msdb..sysmail_add_profileaccount_sp  @profile_name= 'dba_profile' -- profile 名称 
,@account_name= 'test'     -- account 名称
 ,@sequence_number = 1-- account 在 profile 中顺序   


select * from dbo.abc

declare @b nvarchar(100)
set @b='老婆还有'+convert(varchar(2),datediff(d,getdate(),'2009-08-18'))+'天，你就是我的'
exec msdb..sp_send_dbmail @profile_name =  'dba_profile'-- profile 名称
,@recipients   =  'jackfor001@126.com'-- 收件人邮箱
,@subject=@b -- 邮件标题
,@body=  '老婆准备好嫁给我吧！！！！我会好好爱你的！！！！'-- 邮件内容
,@body_format='TEXT'-- 邮件格式   


EXEC msdb.dbo.sp_send_dbmail
    @profile_name = 'dba_profile',
    @recipients = 'zuowenming@kingsoft.com',
    @query = 'SELECT * FROM AdventureWorks.Production.WorkOrder
                  WHERE DueDate > ''2004-04-30''
                  AND  DATEDIFF(dd, ''2004-04-30'', DueDate) < 2' ,
    @subject = '老婆还有'+convert(varchar(2),datediff(d,getdate(),'2009-08-18'+'天';



SELECT * FROM AdventureWorks.Production.WorkOrder
                  WHERE DueDate > '2004-04-30'
                  AND  DATEDIFF(dd, '2004-04-30', DueDate) < 2