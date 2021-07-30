INSERT
INTO bss_duplicados
  (
    processo,
    created,
    external_id,
    subscr_no,
	account_no,    
    server_id,
    ciclo,
    ultimofat,
    primeirocdr,
    ultimocdr,
    qtde
  )
  VALUES
  (
    'Erro430',
    trunc(sysdate),
    ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?,    ?
  )