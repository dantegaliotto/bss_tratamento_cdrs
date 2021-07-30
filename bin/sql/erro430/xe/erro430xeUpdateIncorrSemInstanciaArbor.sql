UPDATE bss_tratamento_cdrs
   SET resultado = -7, log = 'Sem instancia no Arbor'
 WHERE processo = 'Erro430' and created = trunc(sysdate)
   and subscr_no = 0