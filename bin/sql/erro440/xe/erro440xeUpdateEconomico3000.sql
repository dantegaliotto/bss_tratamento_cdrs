update bss_tratamento_cdrs set
parcomponentidlocal = 31375,
parcomponentidvc = 31376,
parcomponentidldn = 31377
where created = trunc(sysdate)
and plano = 'Econ�mico 3.000'