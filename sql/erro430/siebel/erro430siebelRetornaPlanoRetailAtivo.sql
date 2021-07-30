select produto.name, elemento.eff_end_dt, elemento.row_id
from s_prod_int produto, s_quote_item elemento, s_quote_soln instancia
where produto.row_id = elemento.prod_id
and elemento.stat_cd = 'Ativo'
and nvl(elemento.action_cd, 'nulo') != 'Desconectar'
and elemento.quote_soln_id = instancia.row_id
and instancia.row_id = ?
and produto.row_id in (select row_id from bq_tp_siebel_local)