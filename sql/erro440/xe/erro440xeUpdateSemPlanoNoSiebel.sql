update BSS_TRATAMENTO_CDRS
   set log = 'Sem plano no Siebel', log = -2
 WHERE CREATED = TRUNC(SYSDATE)
   and componentid = 0
