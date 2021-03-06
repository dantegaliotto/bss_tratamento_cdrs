UPDATE bss_tratamento_cdrs
SET rpon              = ?,
  designador          = ?,
  rowidelementoplano  = ?,
  plano               = ?,
  data_plano_siebel   = ?,
  tipoplano           = ?,
  local               = ?,
  vc                  = ?,
  ldn                 = ?,
  ldi                 = ?,
  componentid         = ?,
  parcomponentidlocal = ?,
  parcomponentidvc    = ?,
  parcomponentidldn   = ?,
  parcomponentidldi   = ?,
  csp_siebel          = ?,
  cspid_siebel        = ?,
  cspid_kenan         = ?
WHERE processo        = 'Erro430'
and created = trunc(sysdate)
AND externalid        = ?
AND msg_id            = ?