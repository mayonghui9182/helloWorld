--创建取拼音函数
create function FunGetPinYin(@Str varchar(500)='')
returns varchar(500)
as
begin
    declare @strlen int,@return varchar(500),@ii int
    select @strlen=len(@str),@return='',@ii=0
    set @ii=0
    while @ii<@strlen
    begin
        set @ii=@ii+1
        select @return=@return+py
            from (select 0xB0A1 as cbegin, 0xB0C4 as cend,'A' as py
                union all select 0xB0C5, 0xB2C0,'B' 
                union all select 0xB2C1, 0xB4ED,'C'
                union all select 0xB4EE, 0xB6E9,'D'
                union all select 0xB6EA, 0xB7A1,'E'
                union all select 0xB7A2, 0xB8C0,'F'
                union all select 0xB8C1, 0xB9FD,'G'
                union all select 0xB9FE, 0xBBF6,'H'
                union all select 0xBBF7, 0xBFA5,'J'
                union all select 0xBFA6, 0xC0AB,'K'
                union all select 0xC0AC, 0xC2E7,'L'
                union all select 0xC2E8, 0xC4C2,'M'
                union all select 0xC4C3, 0xC5B5,'N'
                union all select 0xC5B6, 0xC5BD,'O'
                union all select 0xC5BE, 0xC6D9,'P'
                union all select 0xC6DA, 0xC8BA,'Q'
                union all select 0xC8BB, 0xC8F5,'R'
                union all select 0xC8F6, 0xCBF9,'S'
                union all select 0xCBFA, 0xCDD9,'T'
                union all select 0xCDDA, 0xCEF3,'W'
                union all select 0xCEF4, 0xD1B8,'X'
                union all select 0xD1B9, 0xD4D0,'Y'
                union all select 0xD4D1, 0xD7F9,'Z') a
            where cast(substring(@str,@ii,1) as varbinary)
                    between cbegin and cend
    end
    return(@return)
end

go
--测试
select dbo.FunGetPinYin('我是一个兵') as 我是一个兵,dbo.FunGetPinYin('中国人') as 中国人

--删除拼音函数
drop function fgetpy


USE [OperatorsV5]
GO
/****** 对象:  UserDefinedFunction [dbo].[FunGetPinYin]    脚本日期: 03/24/2009 16:47:39 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

--创建取拼音函数
CREATE function [dbo].[FunGetPinYin](@Str varchar(500)='')
returns varchar(500)
as
begin
    declare @strlen int,@return varchar(500),@ii int,@temp varchar(10),@one varchar(5)
    select @strlen=len(@str),@return='',@ii=0
    set @ii=1
    while @ii<@strlen+1
    begin
        select @temp=py 
            from (select 0xB0A1 as cbegin, 0xB0C4 as cend,'A' as py
                union all select 0xB0C5, 0xB2C0,'B' 
                union all select 0xB2C1, 0xB4ED,'C'
                union all select 0xB4EE, 0xB6E9,'D'
                union all select 0xB6EA, 0xB7A1,'E'
                union all select 0xB7A2, 0xB8C0,'F'
                union all select 0xB8C1, 0xB9FD,'G'
                union all select 0xB9FE, 0xBBF6,'H'
                union all select 0xBBF7, 0xBFA5,'J'
                union all select 0xBFA6, 0xC0AB,'K'
                union all select 0xC0AC, 0xC2E7,'L'
                union all select 0xC2E8, 0xC4C2,'M'
                union all select 0xC4C3, 0xC5B5,'N'
                union all select 0xC5B6, 0xC5BD,'O'
                union all select 0xC5BE, 0xC6D9,'P'
                union all select 0xC6DA, 0xC8BA,'Q'
                union all select 0xC8BB, 0xC8F5,'R'
                union all select 0xC8F6, 0xCBF9,'S'
                union all select 0xCBFA, 0xCDD9,'T'
                union all select 0xCDDA, 0xCEF3,'W'
                union all select 0xCEF4, 0xD1B8,'X'
                union all select 0xD1B9, 0xD4D0,'Y'
                union all select 0xD4D1, 0xD7F9,'Z') a
            where cast(substring(@str,@ii,1) as varbinary)
                    between cbegin and cend
			set @one=cast(substring(@str,@ii,1) as varbinary)
			select @return=@return+ISNULL(@temp,@one)
			set @ii=@ii+1
			set @temp=NULL
    end
    return(lower(@return))
end




SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create function [dbo].[FunGetPinYin](@str nvarchar(4000))
returns nvarchar(4000)
as
begin
declare @strlen int,@re nvarchar(4000)
declare @t table(chr nchar(1) collate Chinese_PRC_CI_AS,letter nchar(1))
insert into @t(chr,letter)
  select '吖','A' union all select '八','B' union all
  select '嚓','C' union all select '咑','D' union all
  select '妸','E' union all select '发','F' union all
  select '旮','G' union all select '铪','H' union all
  select '丌','J' union all select '咔','K' union all
  select '垃','L' union all select '嘸','M' union all
  select '拏','N' union all select '噢','O' union all
  select '妑','P' union all select '七','Q' union all
  select '呥','R' union all select '仨','S' union all
  select '他','T' union all select '屲','W' union all
  select '夕','X' union all select '丫','Y' union all
  select '帀','Z'
  select @strlen=len(@str),@re=''
  while @strlen>0
  begin
    select top 1 @re=letter+@re,@strlen=@strlen-1
      from @t a where chr<=substring(@str,@strlen,1)
      order by chr desc
    if @@rowcount=0
      select @re=substring(@str,@strlen,1)+@re,@strlen=@strlen-1
  end
  return(lower(@re))
end
GO

SET ANSI_NULLS OFF
GO
SET QUOTED_IDENTIFIER OFF
GO

