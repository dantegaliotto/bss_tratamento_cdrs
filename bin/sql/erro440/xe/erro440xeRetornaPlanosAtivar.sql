-- parte TV:
select distinct b1.rowidelementoplano element_id, 
       b2.external_id external_id, 
       10 external_id_type, 
       b1.componentid component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')),
                 --nvl(b1.data_ultimo_faturamento, to_date('01/01/1999','dd/mm/yyyy')),
                 nvl(b1.primeirocdr, to_date('01/01/1999','dd/mm/yyyy'))
                 ) dt_ativar,
       b1.serverid server_id, 
       b2.subscrno subscrno, 
       b1.external_id instancia, 
       0 flag,
       'Plano TV' motivo,
       plano siebelcomponentdescription,
       'Instância' nivelkenan,
       0 mod_lavoisier,
       0 jurisdiction,
       0 units
  from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where     b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created = trunc(sysdate) and b2.created = trunc(sysdate) 
           and b1.tipoplano = 'TV'
   and b1.external_id = b2.instancia
--   and b1.external_id = ?
   and b1.componentid > 0
   and b1.resultado in (5,3)
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate)
           and instancia = b1.external_id 
           and external_id = b2.external_id 
           and componentid = b1.componentid
           and desativado_pelo_processo is null
           and validacao_elementos = 1)
           
union

-- parte retail:
select distinct b1.rowidelementoplano element_id, 
       b2.external_id external_id, 
       6 external_id_type, 
       b1.componentid component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')),
                 --nvl(b1.data_ultimo_faturamento, to_date('01/01/1999','dd/mm/yyyy')),
                 nvl(b1.primeirocdr, to_date('01/01/1999','dd/mm/yyyy'))
                 ) dt_ativar,
       b1.serverid server_id, 
       b2.subscrno subscrno, 
       b1.external_id instancia, 
       0 flag,
       'Plano Retail' motivo,
       plano siebelcomponentdescription,
       'Instância' nivelkenan,
       0 mod_lavoisier,
       0 jurisdiction,
       0 units
  from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where     b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created = trunc(sysdate) and b2.created = trunc(sysdate) 
           and b1.tipoplano in ('R', 'RS8', 'L')
   and b1.external_id = b2.instancia
--   and b1.external_id = ?
   and b1.componentid > 0
   and b1.resultado in (5,3)
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate)
           and instancia = b1.external_id 
           and external_id = b2.external_id 
           and componentid = b1.componentid
           and desativado_pelo_processo is null
           and validacao_elementos = 1)
           
 union
-- parte corporate Local
select distinct b1.rowidelementoplano element_id, b2.external_id external_id, 6 external_id_type, 
       b1.parcomponentidlocal component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')),
                 --nvl(b1.data_ultimo_faturamento, to_date('01/01/1999','dd/mm/yyyy')),                 
                 nvl(b1.primeirocdr, to_date('01/01/1999','dd/mm/yyyy'))
                 ) dt_ativar,
       b1.serverid server_id, b2.subscrno subscrno, b1.external_id instancia, 0 flag,
       'Plano Corporate Local' motivo,
       local siebelcomponentdescription,
       'Instância' nivelkenan,
       0 mod_lavoisier,
       0 jurisdiction,
       0 units
  from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where     b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created = trunc(sysdate) and b2.created = trunc(sysdate) 
           and b1.tipoplano = 'C'
   and b1.external_id = b2.instancia
   --and b1.external_id = ?
   and parcomponentidlocal > 0
   and b1.resultado in (5,3)
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate)
           and instancia = b1.external_id 
           and external_id = b2.external_id 
           and componentid = b1.parcomponentidlocal
           and desativado_pelo_processo is null
           and validacao_elementos = 1)
           
union

-- parte corporate VC pode ser corporate ou retail, faz isto para todos os tipos de instância
select distinct b1.rowidelementoplano element_id, b2.external_id external_id, 6 external_id_type, 
       b1.parcomponentidvc component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')),
                 --nvl(b1.data_ultimo_faturamento, to_date('01/01/1999','dd/mm/yyyy')),                 
                 nvl(b1.primeirocdr, to_date('01/01/1999','dd/mm/yyyy'))
                 ) dt_ativar,
       b1.serverid server_id, b2.subscrno subscrno, b1.external_id instancia, 0 flag,
       'Plano VC' motivo,
       vc siebelcomponentdescription,
       'Instância' nivelkenan,
       case when (b1.parcomponentidvc in (30362,30363,30361)) then 500 + b1.lavoisier else 0 end mod_lavoisier,
       case when (b1.parcomponentidvc in (30362,30363,30361)) then 8000               else 0 end jurisdiction,
       case when (b1.parcomponentidvc in (30362,30363,30361)) then 1                  else 0 end units
  from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where b1.processo = 'Erro440' and b2.processo = 'Erro440'
   and b1.created = trunc(sysdate) and b2.created = trunc(sysdate) 
   --and (b1.tipoplano = 'R' or b1.tipoplano = 'C')
   and b1.external_id = b2.instancia
   --and b1.external_id = ?
   and parcomponentidvc > 0
   -- caso o vc seja igual ao componentid, que pode acontecer no caso do retail, não considera
   and parcomponentidvc <> componentid
   and b1.resultado in (5,3)
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate)
           and instancia = b1.external_id 
           and external_id = b2.external_id 
           and componentid = b1.parcomponentidvc
           and desativado_pelo_processo is null
           and validacao_elementos = 1)
           
  union
