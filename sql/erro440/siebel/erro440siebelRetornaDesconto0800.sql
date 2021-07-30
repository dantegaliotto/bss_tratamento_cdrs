SELECT elemento.row_id,
  produto.name,
  elemento.eff_end_dt
FROM s_quote_item elemento,
  s_prod_int produto
WHERE elemento.quote_soln_id = ?
AND elemento.stat_cd  = 'Ativo'
AND elemento.prod_id = produto.row_id
AND NAME like '%VOX 0800%'
and produto.x_provision_category like '%BILLING%'
AND (NAME like '% - %' or UPPER(NAME) LIKE 'DESCONTO%')