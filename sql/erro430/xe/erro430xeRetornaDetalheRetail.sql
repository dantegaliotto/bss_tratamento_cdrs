SELECT componentinst,
  produto,
  dtactive,
  dtultfaturamento,
  created,
  component_id
FROM bss_tratamento_cdrs_produtos btcp
WHERE btcp.external_id = ?
AND btcp.msg_id        = ?
AND btcp.created       = TRUNC(sysdate)