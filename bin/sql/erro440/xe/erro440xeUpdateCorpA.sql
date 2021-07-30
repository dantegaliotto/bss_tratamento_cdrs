update bss_tratamento_cdrs
set local = 'Plano Local Fixo 01',
vc = 'Plano Local Móvel 01',
ldn = 'Plano LDN Especial 08 (60+6)',
ldi = 'Plano LDI 1',
componentid = 10001,
parcomponentidlocal = 23822,
parcomponentidvc = 23832,
parcomponentidldn = 24333,
parcomponentidldi = 23843,
log = 'Atualizou componentes para o Plano Corporate A',
resultado = 6
where created = trunc(sysdate)
and plano = 'Plano Corporate A'
and parcomponentidlocal = 0
--and log like '%Siebel%orporate%sem par%metros%'