-- parte corporate LDN
select distinct b1.rowidelementoplano element_id, b2.external_id external_id, 6 external_id_type, 
       b1.parcomponentidldn component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')),
                 --nvl(b1.data_ultimo_faturamento, to_date('01/01/1999','dd/mm/yyyy')),                 
                 nvl(b1.primeirocdr, to_date('01/01/1999','dd/mm/yyyy'))
                 ) dt_ativar,
       b1.serverid server_id, b2.subscrno subscrno, b1.external_id instancia, 0 flag,
       'Plano Corporate LDN' motivo,
       ldn siebelcomponentdescription,
       'Instância' nivelkenan,
       0 mod_lavoisier,
       0 jurisdiction,
       0 units
  from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where     b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created = trunc(sysdate) and b2.created = trunc(sysdate) 
           and b1.tipoplano = 'C'
   and b1.external_id = b2.instancia
   --and b1.external_id = ?
   and parcomponentidldn > 0
   and b1.resultado in (5,3)
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate)
           and instancia = b1.external_id 
           and external_id = b2.external_id 
           and componentid = b1.parcomponentidldn
           and desativado_pelo_processo is null
           and validacao_elementos = 1)
           
union

-- parte corporate LDI
select distinct b1.rowidelementoplano element_id, b2.external_id external_id, 6 external_id_type, 
       b1.parcomponentidldi component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')),
                 --nvl(b1.data_ultimo_faturamento, to_date('01/01/1999','dd/mm/yyyy')),                 
                 nvl(b1.primeirocdr, to_date('01/01/1999','dd/mm/yyyy'))
                 ) dt_ativar,
       b1.serverid server_id, b2.subscrno subscrno  , b1.external_id instancia, 0 flag,
       'Plano Corporate LDI' motivo,
       ldi siebelcomponentdescription,
       'Instância' nivelkenan,
       0 mod_lavoisier,
       0 jurisdiction,
       0 units
  from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where     b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created = trunc(sysdate) and b2.created = trunc(sysdate) 
           and b1.tipoplano = 'C'
   and b1.external_id = b2.instancia
   --and b1.external_id = ?
   and parcomponentidldi > 0
   and b1.resultado in (5,3)
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate) 
           and instancia = b1.external_id 
           and external_id = b2.external_id 
           and componentid = b1.parcomponentidldi
           and desativado_pelo_processo is null
           and validacao_elementos = 1)

union

-- parte CSP:
select distinct b1.rowidelementoplano element_id, b2.external_id external_id, 
       6 external_id_type, 
       b1.cspid_kenan component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_csp_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')),
                 --nvl(b1.data_ultimo_faturamento, to_date('01/01/1999','dd/mm/yyyy')),                 
                 nvl(b1.primeirocdr, to_date('01/01/1999','dd/mm/yyyy'))
                 ) dt_ativar,
       b1.serverid server_id, b2.subscrno subscrno, b1.external_id instancia, 0 flag,
       'Plano CSP' motivo,
       csp_siebel siebelcomponentdescription,
       'Instância' nivelkenan,
       0 mod_lavoisier,
       0 jurisdiction,
       0 units
       from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created = trunc(sysdate) and b2.created = trunc(sysdate) 
   and b1.external_id = b2.instancia
   --and b1.external_id = ?
   and b1.cspid_kenan > 0
   and b1.cspid_kenan <> 24006
   and b1.tipoplano in ('R', 'C', 'RS8', 'L')
   and b1.resultado in (5,3)
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate) 
           and instancia = b1.external_id 
           and external_id = b2.external_id 
           and componentid = b1.cspid_kenan
           and desativado_pelo_processo is null
           and validacao_elementos = 1)

union

