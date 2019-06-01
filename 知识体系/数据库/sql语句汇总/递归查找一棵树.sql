--测试   
    
  --测试数据   
  create   table   表(ID   int,是否为部门   char(1),部门名   varchar(10),上级ID   int)   
  insert   表   select   1   ,'y','部门0'   ,1   
  union   all   select   31,'y','部门1'   ,1   
  union   all   select   32,'n','张三'   ,31   
  union   all   select   33,'n','李二'   ,31   
  union   all   select   34,'y','部门2',31   
  union   all   select   35,'n','王五'   ,34   
  union   all   select   35,'y','部门3',34   
  union   all   select   36,'n','小三'   ,35   
  go   
    
  --创建查询函数   
  create   function   f_id(   
  @id   int --要查询的id   
  )returns   @re   table(id   int,level   int)   
  as   
  begin   
  declare   @l   int   
  set   @l=0   
  insert   @re   select   id,@l   
  from   表     
  where   上级id=@id   
  while   @@rowcount>0   
  begin   
  set   @l=@l+1   
  insert   @re   select   a.id,@l   
  from   表   a   join   @re   b   on   a.上级id=b.id   and   b.level=@l-1   
  end   
  return   
  end   
  go   
    
  --调用函数进行查询   
  select   a.*   from   表   a   join   f_id(35)   b   on   a.id=b.id   
  go   
    
  --删除测试   
  drop   table   表   
  drop   function   f_id   
    
  /*--测试结果   
    
  ID                     是否为部门   部门名                 上级ID                   
  -----------   -----   ----------   -----------     
  36                     n           小三                   35   
    
  （所影响的行数为   1   行）   
  --*/