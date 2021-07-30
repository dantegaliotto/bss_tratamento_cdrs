update bss_tratamento_cdrs
   set parcomponentidldn = 
       decode(ldn,'Plano LDN Especial 10 (30+6)', 24326,
                  'Plano LDN 03 (30 + 60)', 23841)
 where ldn in (   'Plano LDN Especial 10 (30+6)', 
                  'Plano LDN 03 (30 + 60)')
   and created = trunc(sysdate) 
   and parcomponentidldn = 0
