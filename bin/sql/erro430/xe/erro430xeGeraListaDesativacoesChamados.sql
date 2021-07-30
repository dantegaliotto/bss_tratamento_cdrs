insert into bss_duplicados_desativacoes (
   select 'Erro430',
          trunc(sysdate) created,
          external_id, 
          server_id,
          ciclo,
          tracking_id,
          component_id,
          package_id,
          component_inst_id,
          min(data_desconexao) data_desconexao,
          'Via chamado',
          0, null
     from (
         select tracking_id_2 tracking_id, external_id, server_id, ciclo, component_id_2 component_id, package_id_2 package_id, component_inst_id_2 component_inst_id, greatest(active_dt_1, active_dt_2) data_desconexao
           from bss_duplicados_duplicidades
          where created = trunc(sysdate)
            and (inactive_dt_1 is null or inactive_dt_1 > inactive_dt_2)
            and inactive_dt_2 is not null
         union
         select tracking_id_2, external_id, server_id, ciclo, component_id_2, package_id_2, component_inst_id_2, active_dt_2
           from bss_duplicados_duplicidades
          where created = trunc(sysdate)
            and ((inactive_dt_1 = inactive_dt_2))
            and active_dt_2 > active_dt_1
   ) group by tracking_id, external_id, server_id, ciclo, component_id, package_id, component_inst_id
)
