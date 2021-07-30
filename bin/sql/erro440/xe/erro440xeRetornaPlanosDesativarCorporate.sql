select distinct b3.external_id, 
       '6' external_id_type,
       greatest ( nvl(b3.dtplano, to_date('01/01/1999','dd/mm/yyyy')), 
                  nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')),
                  --nvl(b1.data_ultimo_faturamento, to_date('01/01/1999','dd/mm/yyyy')),
                  nvl(b1.primeirocdr, to_date('01/01/1999','dd/mm/yyyy')) 
                  ) dt_desativar,    
       b3.componentid                    component_id, 
       b1.serverid                       server_id, 
       b3.subscrno                       subscrno, 
       b1.external_id                    instancia,
       b3.component_inst_id              component_inst_id,
       b3.package_id                     package_id,
       'Plano indevido corporate'        motivo,
       b3.arborcomponentdescription      arborcomponentdescription,
       b3.nivelkenan                     nivelkenan
  from bss_tratamento_cdrs       b1, 
       bss_tratamento_cdrs_plano b3
 where b1.processo = 'Erro440'     and b3.processo = 'Erro440'
   and b1.created = trunc(sysdate) and b3.created = trunc(sysdate)
   and b1.tipoplano = 'C'
   and b1.external_id = b3.instancia
   and b1.resultado in (5,3)
   and b3.validacao_elementos = 1
   and b3.desativado_pelo_processo is null
   and (( -- Planos '%Corporate%' usam parametros + CSP
       b1.cspid_kenan + parcomponentidlocal + parcomponentidvc + parcomponentidldn + parcomponentidldi > 0
       and instr(b1.cspid_kenan || ' ' || parcomponentidlocal || ' ' || parcomponentidvc || ' ' || parcomponentidldn || ' ' || parcomponentidldi , b3.componentid) = 0
       and (plano like '%orporate%' 
         or plano like 'Econômico%' 
         or plano like '%Ouro%'
         or plano like '%Prata%'
         or plano like '%MIX%'
         or plano like '%Bronze%'
         )) or
       (  -- Planos VOX DDR / VOX RI / VOX 0800 usam o plano mais CSP (o desconto é gravado no local)
       b1.componentid + b1.cspid_kenan + b1.parcomponentidlocal > 0
       and instr(b1.componentid  || ' ' ||  b1.cspid_kenan  || ' ' ||  b1.parcomponentidlocal , b3.componentid) = 0
       and  (plano not like '%orporate%' 
         and plano not like 'Econômico%' 
         and plano not like '%Ouro%'
         and plano not like '%Prata%'
         and plano not like '%MIX%'
         and plano not like '%Bronze%')))
       