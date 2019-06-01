create table #tmpA 
( Dept char(3) , Sect char(3) , line char(3) , Line_Desc varchar(30) , Title_code char(3) , Title_Desc varchar(30) ,  Headcount int  
) 

-- 加入一些数据记录 
Insert #tmpA  Select 'DA' , 'S1' , 'La' , 'Line a ' , 'T1' , 'Title Desc 1', 3 
Insert #tmpA  Select 'DA' , 'S1' , 'La' ,  'Line a ' , 'T2' ,  'Title Desc 2' ,  3 
Insert #tmpA  Select 'DA' , 'S1' , 'Lb' , 'Line b ' ,  'T1' ,  'Title Desc 1' , 3 
Insert #tmpA  Select 'DA' , 'S1' , 'Lb' , 'Line b ' ,  'T2' ,  'Title Desc 2' ,  3 
Insert #tmpA  Select 'DA' , 'S2' , 'La' , 'Line a ' ,  'T1' ,   'Title Desc 1' , 3 
Insert #tmpA  Select 'DA' , 'S2' , 'Lb' , 'Line b ' ,  'T1' ,   'Title Desc 1' , 3 
Insert #tmpA  Select 'DB' , 'S1' , 'Lb' , 'Line b ' ,  'T1' ,   'Title Desc 1' , 3 
Insert #tmpA  Select 'DB' , 'S2' , 'La' , 'Line a ' ,  'T1' ,   'Title Desc 1' , 3 
Insert #tmpA  Select 'DB' , 'S2' , 'Lb' , 'Line b ' ,  'T1' ,   'Title Desc 1' , 3 
Insert #tmpA  Select 'DC' , 'S1' , 'La' , 'Line a ' ,  'T1' ,   'Title Desc 1' , 3 
Insert #tmpA  Select 'DC' , 'S2' , 'Lb' , 'Line b ' ,  'T1' ,   'Title Desc 1' , 3 

select * from #tmpA 

-- 直接使用的效果 
select dept , sect , line , title_code , max(title_desc) as title_desc , sum(headcount) as Headcounts 
 from #tmpA 
 group by dept , sect , line , title_code with rollup 

-- 对其中一个进行转换标题的效果 
select dept , sect , line , ( case  when title_code is null then 'total ( by Line ) : ' else title_code end )  as title_code, sum(headcount) as Headcounts 
 from #tmpA 
 group by dept , sect , line , title_code with rollup 

select dept , sect , line ,  
 ( case  when (title_code is null) and ( line is not null) then 'total ( by Line ) : ' else title_code end )  as title_code,  
 sum(headcount) as Headcounts 
 from #tmpA 
 group by dept , sect , line , title_code with rollup 

-- 对所有的进行处理后的结果 
select   
 ( case  when (dept is null) then 'Grant total  : ' else isNull(dept,'')  end )  as sect,  
 ( case  when (sect is null) and ( dept is not null) then 'total ( by Dept ) : ' else IsNull(sect,'') end )  as sect,  
 ( case  when (line is null) and ( sect is not null) then 'total ( by Sect ) : ' else IsNull(line,'') end )  as line,  
 ( case  when (title_code is null) and ( line is not null) then 'total ( by Line ) : ' else isNull(title_code,'') end )  as title_code,  
 sum(headcount) as Headcounts 
 from #tmpA 
 group by dept , sect , line , title_code with rollup 

-- 对所有的进行处理后的结果 
select   
 ( case  when (dept is null) then 'Grand total  : ' else isNull(dept,'')  end )  as sect,  
 ( case  when (sect is null) and ( dept is not null) then 'Total ( by Dept  : ' + dept + ') : ' else IsNull(sect,'') end )  as sect,  
 ( case  when (line is null) and ( sect is not null) then 'Total ( by Sect : ' + Sect + ' ) : ' else IsNull(line,'') end )  as line,  
 ( case  when (title_code is null) and ( line is not null) then '小计 ( 按组别  : ' + line + ') : ' else isNull(title_code,'') end )  as title_code,  
 sum(headcount) as Headcounts 
 from #tmpA 
 group by dept , sect , line , title_code with rollup 

select   
 ( case  when (dept is null) then 'Grant total  : ' else isNull(dept,'')  end )  as sect,  
 ( case  when (sect is null) and ( dept is not null) then 'total ( by Dept ) : ' else IsNull(sect,'') end )  as sect, max(line) , max(title_code) ,  
 sum(headcount) as Headcounts 
 from #tmpA 
 group by dept , sect  with rollup 

drop table #tmpA
