update BSS_430_DESATIVACOES 
   set result = ?
 where created = trunc(sysdate)
   and external_id = ? 
   and component_id = ?
   and component_inst_id = ?
   and result is null