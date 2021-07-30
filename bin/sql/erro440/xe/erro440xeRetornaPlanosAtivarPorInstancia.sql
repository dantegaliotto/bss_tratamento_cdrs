
-- parte retail:
select b1.rowidelementoplano element_id, 
       b2.external_id external_id, 
       6 external_id_type, 
       b1.componentid component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')) ) dt_ativar,
       b1.serverid server_id, 
       b2.subscrno subscrno, 
       b1.external_id instancia, 
       0 flag
  from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where     b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created >= trunc(sysdate) and b2.created >= trunc(sysdate) 
           and b1.tipoplano = 'R'
   and b1.external_id = b2.instancia
   and b1.external_id = ?
   and b1.componentid > 0
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate)
         and instancia = b1.external_id 
         and external_id = b2.external_id 
         and componentid = b1.componentid)
 union

-- parte corporate:

select b1.rowidelementoplano element_id, b2.external_id external_id, 6 external_id_type, 
       b1.parcomponentidlocal component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')) ) dt_ativar,
       b1.serverid server_id, b2.subscrno subscrno, b1.external_id instancia, 0 flag
  from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where     b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created >= trunc(sysdate) and b2.created >= trunc(sysdate) 
           and b1.tipoplano = 'C'
   and b1.external_id = b2.instancia
   and b1.external_id = ?
   and parcomponentidlocal > 0
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate)
         and instancia = b1.external_id 
         and external_id = b2.external_id 
         and componentid = b1.parcomponentidlocal)
 union
 
select b1.rowidelementoplano element_id, b2.external_id external_id, 6 external_id_type, 
       b1.parcomponentidvc component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')) ) dt_ativar,
       b1.serverid server_id, b2.subscrno subscrno, b1.external_id instancia, 0 flag
  from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where     b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created >= trunc(sysdate) and b2.created >= trunc(sysdate) 
           and b1.tipoplano = 'C'
   and b1.external_id = b2.instancia
   and b1.external_id = ?
   and parcomponentidvc > 0
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate)
         and instancia = b1.external_id 
         and external_id = b2.external_id 
         and componentid = b1.parcomponentidvc)
  union
  
select b1.rowidelementoplano element_id, b2.external_id external_id, 6 external_id_type, 
       b1.parcomponentidldn component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')) ) dt_ativar,
       b1.serverid server_id, b2.subscrno subscrno, b1.external_id instancia, 0 flag
  from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where     b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created >= trunc(sysdate) and b2.created >= trunc(sysdate) 
           and b1.tipoplano = 'C'
   and b1.external_id = b2.instancia
   and b1.external_id = ?
   and parcomponentidldn > 0
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate)
         and instancia = b1.external_id 
         and external_id = b2.external_id 
         and componentid = b1.parcomponentidldn)
  union
  
select b1.rowidelementoplano element_id, b2.external_id external_id, 6 external_id_type, 
       b1.parcomponentidldi component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')) ) dt_ativar,
       b1.serverid server_id, b2.subscrno subscrno  , b1.external_id instancia, 0 flag
  from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where     b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created >= trunc(sysdate) and b2.created >= trunc(sysdate) 
           and b1.tipoplano = 'C'
   and b1.external_id = b2.instancia
   and b1.external_id = ?
   and parcomponentidldi > 0
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate) 
         and instancia = b1.external_id 
         and external_id = b2.external_id 
         and componentid = b1.parcomponentidldi)
         
 union

-- parte CSP retail:
select b1.rowidelementoplano element_id, b2.external_id external_id, 
       6 external_id_type, 
       b1.cspid_kenan component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')) ) dt_ativar,
       b1.serverid server_id, b2.subscrno subscrno, b1.external_id instancia, 0 flag
       from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created >= trunc(sysdate) and b2.created >= trunc(sysdate) 
   and b1.external_id = b2.instancia
   and b1.external_id = ?
   and b1.cspid_kenan > 0
   and (b1.tipoplano = 'R' or b1.tipoplano = 'C')
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate) 
         and instancia = b1.external_id 
         and external_id = b2.external_id 
         and componentid = b1.cspid_kenan)

 union
 
/* parte VOX 0800 */ 
select b1.rowidelementoplano element_id, 
       b2.external_id external_id, 
       6 external_id_type, 
       b1.componentid component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')) ) dt_ativar,
       b1.serverid server_id, 
       b2.subscrno subscrno, 
       b1.external_id instancia, 
       0 flag
  from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where     b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created >= trunc(sysdate) and b2.created >= trunc(sysdate) 
           and b1.tipoplano = 'C'
           and (b1.tipo_instancia_siebel like 'VOX 0800%'
                OR (b1.tipo_instancia_siebel like 'VOX DDR%' and parcomponentidlocal = 0))
   and b1.external_id = b2.instancia
   and b1.external_id = ?
   and componentid > 0
   and b2.dtinstancia is not null
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate) 
         and instancia = b1.external_id 
         and external_id = b2.external_id 
         and componentid = b1.componentid)
         

