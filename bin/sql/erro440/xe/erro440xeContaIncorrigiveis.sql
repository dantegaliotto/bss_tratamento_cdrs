select sum(corrigiveis) corrigiveis, sum(incorrigiveis) incorrigiveis from (
(  select count(*) corrigiveis, 0 incorrigiveis 
  from cdr_data_work
  where external_id = ?
  and miu_disp_status <> 0
  and miu_disp_code in (0,1)
  and to_date(trim(trans_dt),'yyyymmdd hh24:mi:ss') >= ?
 )
     union
 (
  select 0 corrigiveis, count(*) incorrigiveis 
  from cdr_data_work
  where external_id = ?
  and miu_disp_status <> 0
  and miu_disp_code in (0,1)
  and to_date(trim(trans_dt),'yyyymmdd hh24:mi:ss') < ?
  )
)
