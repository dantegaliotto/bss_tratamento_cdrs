select distinct *
  from bss_desativacoes_autorot
 where processo = 'Erro440'
   and result is null
   and package_id > 0
   and created = trunc(sysdate)
   and component_inst_id <> 0
   and dt_desativar is not null
