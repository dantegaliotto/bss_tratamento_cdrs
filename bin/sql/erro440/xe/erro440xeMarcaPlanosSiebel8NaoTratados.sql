update bss_tratamento_cdrs
set log = 'SISTEMA: PLANO NULO OU NÃO CONFIGURADO NO SISTEMA', resultado = -2
where created = trunc(sysdate)
and (plano not in (select plano from bss_mapa_planos)
 or  plano is null)
 and resultado = 6