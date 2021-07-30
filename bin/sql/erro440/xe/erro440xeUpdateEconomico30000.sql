update bss_tratamento_cdrs set
local = 'Plano Local Fixo 07',
vc = 'Plano Local Móvel 12 (30+6)',
ldn = 'Plano LDN Especial 14 (60+6)',
ldi = 'Plano LDI 1',
parcomponentidlocal = 31045,
parcomponentidvc = 31049,
parcomponentidldn = 31047,
parcomponentidldi = 23843
where created = trunc(sysdate)
and plano = 'Econômico 30000'
