update bss_tratamento_cdrs_plano
   set desativado_pelo_processo = ?
 where processo = 'Erro440'
   and created = trunc(sysdate)
   and external_id = ?
   and componentid = ?
   and component_inst_id = ?
   and result is null