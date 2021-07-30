select distinct external_id
  from bss_tratamento_cdrs_load
 where processo = 'Erro440' 
   and rpon = ?
   and created = trunc(sysdate)
