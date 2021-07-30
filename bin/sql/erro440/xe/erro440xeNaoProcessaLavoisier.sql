UPDATE bss_tratamento_cdrs
set log = 'PLANO LAVOISIER', RESULTADO = -2
where created = trunc(sysdate)
and plano like 'GVT%'
