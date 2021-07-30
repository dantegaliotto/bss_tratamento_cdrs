insert into bss_duplicados_desativacoes (
select 'Erro430', trunc(sysdate),external_id, server_id, ciclo, 
       tracking_id_2, 
       component_id_2, 
       package_id_2,
       component_inst_id_2,
       min(active_dt_2),
       'Via sautorot', 0, null
  from bss_duplicados_duplicidades
 where created = trunc(sysdate) 
   and (inactive_dt_1 is null and inactive_dt_2 is null)
   and active_dt_2 > active_dt_1
group by external_id,   server_id,   ciclo, 
         tracking_id_2, component_id_2, 
         package_id_2,  component_inst_id_2
         )

