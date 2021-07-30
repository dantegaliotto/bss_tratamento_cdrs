UPDATE bss_tratamento_cdrs
   SET resultado = -5, log = 'Sem plano no Siebel'
 WHERE processo = 'Erro430' AND created = trunc(sysdate) 
   AND (componentid = 0 or cspid_siebel is null)
   AND resultado = 0