select top 10 
text,
plan_generation_num as 该计划保留在缓存中时被重新编译的次数,
creation_time as 编译计划的时间,
last_execution_time as 上次执行计划的时间,
execution_count as 计划自上次编译以来所执行的次数, 
total_worker_time as 此计划自编译以来执行所用的CPU时间总量,
last_worker_time as上次执行计划所用的CPU时间,
min_worker_time as 此计划在单次执行期间曾占用的最小CPU时间,
max_worker_time as 此计划在单次执行期间曾占用的最大CPU时间,
total_physical_reads as 此计划自编译后在执行期间所执行的物理读取总次数,
last_physical_reads as上次执行计划时所执行的物理读取次数,
min_physical_reads as 该计划在单个执行期间所执行的最少物理读取次数,
max_physical_reads as 该计划在单个执行期间所执行的最多物理读取次数,
total_elapsed_time as 完成此计划的执行所占用的总时间,
last_elapsed_time as 最近完成此计划的执行所占用的时间,
min_elapsed_time as任意一次完成此计划的执行所占用的最短时间,
max_elapsed_time as 任意一次完成此计划的执行所占用的最长时间
from sys.dm_exec_query_stats a CROSS APPLY sys.dm_exec_sql_text(sql_handle) 
order by execution_count desc
 
