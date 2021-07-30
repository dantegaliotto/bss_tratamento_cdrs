SELECT elemento.row_id, 
       produto.name, 
       elemento.eff_end_dt
  FROM s_quote_item elemento,
       s_prod_int produto
 WHERE elemento.quote_soln_id = ?
   AND elemento.stat_cd = 'Ativo'
   AND elemento.prod_id = produto.row_id
   and produto.name like 'VOX REMOTA%'


/*
SELECT elemento.row_id,
  produto.name,
  elemento.eff_end_dt, produto.name
  FROM s_quote_item elemento,
       s_prod_int produto,
       s_param parametro --,
 WHERE elemento.quote_soln_id = ?
   AND elemento.stat_cd = 'Ativo'
   AND elemento.prod_id = produto.row_id
and produto.name like '%VOX REMOTA GVT%'
   AND elemento.row_id = parametro.ref_table_id
--   and parametro.x_lista_de_valores is not null
*/