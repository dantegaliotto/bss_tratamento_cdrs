update BSS_DESATIVACOES_AUTOROT 
   set result = ?
 where processo = 'Erro440'
   and created = trunc(sysdate)
   and external_id = ? 
   and component_id = ?
   and component_inst_id = ?
   and result is null