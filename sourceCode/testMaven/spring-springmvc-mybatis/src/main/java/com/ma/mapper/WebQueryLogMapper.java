package com.ma.mapper;


import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface WebQueryLogMapper {
    @Select("select t.id,t.querydate,t.userid,t.querywords,t.urlplace,t.clickorder,t.urlstr from WEBQUERYLOG t where t.id=25842861")
    public Map<String,Object> get();


}
