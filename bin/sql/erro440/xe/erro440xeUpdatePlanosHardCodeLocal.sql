update bss_tratamento_cdrs
   set parcomponentidlocal = 
       decode(local,'Local Pré-Vendas IV (30+6)', 23830)
 where local in (   'Local Pré-Vendas IV (30+6)')
   and created = trunc(sysdate) 
   and parcomponentidlocal = 0
