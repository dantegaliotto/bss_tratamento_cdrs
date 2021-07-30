SELECT *
FROM bss_tratamento_cdrs
WHERE processo = 'Erro430'
AND created    = TRUNC(sysdate)
and resultado is null
and tipoplano is null
--and tipoplano = 'R'