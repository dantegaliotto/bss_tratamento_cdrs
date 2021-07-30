INSERT
INTO BSS_DESATIVACOES_AUTOROT
  (
    processo,     created,        instancia,       external_id,
    subscrno,     dt_desativar,   PACKAGE_ID,      COMPONENT_INST_ID,
	component_id, server_id,      dt_envio,        motivo,
	arborcomponentdescription,    nivelkenan,      external_id_type
  )
  VALUES
  (
    'Erro440',    trunc(sysdate), ?,               ?, 
    ?,            ?,              ?,               ?,
    ?,            ?,              trunc(sysdate),  ?,
    ?,                            ?,               ?
  )

  
