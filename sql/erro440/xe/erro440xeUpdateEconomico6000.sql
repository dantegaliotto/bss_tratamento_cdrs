update bss_tratamento_cdrs
set parcomponentidlocal = 31044,
parcomponentidvc = 31048,
parcomponentidldn = 31046,
log = 'Atualizou componentes para o Econômico 6000',
resultado = 6
where created = trunc(sysdate)
and plano in ('Econômico 6000') --,'Econômico 30000')
and local = 'Plano Local Fixo 06'
and parcomponentidlocal = 0
