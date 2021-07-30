update bss_tratamento_cdrs
set local = 'Plano Local Fixo 03',
vc = 'Plano Local Móvel 03',
ldn = 'Plano LDN 02',
ldi = 'Plano LDI 1',
componentid = 10005,
parcomponentidlocal = 23824,
parcomponentidvc = 23834,
parcomponentidldn = 23839,
parcomponentidldi = 23843,
log = 'Atualizou elementos para o Plano Corporate D',
resultado = 6
where created = trunc(sysdate)
and plano = 'Plano Corporate D'
and parcomponentidlocal = 0
--and log like '%Siebel%orporate%sem par%metros%'