select external_id       external_id,
       serverid          serverid,
       external_id_type  external_id_type,
       subscr_no         subscr_no,
       element_id1       element_id,
       component_id1     component_id,
       package_id1       package_id,
       case when (component_id1 in (30362,30363,30361)) then 500 + mod_lavoisier else 0 end mod_lavoisier,
       case when (component_id1 in (30362,30363,30361)) then 8000                else 0 end jurisdiction,
       case when (component_id1 in (30362,30363,30361)) then 1                   else 0 end units,
       'Instância'       nivelkenan,
       --max(inactive_dt1) dt_ativar
       max(inactive_dt2) dt_ativar
  from bss_430_duplicidades 
 where created = trunc(sysdate)
--   and external_id in ('5130222763','1148721430','7130129629','4130334162','4130336929')
 group by external_id, external_id_type, subscr_no, mod_lavoisier, 
          element_id1, package_id1, component_id1, serverid
