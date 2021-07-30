update bss_tratamento_cdrs
   set resultado = 4, data_interface = sysdate
 where created = trunc(sysdate)
   and external_id = ?
   and resultado = 5
   