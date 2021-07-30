select distinct *
  from bss_430_ativacoes
 where created = trunc(sysdate)
   and result is null
