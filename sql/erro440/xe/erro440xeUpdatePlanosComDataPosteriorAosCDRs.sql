update bss_tratamento_cdrs_plano
   set desativado_pelo_processo = 'Plano com erro de data posterior ao primeiro CDR'
 where created = trunc(sysdate)
   and (instancia, external_id, componentid, subscrno, dtplano, serverid, component_inst_id) in (
        select bp.instancia, bp.external_id, bp.componentid, bp.subscrno, bp.dtplano, bp.serverid, bp.component_inst_id
          from bss_tratamento_cdrs_plano bp, bss_tratamento_cdrs b1
         where bp.created = trunc(sysdate) and b1.created = trunc(sysdate)
           and bp.instancia = b1.external_id
           and bp.dtplano > greatest(nvl(b1.data_instancia_kenan,'01/01/1975'), 
                                     nvl(b1.data_plano_siebel,'01/01/1975'), 
                                     nvl(b1.primeirocdr,'01/01/1975') --, 
                                     --nvl(b1.data_ultimo_faturamento,'01/01/1975')
                                     )
           and bp.desativado_pelo_processo is null
           -- 13/04/2013: (Dante) Não desativa os planos nivel conta por que não guiam CDRs
           -- 23/04/2013: (Dante) Alterado para provisionar a data também do nível conta
           -- 17/06/2013: (Dante) Alterado NOVAMENTE para não validar produtos nível conta pela data
           and bp.nivelkenan = 'Instância'
           )