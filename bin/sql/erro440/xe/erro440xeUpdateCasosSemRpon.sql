update bss_tratamento_cdrs_load
set log = 'Instancia sem Rpon no Siebel', resultado = -2
WHERE RPON is null
and created = trunc(sysdate)
