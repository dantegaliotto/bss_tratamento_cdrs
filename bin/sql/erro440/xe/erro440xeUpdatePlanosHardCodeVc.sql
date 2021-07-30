update bss_tratamento_cdrs
   set parcomponentidvc = 
       decode(vc,'Plano Local Móvel 07', 23835,
                 'Plano Local Móvel 05 (30+6)', 23836)
 where vc in (   'Plano Local Móvel 07', 
                 'Plano Local Móvel 05 (30+6)')
   and created = trunc(sysdate) 
   and parcomponentidvc = 0