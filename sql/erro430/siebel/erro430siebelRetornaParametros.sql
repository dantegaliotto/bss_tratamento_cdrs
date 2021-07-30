SELECT MAX (DECODE (pname.NAME, 'Local Fixo', parametro.X_LISTA_DE_VALORES, '')) slocal,
       MAX (DECODE (pname.NAME, 'Local Móvel', parametro.X_LISTA_DE_VALORES,'')) svc,
       MAX (DECODE (pname.NAME, 'Plano LDN', parametro.X_LISTA_DE_VALORES, '')) sldn,
       MAX (DECODE (pname.NAME, 'Plano LDI', parametro.X_LISTA_DE_VALORES, '')) sldi
  FROM s_quote_item elemento,
       s_prod_int produto,
       s_param parametro,
       s_param_name pname
 WHERE elemento.quote_soln_id = ?
   AND elemento.stat_cd = 'Ativo'
   AND elemento.prod_id = produto.row_id
   AND produto.NAME IN
          ('Plano Corporate A','Plano Corporate B','Plano Corporate C','Plano Corporate D', 
'Plano Corporate E','Plano Corporate F','Plano Corporate G','Plano Corporate H',
'Plano Corporate I','Plano Corporate J','Plano Corporate L','Plano Corporate A La Carte',
'Plano Corporate A 30+6','Plano Corporate B 30+6','Plano Corporate C 30+6','Plano Corporate D 30+6',
'Plano Corporate E 30+6','Plano Corporate L VC1 Light')
   AND elemento.row_id = parametro.ref_table_id
   AND parametro.param_name_id = pname.row_id
   AND pname.NAME IN ('Local Fixo', 'Local Móvel', 'Plano LDN', 'Plano LDI')