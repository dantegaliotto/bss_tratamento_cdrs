update bss_tratamento_cdrs
   set parcomponentidvc = 
       decode(vc,'Plano Local M�vel 07', 23835,
                 'Plano Local M�vel 05 (30+6)', 23836)
 where vc in (   'Plano Local M�vel 07', 
                 'Plano Local M�vel 05 (30+6)')
   and created = trunc(sysdate) 
   and parcomponentidvc = 0