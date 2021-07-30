insert into bss_duplicados_querys_chamados(
select 'Erro430', trunc(sysdate), (seq_bss_dupl_querys_chamados.nextval) seq, 2 ordem, external_id, server_id, tracking_id, 
       component_id, data_desconexao, 0,
       'update cmf_package_component set inactive_dt = to_date(''' || to_char(data_desconexao,'dd/mm/yyyy') || 
       ''', ''dd/mm/yyyy'') where component_inst_id = (select distinct component_inst_id from cmf_component_element ' ||
       ' where association_id = ' || tracking_id || ' and component_id = ' || component_id || ');'
from bss_duplicados_desativacoes
where created = trunc(sysdate)
and tipo_acao = 'Via chamado'
and enviado = 0)
