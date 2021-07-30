select b3.external_id, 
    greatest ( nvl(b3.dtplano, to_date('01/01/1999','dd/mm/yyyy')), 
               nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
               nvl(b1.data_ultimo_faturamento, to_date('01/01/1999','dd/mm/yyyy')) ) dt_desativar,    
       b3.componentid component_id, b1.serverid server_id, b3.subscrno subscrno, b1.external_id instancia
  from bss_tratamento_cdrs b1, bss_tratamento_cdrs_plano b3
 where b1.processo = 'Erro440' and b3.processo = 'Erro440'
   and b1.created >= trunc(sysdate) and b3.created >= trunc(sysdate)
   and b1.tipoplano = 'C'
   and b1.plano like '%orporate%'
      and b1.tipo_instancia_siebel not like '%VOX 0800%'
      and b1.tipo_instancia_siebel not like '%VOX DDR%'   
      and b1.tipo_instancia_siebel not like '%VOX DIGITAL TRONCO%'
   and b1.external_id = b3.instancia
   and b1.external_id = ?
   and parcomponentidlocal + parcomponentidvc + parcomponentidldn + parcomponentidldi > 0
   and instr(parcomponentidlocal || ' ' || parcomponentidvc || ' ' || parcomponentidldn || ' ' || parcomponentidldi , b3.componentid) = 0

union

select b3.external_id, 
        greatest ( nvl(b3.dtplano, to_date('01/01/1999','dd/mm/yyyy')), 
               nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
               nvl(b1.data_ultimo_faturamento, to_date('01/01/1999','dd/mm/yyyy')) ) dt_desativar,    
       b3.componentid component_id, b1.serverid server_id, b3.subscrno subscrno, b1.external_id instancia
  from bss_tratamento_cdrs b1, bss_tratamento_cdrs_plano b3
 where b1.processo = 'Erro440' and b3.processo = 'Erro440'
   and b1.created >= trunc(sysdate) and b3.created >= trunc(sysdate)
   and b1.tipoplano = 'C'
   and b1.tipo_instancia_siebel like '%VOX 0800%'
   and b1.external_id = b3.instancia
   and b1.external_id = ?
   -- o plano do desconto fica gravado na parcomponentidlocal
   and b1.componentid + b1.cspid_kenan + b1.parcomponentidlocal > 0
   and instr(b1.componentid  || ' ' ||  b1.cspid_kenan  || ' ' ||  b1.parcomponentidlocal , b3.componentid) = 0

union

select b3.external_id, 
    greatest ( nvl(b3.dtplano, to_date('01/01/1999','dd/mm/yyyy')), 
               nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
               nvl(b1.data_ultimo_faturamento, to_date('01/01/1999','dd/mm/yyyy')) ) dt_desativar,    
       b3.componentid component_id, b1.serverid server_id, b3.subscrno subscrno, b1.external_id instancia
  from bss_tratamento_cdrs b1, bss_tratamento_cdrs_plano b3
 where b1.processo = 'Erro440' and b3.processo = 'Erro440'
   and b1.created >= trunc(sysdate) and b3.created >= trunc(sysdate)
   and b1.tipoplano = 'C'
   and (b1.tipo_instancia_siebel like '%VOX DDR%'
        or b1.tipo_instancia_siebel like '%VOX DIGITAL TRONCO%')
   and b1.external_id = b3.instancia
   and b1.external_id = ?
   -- o plano do desconto fica gravado na parcomponentidlocal
   and b1.componentid + b1.cspid_kenan + b1.parcomponentidlocal > 0
   and instr(b1.componentid  || ' ' ||  b1.cspid_kenan  || ' ' ||  b1.parcomponentidlocal , b3.componentid) = 0

   and b1.cspid_kenan + parcomponentidlocal + parcomponentidvc + parcomponentidldn + parcomponentidldi > 0
   and instr(b1.cspid_kenan || ' ' || parcomponentidlocal || ' ' || parcomponentidvc || ' ' || parcomponentidldn || ' ' || parcomponentidldi , b3.componentid) = 0
   
   