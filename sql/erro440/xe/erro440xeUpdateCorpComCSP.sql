update BSS_TRATAMENTO_CDRS
   set csp_siebel = null, cspid_siebel = 0, cspid_kenan = 0
 WHERE CREATED = TRUNC(SYSDATE)
   and tipo_instancia_siebel  <> 'VOX DDR'
   and tipo_instancia_siebel  <> 'VOX DIGITAL TRONCO'
   and tipoplano = 'C'
   and cspid_kenan <> 0
