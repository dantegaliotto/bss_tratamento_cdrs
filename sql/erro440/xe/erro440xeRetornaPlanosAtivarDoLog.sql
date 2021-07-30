select distinct *
  from bss_ativacoes_autorot
 where created = trunc(sysdate)
   and result is null
