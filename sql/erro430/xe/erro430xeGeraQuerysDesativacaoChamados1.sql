insert into bss_duplicados_querys_chamados(
select 'Erro430', trunc(sysdate), (seq_bss_dupl_querys_chamados.nextval) seq, 1 ordem, external_id, server_id, tracking_id, 
       component_id, data_desconexao, 0,
       'update product_view set billing_inactive_dt = to_date(''' || to_char(data_desconexao,'dd/mm/yyyy') || 
       ''', ''dd/mm/yyyy''), product_inactive_dt = to_date(''' || to_char(data_desconexao,'dd/mm/yyyy') || 
       ''', ''dd/mm/yyyy'') where tracking_id = ' || tracking_id || 
       ' and billing_inactive_dt is not null;'
from bss_duplicados_desativacoes
where created = trunc(sysdate)
and tipo_acao = 'Via chamado'
and enviado = 0)
