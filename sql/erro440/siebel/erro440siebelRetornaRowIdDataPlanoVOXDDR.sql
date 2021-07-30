select elemento.row_id, 
       elemento.eff_end_dt
  from s_quote_item elemento, 
       s_prod_int produto
 where elemento.quote_soln_id = ?
   AND elemento.prod_id = produto.row_id
   AND elemento.stat_cd  = 'Ativo'
   and produto.name = 'SERVIÇO VOX DDR'
