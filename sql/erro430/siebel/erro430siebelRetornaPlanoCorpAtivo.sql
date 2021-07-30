SELECT elemento.row_id,
  produto.name,
  elemento.eff_end_dt
FROM s_quote_item elemento,
  s_prod_int produto
WHERE elemento.quote_soln_id = ?
AND elemento.stat_cd  = 'Ativo'
AND elemento.prod_id = produto.row_id
and produto.name in ('Plano Corporate A', 
'Plano Corporate B', 
'Plano Corporate C', 
'Plano Corporate D', 
'Plano Corporate E', 
'Plano Corporate F', 
'Plano Corporate G',
'Plano Corporate H', 
'Plano Corporate I', 
'Plano Corporate J', 
'Plano Corporate L', 
'Plano Corporate A La Carte',
'Plano Corporate A 30+6',
'Plano Corporate B 30+6',
'Plano Corporate C 30+6',
'Plano Corporate D 30+6',
'Plano Corporate E 30+6',
'Plano Corporate L VC1 Light')
