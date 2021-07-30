UPDATE bss_tratamento_cdrs
   SET resultado = -4, log = 'Sem plano no Arbor'
 WHERE processo = 'Erro430' and created = trunc(sysdate)
   and 
  (externalid, msg_id) IN
  (SELECT btc.externalid,
          btc.msg_id
     FROM bss_tratamento_cdrs btc,
          bss_tratamento_cdrs_produtos btcm
    WHERE btc.processo     = 'Erro430'
      AND btc.msg_id       = btcm.msg_id(+)
      AND btcm.external_id is null
      AND btc.created      = TRUNC(sysdate)
      AND btc.resultado    = 0
 GROUP BY btc.externalid,
          btc.msg_id)
          