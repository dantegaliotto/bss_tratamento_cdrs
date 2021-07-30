SELECT parametro.x_range_inicial,
  parametro.x_range_final
FROM s_quote_item elemento,
  s_prod_int produto,
  s_param parametro,
  s_param_name pname
WHERE elemento.quote_soln_id = ?
AND elemento.stat_cd         = 'Ativo'
AND elemento.prod_id         = produto.row_id
AND produto.name            IN ('SERVIÇO VOX DDR', 'DDR VOX IP')
AND elemento.row_id          = parametro.ref_table_id
AND parametro.param_name_id  = pname.row_id
AND pname.name              IN ('Range de ramais')