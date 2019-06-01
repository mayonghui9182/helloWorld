truncate table MonSMSSubScribe
truncate table MonBankSubScribe
truncate table MonSSOSubScribe

--短信表中的数据
insert into dbo.MonSMSSubScribe
Select * From OpenRowset('MSOLAP', 'Data Source=.;Initial Catalog=TimeSMSBank;', 
'SELECT { [Measures].[短信定制量], [Measures].[实时短信定制量] } ON COLUMNS ,

{ { { [定制小时].[Dim小时].[All].CHILDREN } * { [Dim短信平台].[产品].&[PC保险], [Dim短信平台].[产品].&[毒霸套装], [Dim短信平台].[产品].&[在线杀毒] } } } ON ROWS  

FROM [TimeSMSBank]
')

--银行卡
insert into dbo.MonBankSubScribe
Select * From OpenRowset('MSOLAP', 'Data Source=.;Initial Catalog=TimeSMSBank;', 
'SELECT { [Measures].[定单次数], [Measures].[实时定单次数] } ON COLUMNS ,
{ { { [定制小时].[Dim小时].[All].CHILDREN } * { [Dim支付类型].[支付类型层次].[支付大类型].&[银行卡], 
[Dim支付类型].[支付类型层次].[支付类型].&[嗖付支付]&[银行卡], [Dim支付类型].[支付类型层次].[支付类型].&[网银]&[银行卡], 
[Dim支付类型].[支付类型层次].[支付类型].&[支付宝]&[银行卡], 
[Dim支付类型].[支付类型层次].[支付类型].&[支付宝网关]&[银行卡] } } } ON ROWS  
FROM [TimeSMSBank]')

--SSO
insert into MonSSOSubScribe
Select * From OpenRowset('MSOLAP', 'Data Source=.;Initial Catalog=TimeSMSBank;', 'SELECT { [Measures].[实时SSO输入量], [Measures].[实时SSO定制量], [Measures].[SSO输入量], [Measures].[SSO定制量] } ON COLUMNS ,
{ { { [定制小时].[Dim小时].[All].CHILDREN } * { [Dim支付页面].[Puidpsid].&[12_9], [Dim支付页面].[Puidpsid].&[12_11], [Dim支付页面].[Puidpsid].&[12_1], [Dim支付页面].[Puidpsid].&[10_5], [Dim支付页面].[Puidpsid].&[11_1] } } } ON ROWS  
FROM [TimeSMSBank]')

alter proc MonTime @tablename varchar(20)
as
begin
	declare @hour int,@a int,@b int,@Ta int,@Tb int,@type varchar(50),@Ba int,@Bb int,@result varchar(100)
	set @result=''
	if @tablename='MonSMSSubScribe'
	begin
		set @hour=DATEPART(hh,getdate())
		declare mycursor cursor for 
		select a.type,a.sms,a.smstime,b.sms BSms from MonSMSSubScribe a inner join (select type,Sms,SmsTime from MonSMSSubScribe where HourNum='7') b on a.Type=b.type 
		where a.HourNum=convert(varchar(2),@hour)
		open mycursor
		fetch  next from mycursor into @type,@a,@Ta,@Ba
			while (@@fetch_status=0)
			begin 	
				if (abs((@a-isnull(@Ta,0))/@a)>0.1) and (abs(@a-isnull(@Ta,0))>@Ba*0.1)
				begin
					set @result=@result+';短信定制数据'+convert(varchar(2),@hour)+'点,具体详情请查看邮件'
					break
				end
				fetch  next from mycursor into @type,@a,@Ta,@Ba
			end
		close mycursor 
		deallocate mycursor
	end
	if @tablename='MonBankSubScribe'
	begin
		set @hour=DATEPART(hh,getdate())
		declare mycursor cursor for 
			select a.type,a.Num,a.NumTime,b.Num BNum from MonBankSubScribe a inner join 
			(select type,Num from MonBankSubScribe where HourNum='7') b on a.Type=b.type 
			where a.HourNum=convert(varchar(2),@hour)
		open mycursor
		fetch  next from mycursor into @type,@a,@Ta,@Ba
			while (@@fetch_status=0)
			begin 	
				if (abs((@a-isnull(@Ta,0))/@a)>0.1) and (abs(@a-isnull(@Ta,0))>@Ba*0.1)
				begin
					set @result=@result+';银行卡数据'+convert(varchar(2),@hour)+'点,具体详情请查看邮件'
					break
				end
				fetch  next from mycursor into @type,@a,@Ta,@Ba
			end
		close mycursor 
		deallocate mycursor
	end
	if @tablename='MonSSOSubScribe'
	begin
		set @hour=DATEPART(hh,getdate())
		declare mycursor cursor for 
			select a.type,a.Input,a.[Order],a.InputTime,a.OrderTime,b.Input,b.[Order] from MonSSOSubScribe a inner join 
			(select type,Input,[Order] from MonSSOSubScribe where HourNum='7') b on a.Type=b.type 
			where a.HourNum=convert(varchar(2),@hour)
		open mycursor
		fetch  next from mycursor into @type,@a,@b,@Ta,@Tb,@Ba,@Bb
			while (@@fetch_status=0)
			begin 	
				if (abs((@a-isnull(@Ta,0))/@a)>0.1) and (abs(@a-isnull(@Ta,0))>@Ba*0.1)
				begin
					set @result=@result+';SSO输入量'+convert(varchar(2),@hour)+'点,具体详情请查看邮件'
					break
				end
				if (abs((@b-isnull(@Tb,0))/@b)>0.1) and (abs(@b-isnull(@Tb,0))>@Bb*0.1)
				begin
					set @result=@result+';SSO订制量'+convert(varchar(2),@hour)+'点,具体详情请查看邮件'
					break
				end
				fetch next from mycursor into @type,@a,@b,@Ta,@Tb,@Ba,@Bb
			end
		close mycursor 
		deallocate mycursor
	end
	select @result as num
end




exec MonTime 'MonSMSSubScribe'
