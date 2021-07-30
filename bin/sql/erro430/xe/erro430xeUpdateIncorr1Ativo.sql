UPDATE bss_tratamento_cdrs
   SET resultado = -2, log = 'So 1 plano ativo no Arbor'
 WHERE processo = 'Erro430' and created = trunc(sysdate)
  and 
  (externalid, msg_id) IN
  (SELECT btc.externalid,
          btc.msg_id
     FROM bss_tratamento_cdrs btc,
          bss_tratamento_cdrs_produtos btcm
    WHERE btc.processo   = 'Erro430'
      AND btc.created      = TRUNC(sysdate)
      AND btcm.created     = TRUNC(sysdate)
      AND btc.externalid   = btcm.external_id
      AND btc.msg_id       = btcm.msg_id
      AND btcm.result     IS NULL
      AND btc.resultado    = 0
      AND btcm.dtinactive IS NULL
 GROUP BY btc.externalid,
          btc.msg_id
   HAVING COUNT(1) = 1
   )