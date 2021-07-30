select * from (

-- parte retail
select distinct b3.external_id        external_id, 
       '6'                            external_id_type,
       greatest ( nvl(b3.dtplano, to_date('01/01/1999','dd/mm/yyyy')), 
                  nvl(b1.data_plano_siebel, to_date('01/01/1999','dd/mm/yyyy')), 
                  nvl(b1.primeirocdr, to_date('01/01/1999','dd/mm/yyyy'))
                  ) dt_desativar,
       b3.componentid                 component_id, 
       b1.serverid                    server_id, 
       b3.subscrno                    subscrno, 
       b1.external_id                 instancia,
       b3.component_inst_id           component_inst_id,
       b3.package_id                  package_id,
       'Plano errado Retail'          motivo,
       b3.arborcomponentdescription   arborcomponentdescription,
       b3.nivelkenan                  nivelkenan
  from bss_tratamento_cdrs       b1, 
       bss_tratamento_cdrs_plano b3
 where b1.processo = 'Erro440' 
   and b3.processo = 'Erro440'
   and b1.created = trunc(sysdate) 
   and b3.created = trunc(sysdate)
   and b1.tipoplano in ('R','RS8','L')
   and b1.external_id = b3.instancia
   and b1.componentid > 0 
   and b3.componentid > 0
   and b1.resultado in (5,3)
   and b3.validacao_elementos = 1
   and b3.desativado_pelo_processo is null
   and instr(b1.componentid || ' ' || b1.cspid_kenan || ' ' || b1.parcomponentidvc, b3.componentid) = 0

union

-- parte duplicados
select distinct 
       s2.external_id                 external_id,
       '6'                            external_id_type,
       s2.dtplano                     dt_desativar, 
       s2.componentid                 componentid, 
       s2.serverid                    serverid, 
       s2.subscrno                    subscrno, 
       s2.instancia                   instancia,
       s2.component_inst_id           component_inst_id, 
       s2.package_id                  package_id,
       'Plano duplicado'              motivo,
       s2.arborcomponentdescription   arborcomponentdescription,
       s2.nivelkenan                  nivelkenan
  from 
      (select created, instancia, external_id, componentid, min(component_inst_id) component_inst_id, count(*) 
         from bss_tratamento_cdrs_plano b
        where created = trunc(sysdate)
          and b.validacao_elementos = 1
          and b.desativado_pelo_processo is null
          and exists -- o plano deve ser válido senão vai ser desconectado pelo plano indevido
             (select * from bss_tratamento_cdrs
               where instr(componentid || ' ' || cspid_kenan || ' ' || parcomponentidlocal || ' ' || parcomponentidldn || ' ' || parcomponentidldi || ' ' || parcomponentidvc, b.componentid)<>0  
                 and created = trunc(sysdate))
       group by created, instancia, external_id, componentid, nivelkenan
       having count(*) > 1) s1, 
       bss_tratamento_cdrs_plano s2
 where s1.componentid = s2.componentid
   and s1.component_inst_id <> s2.component_inst_id
   and s1.instancia = s2.instancia
   and s1.external_id = s2.external_id
   and s1.created = s2.created
   and s2.validacao_elementos = 1
   and s2.desativado_pelo_processo is null

union

-- parte erro de elementos
select distinct
   external_id, 
   '6'           external_id_type,
   dtplano, 
   componentid, 
   serverid, 
   subscrno, 
   instancia,
   component_inst_id, 
   package_id,
   'Plano com erro de elemento' motivo,
   arborcomponentdescription,
   nivelkenan
  from bss_tratamento_cdrs_plano
 where created = trunc(sysdate)
   and validacao_elementos = -1
   and desativado_pelo_processo is null
   and nivelkenan = 'Instância'

union

-- parte desativado por erro de datas de planos
select distinct
   b1.external_id, 
   '6'   external_id_type,
   b1.dtplano, 
   b1.componentid, 
   b1.serverid, 
   b1.subscrno, 
   b1.instancia,
   b1.component_inst_id, 
   b1.package_id,
   'Plano com erro de data posterior ao primeiro CDR' motivo,
   b1.arborcomponentdescription,
   b1.nivelkenan
  from bss_tratamento_cdrs_plano b1, bss_tratamento_cdrs b2
 where b1.created = trunc(sysdate) and b2.created = trunc(sysdate)
   and b1.instancia = b2.external_id
   and b2.resultado in (5,3)
   and b1.desativado_pelo_processo = 'Plano com erro de data posterior ao primeiro CDR'
   and b1.nivelkenan = 'Instância'
      
   ) --where component_id not like '3%'