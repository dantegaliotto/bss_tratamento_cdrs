select external_id, 
       external_id_type,
       greatest(active_dt1, active_dt2) dt_desativar,
       component_id,
       server_id,
       subscrno, 
       external_id instancia, 
       component_inst_id,
       package_id,
       'Plano duplicado'          motivo,
       ''                         arborcomponentdescription
  from (
       select external_id         external_id,
              serverid            server_id,
              external_id_type    external_id_type,
              subscr_no           subscrno,
              component_id1       component_id,
              package_id1         package_id,
              component_inst_id1  component_inst_id,
              active_dt1          active_dt1, 
              min(active_dt2)     active_dt2
       from bss_430_duplicidades 
       where created = trunc(sysdate)
 --      and external_id in ('5130222763','1148721430','7130129629','4130334162','4130336929')
       group by external_id, serverid, external_id_type, subscr_no, element_id1, component_id1, package_id1, 
                component_inst_id1, active_dt1)