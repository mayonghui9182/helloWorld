检查SQL内存使用情况

select *;cntr_value*1.0/(1024*1024) from master..sysperfinfo where counter_name like ''%total%
memory%'' 