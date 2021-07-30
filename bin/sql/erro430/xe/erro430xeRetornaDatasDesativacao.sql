select componentinst, 
       min(external_id) external_id, 
       min(component_id) component_id, 
       min(created) created, 
       min(dtactive) dtactive, 
       min(primeirocdr) primeirocdr,
       min(dtdesativar) dtdesativar, 
       min(server_id) server_id, 
       min(package_id) package_id
       from (
       -- agrupa pelo componentinst para não dar problema de indice na gvt_disc_component
select distinct b1.externalid external_id, b2.componentinst componentinst, b2.component_id component_id, b2.created created,
       b2.dtactive dtactive, b1.primeirocdr primeirocdr,
       case when ( b2.dtactive >= b1.primeirocdr ) then
          b2.dtactive
       else
          b1.primeirocdr
       end dtdesativar, b2.serverid server_id, b2.package_id package_id
  from bss_tratamento_cdrs b1, bss_tratamento_cdrs_produtos b2
 where b1.created = trunc(sysdate) and b2.created = trunc(sysdate)
   and b1.processo = 'Erro430' and b2.processo = 'Erro430' 
   and b1.msg_id = b2.msg_id
   and b1.resultado = 0
   and b2.desconectar <> 0
   and b1.externalid = b2.external_id
 --order by b1.externalid, b1.tipo_uso
 --order by componentinst
 ) group by componentinst
