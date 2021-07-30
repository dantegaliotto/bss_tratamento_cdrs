select * from (

-- FRANQUIA DUPLICADA
select distinct 
       s2.external_id                 external_id, 
       '1'                            external_id_type,
       s2.dtplano                     dt_desativar, 
       s2.componentid                 component_id, 
       s2.serverid                    server_id, 
       0                              subscrno,            --s2.subscrno                    subscrno, 
       ''                             instancia,           --s2.instancia                   instancia,
       s2.component_inst_id           component_inst_id, 
       s2.package_id                  package_id,
       'Franquia duplicada'           motivo,
       s2.arborcomponentdescription   arborcomponentdescription,
       s2.nivelkenan                  nivelkenan
  from 
      (select created, instancia, external_id, componentid, min(component_inst_id) component_inst_id, count(*) 
         from bss_tratamento_cdrs_plano b
        where created = trunc(sysdate)
          --and b.validacao_elementos = 1
          and b.desativado_pelo_processo is null
          and exists -- o plano deve ser válido senão vai ser desconectado pelo plano indevido
             (select * from bss_tratamento_cdrs
               where instr(COMP_ID_FRANQUIA || ' ' || COMP_ID_CSP_CONTA, b.componentid)<>0  
                 and created = trunc(sysdate))
       group by created, instancia, external_id, componentid, nivelkenan
       having count(*) > 1) s1, 
       bss_tratamento_cdrs_plano s2
 where s1.componentid = s2.componentid
   and s1.component_inst_id <> s2.component_inst_id
   and s1.instancia = s2.instancia
   and s1.external_id = s2.external_id
   and s1.created = s2.created
   --and s2.validacao_elementos = 1
   and s2.desativado_pelo_processo is null

union

-- parte desativado por erro de datas de planos
select
   b1.external_id, 
   '1'   external_id_type,
   b1.dtplano, 
   b1.componentid, 
   b1.serverid, 
   0 subscrno, 
   min(b1.instancia) instancia,
   b1.component_inst_id, 
   b1.package_id,
   'Nivel conta com erro de data posterior ao primeiro CDR' motivo,
   b1.arborcomponentdescription,
   b1.nivelkenan
  from bss_tratamento_cdrs_plano b1, bss_tratamento_cdrs b2
 where b1.created = trunc(sysdate) and b2.created = trunc(sysdate)
   and b1.instancia = b2.external_id
   and b2.resultado in (5,3)
   and b1.desativado_pelo_processo = 'Plano com erro de data posterior ao primeiro CDR'
   and b1.nivelkenan = 'Conta' 
group by 
   b1.external_id, 
   b1.dtplano, 
   b1.componentid, 
   b1.serverid, 
   b1.component_inst_id, 
   b1.package_id,
   b1.arborcomponentdescription,
   b1.nivelkenan
      
/*  --- NÃO ESTÁ DESATIVANDO PRODUTOS NIVEL CONTA ERRADOS
union
-- parte S8 nivel conta
select distinct b3.external_id, 
       trunc(sysdate) dt_desativar,
       b3.componentid                 component_id, 
       b1.serverid                    server_id, 
       b1.account_no                  subscrno, -- grava o account_no no lugar do subscr_no para os produtos nivel conta
       b1.external_id                 instancia,
       b3.component_inst_id           component_inst_id,
       b3.package_id                  package_id,
       'Plano errado S8 conta'        motivo,
       b3.arborcomponentdescription   arborcomponentdescription,
       b3.nivelkenan                  nivelkenan
  from bss_tratamento_cdrs       b1, 
       bss_tratamento_cdrs_plano b3
 where b1.processo = 'Erro440' 
   and b3.processo = 'Erro440'
   and b1.created = trunc(sysdate) 
   and b3.created = trunc(sysdate)
   and b1.tipoplano = 'RS8'
   and b3.nivelkenan = 'Conta'
   and b1.external_id = b3.instancia
   and (b1.comp_id_franquia > 0 or b1.comp_id_csp_conta > 0)
   and b3.componentid > 0
   and b1.resultado in (5,3)
   and b3.desativado_pelo_processo is null
   and instr(b1.comp_id_franquia || ' ' || b1.comp_id_csp_conta, b3.componentid) = 0
*/
   ) 
