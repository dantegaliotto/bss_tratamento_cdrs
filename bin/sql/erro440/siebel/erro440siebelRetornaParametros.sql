select max (decode (spn.name, 'Local Fixo' , sp.x_lista_de_valores, '')) slocal,
       max (decode (spn.name, 'Local Móvel', sp.x_lista_de_valores, '')) svc,
       max (decode (spn.name, 'Plano LDN'  , sp.x_lista_de_valores, '')) sldn,
       max (decode (spn.name, 'Plano LDI'  , sp.x_lista_de_valores, '')) sldi
  from
       s_quote_item       sqi  ,
       s_qteitem_param    sqip ,
       s_param            sp   ,
       s_param_name       spn  ,
       s_prod_int         spi
 where sqi.quote_soln_id  = ?
   and sqip.quote_item_id = sqi.row_id
   and sqip.param_id      = sp.row_id
   and spn.row_id         = sp.param_name_id
   and spi.row_id         = sqi.prod_id
   and (spi.name           like 'Plano Degrau%'
     or spi.name           like 'Plano Corporate%'
     or spi.name           like 'Plano MIX%'
     or spi.name           like 'Plano Bronze%'
     or spi.name           like 'Plano Prata%'
     or spi.name           like 'Plano Ouro%'
     or spi.name           like 'Econômico %')
   and stat_cd            = 'Ativo'
   
/* query antiga que não utilizava a 's_qteitem_param' (alterado em 18/01/2012)
SELECT MAX (DECODE (pname.NAME, 'Local Fixo', parametro.X_LISTA_DE_VALORES, '')) slocal,
       MAX (DECODE (pname.NAME, 'Local Móvel', parametro.X_LISTA_DE_VALORES,'')) svc,
       MAX (DECODE (pname.NAME, 'Plano LDN', parametro.X_LISTA_DE_VALORES, '')) sldn,
       MAX (DECODE (pname.NAME, 'Plano LDI', parametro.X_LISTA_DE_VALORES, '')) sldi
  FROM s_quote_item elemento,
       s_prod_int produto,
       s_param parametro,
       s_param_name pname
 WHERE elemento.quote_soln_id = ?
   AND elemento.prod_id = produto.row_id
   AND produto.NAME like 'Plano Corporate%'
   AND elemento.row_id = parametro.ref_table_id
   AND parametro.param_name_id = pname.row_id
  AND pname.NAME IN ('Local Fixo', 'Local Móvel', 'Plano LDN', 'Plano LDI')
*/