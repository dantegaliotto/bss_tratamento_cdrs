SELECT elemento.row_id,
  produto.name,
  elemento.eff_end_dt
FROM s_quote_item elemento,
  s_prod_int produto
WHERE elemento.quote_soln_id = ?
AND elemento.stat_cd  = ?
AND elemento.prod_id = produto.row_id
and (produto.name           like 'Plano Degrau%'
     or produto.name           like 'Plano Corporate%'
     or produto.name           like 'Plano MIX%'
     or produto.name           like 'Plano Bronze%'
     or produto.name           like 'Plano Prata%'
     or produto.name           like 'Plano Ouro%'
     or produto.name           like 'Econômico %')


