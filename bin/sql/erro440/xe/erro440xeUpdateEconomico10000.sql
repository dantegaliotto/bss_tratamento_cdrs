update bss_tratamento_cdrs set
parcomponentidlocal = 31610,
parcomponentidvc = 31611,
parcomponentidldn = 31612
where created = trunc(sysdate)
and plano = 'Econ�mico 10.000'