-- parte VOX e VOX 0800
select distinct b1.rowidelementoplano element_id, 
       b2.external_id external_id, 
       6 external_id_type, 
       b1.componentid component_id,
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b2.dtinstancia, to_date('01/01/1999','dd/mm/yyyy')),
                 --nvl(b1.data_ultimo_faturamento, to_date('01/01/1999','dd/mm/yyyy')),                 
                 nvl(b1.primeirocdr, to_date('01/01/1999','dd/mm/yyyy'))
                 ) dt_ativar,
       b1.serverid server_id, 
       b2.subscrno subscrno, 
       b1.external_id instancia, 
       0 flag,
       'Plano Corporate VOX' motivo,
       plano siebelcomponentdescription,
       'Instância' nivelkenan,
       0 mod_lavoisier,
       0 jurisdiction,
       0 units
  from bss_tratamento_cdrs b1,  bss_tratamento_cdrs_range b2
 where     b1.processo = 'Erro440' and b2.processo = 'Erro440'
           and b1.created = trunc(sysdate) and b2.created = trunc(sysdate) 
           and b1.tipoplano = 'C'
           and (b1.tipo_instancia_siebel like 'VOX 0800%'
                OR b1.tipo_instancia_siebel like 'VOX REMOTA%'
                OR (b1.plano like '%VOX DDR%' and parcomponentidlocal = 0))
   and b1.external_id = b2.instancia
   --and b1.external_id = ?
   and componentid > 0
   and b2.dtinstancia is not null
   and b1.resultado in (5,3)
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate) 
           and instancia = b1.external_id 
           and external_id = b2.external_id 
           and componentid = b1.componentid
           and desativado_pelo_processo is null
           and validacao_elementos = 1)

--- 2014-09-24: RETIRADO O CÁLCULO DOS PRODUTOS NÍVEL CONTA
/*           
union          

-- parte Franquia Nível Conta (somente Siebel8)
select b1.rowidelementoplano         element_id, 
       b1.extid_acctno               external_id, 
       1                             external_id_type, 
       b1.comp_id_franquia           component_id,
       min(
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')),
                 nvl(b1.primeirocdr, to_date('01/01/1999','dd/mm/yyyy'))
                 ))                  dt_ativar,
       b1.serverid                   server_id, 
       0 subscrno, -- grava o ACCOUNT_NO da conta no lugar do SUBSCR_NO para ativacoes nivel CONTA
       min(b1.external_id)           instancia,  
       1                             flag,
       'Franquia nível conta'        motivo,
       b1.franquia_n_conta           siebelcomponentdescription,
       'Conta'                       nivelkenan,
       0 mod_lavoisier,
       0 jurisdiction,
       0 units
  from bss_tratamento_cdrs b1
 where b1.processo = 'Erro440'
   and b1.created = trunc(sysdate)
   and b1.tipoplano in ('RS8', 'L')
   --and b1.tipo_instancia_siebel = 'Retail Siebel8'
   --and b1.external_id in ('4131544880')
   and b1.comp_id_franquia > 0
   and b1.resultado in (5,3)
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate) 
           and external_id = b1.extid_acctno
           and componentid = b1.comp_id_franquia
           -- and validacao_elementos = 1 -- não considera a validacao de elementos para produtos nivel conta
           and desativado_pelo_processo is null)
 group by b1.rowidelementoplano, b1.extid_acctno, b1.comp_id_franquia,
          b1.serverid, b1.account_no, b1.franquia_n_conta

union

-- parte CSP Nível Conta (somente Siebel8)
select distinct 
       b1.rowidelementoplano          element_id, 
       b1.extid_acctno                external_id, 
       1                              external_id_type, 
       b1.comp_id_csp_conta           component_id,
       min(
       greatest ( nvl(b1.data_instancia_kenan, to_date('01/01/1999','dd/mm/yyyy')), 
                 nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')),
                 nvl(b1.primeirocdr, to_date('01/01/1999','dd/mm/yyyy'))
                 ))                   dt_ativar,
       b1.serverid                    server_id, 
       0                              subscrno,
       min(b1.external_id)            instancia,  
       1                              flag,
       'CSP nível conta'              motivo,
       b1.csp_n_conta                 siebelcomponentdescription,
       'Conta'                        nivelkenan,
       0 mod_lavoisier,
       0 jurisdiction,
       0 units
  from bss_tratamento_cdrs b1
 where b1.processo = 'Erro440'
   and b1.created = trunc(sysdate)
   and b1.tipoplano in ('RS8', 'L')
   --and b1.tipo_instancia_siebel = 'Retail Siebel8'
   --and b1.external_id in ('4131544888')
   and b1.comp_id_csp_conta > 0
   and b1.resultado in (5,3)
   and not exists 
       (select * from bss_tratamento_cdrs_plano 
         where created = trunc(sysdate) 
           and external_id = b1.extid_acctno
           and (componentid = b1.comp_id_csp_conta
                or componentid in (29515,24060)) -- se o cliente já tiver algum 'Ligue Local X00' configurado
                                                 -- não provisiona o CSP
           -- and validacao_elementos = 1 -- não considera a validacao de elementos para produtos nivel conta
           and desativado_pelo_processo is null) 
 group by b1.rowidelementoplano, 
          b1.extid_acctno, 
          b1.comp_id_csp_conta, 
          trunc(sysdate), 
          b1.serverid, 
          b1.account_no, 
          b1.csp_n_conta
*/
