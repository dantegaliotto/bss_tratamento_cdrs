update bss_tratamento_cdrs
set local = 'Plano Local Fixo 02',
vc = 'Plano Local Móvel 02',
ldn = 'Plano LDN 02',
ldi = 'Plano LDI 1',
componentid = 10003,
parcomponentidlocal = 23823,
parcomponentidvc = 23833,
parcomponentidldn = 23839,
parcomponentidldi = 23843,
log = 'Atualizado componentes para o Plano Corporate B',
resultado = 6
where created = trunc(sysdate)
and plano = 'Plano Corporate B'
and parcomponentidlocal = 0
--and log like '%Siebel%orporate%sem par%metros%'