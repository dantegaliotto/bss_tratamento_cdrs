INSERT
INTO bss_tratamento_cdrs_load_dia (
   processo,created,
   external_id,
   tipo_uso,
   dia,
   total_cdrs,
   designador,
   rpon,
   log,
   resultado
  )
  VALUES (
    'Erro440', trunc(sysdate),
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?,
    ?
)