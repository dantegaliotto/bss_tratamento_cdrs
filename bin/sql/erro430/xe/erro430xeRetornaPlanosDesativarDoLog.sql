select distinct *
  from bss_430_desativacoes
 where created = trunc(sysdate)
   and result is null   